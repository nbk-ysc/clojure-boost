(ns clojure-boost.testedb
  (:use clojure.pprint)
  (:require [datomic.api :as d]))
(def db-url "datomic:dev://localhost:4334/hello")

(defn abre-conexao []
  (d/create-database db-url)
  (d/connect db-url))


(def conn (abre-conexao))

(defn apaga-banco []
  (d/delete-database db-url))


(defn novo-produto [nome, slug, preco]
  {:produto/nome  nome,
   :produto/slug  slug,
   :produto/preco preco})

(pprint (novo-produto "Computador", "/novo-computador", 100))

(def schema [{:db/ident       :produto/nome
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Nome do produto"}
             {:db/ident       :produto/slug
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Path http"}
             {:db/ident       :produto/preco
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "Preço do produto"}
             {:db/ident :produto/palavra-chave
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/many
              :db/doc "Palavra chave"}])

(d/transact conn schema)

(def db (d/db conn))

(pprint (let [computador-novo (novo-produto "Computador1", "/novo-computador", 2500.10M)]
          (d/transact conn [computador-novo])))
(d/q '[:find ?entidade
       :where [?entidade :produto/nome]] db)


(def db (d/db conn))
(pprint (let [computador-novo (novo-produto "Computador10", "/novo-computador-maroto", 2500.10M)]
          (d/transact conn [computador-novo])))

(d/q '[:find ?entidade
       :where [?entidade :produto/nome]] db)


(def db (d/db conn))
(let [calculadora {:produto/nome "Calculadora marota"}]
  (d/transact conn [calculadora]))

(let [celular-barato (novo-produto "Computador1", "/novo-computador", 2500.10M)
      resultado-query @(d/transact conn [celular-barato])
      id-entidade (first (vals (:tempids resultado-query)))]
  (pprint @(d/transact conn [[:db/add id-entidade :produto/preco 0.1M]]))
  (pprint @(d/transact conn [[:db/retract id-entidade :produto/slug "/novo-computador"]])))

(let [computador-novo (novo-produto "Computador2", "/novo-x", 100.1M)
      celular-barato (novo-produto "Computador1", "/novo-computador", 2500.10M)
      calculadora {:produto/nome "Calculadora marota"}]
  (pprint @(d/transact conn [computador-novo, celular-barato, calculadora])))


(defn todos-os-slugs [db]
  (d/q '[:find ?qualquer-valor
         :where [?entidade :produto/slug ?qualquer-valor]] db))

(defn todos-os-ids [db]
  (d/q '[:find ?entidade
         :where [?entidade :produto/nome ?x]] db))

(defn procura-nome-id [db]
  (d/q '[:find ?nome, ?preco
         :keys produto/nome, produto/preco
         :where [?produto :produto/nome ?nome]
         [?produto :produto/preco ?preco]] db))
(defn procura-nome-id-outro [db]
  (d/q '[:find (pull ?produto [:produto/nome :produto/preco :produto/slug])
         :where [?produto :produto/nome ?nome]
         [?produto :produto/preco ?preco]] db))

(defn todos-os-produtos [db]
  (d/q '[:find (pull ?produto [*])
         :where [?produto :produto/nome ?nome]] db))

(defn procura-um-id [db id]
  (d/q '[:find (pull ?id [*])
         :in $ ?id
         :where [?id :produto/nome]] db id))

(pprint (procura-um-id (d/db conn) 17592186045575))

(pprint (todos-os-produtos (d/db conn)))

(defn todos-os-produtos-por-preco [db]
  (d/q '[:find ?nome, ?preco
         :where
         [?produto :produto/preco ?preco]
         [(> ?preco 200)]
       [?produto :produto/nome ?nome]]
       db))

(defn procurando-palavra-chave [db palavra-chave]
  (d/q '[:find (pull ?produto [*])
         :in $ ?palavra-chave
         :where [?produto :produto/palavra-chave ?palavra-chave]] db palavra-chave))

(pprint (procurando-palavra-chave (d/db conn) "testando-novamente"))

(d/transact conn [[:db/add 17592186045575 :produto/palavra-chave "testando-novamente"]])

(pprint (todos-os-produtos-por-preco (d/db conn)))

(pprint (todos-os-ids (d/db conn)))

(pprint (todos-os-slugs (d/db conn)))

(pprint (procura-nome-id (d/db conn)))

(pprint (procura-nome-id-outro (d/db conn)))



(pprint (procura-nome-id (d/as-of (d/db conn) #inst "2022-08-09T03:13:37.449-00:00")))











