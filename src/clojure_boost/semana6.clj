(ns clojure-boost.semana6 (:use clojure.pprint)
                          (:require
                            [datomic.api :as d]
                            [clojure-boost.semana5 :as s5]))

"Comando - Conectar banco Datomic (banco local para estudo):
    bin/transactor -Ddatomic.printConnectionInfo=true config/dev-transactor-template.properties"

"Comando - Abrir Console Datomic (banco local):
    bin/console -p 8080 dev datomic:dev://localhost:4334"

(defonce db-uri "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexao! []
  (d/create-database db-uri)
  (d/connect db-uri))

(pprint (str "Banco conectado: "
             (cria-conexao!)))
"Uma mensagem semelhante a de abaixo deve aparecer se tudo estiver correto:

#object[datomic.peer.Connection 0x1dfe5dd1
{:unsent-updates-queue 0, :pending-txes 0,
:next-t 1000, :basis-t 66, :index-rev 0,
:db-id teste-81f05d8b-94f1-4e73-9e74-e15c8513aa4c}"

(defonce conn (d/connect db-uri))

(defonce schema [
                 {:db/ident       :compra/id
                  :db/valueType   :db.type/long
                  :db/cardinality :db.cardinality/one
                  :db/unique      :db.unique/identity}

                 {:db/ident       :compra/data
                  :db/valueType   :db.type/string
                  :db/cardinality :db.cardinality/one}

                 {:db/ident       :compra/valor
                  :db/valueType   :db.type/bigdec
                  :db/cardinality :db.cardinality/one}

                 {:db/ident       :compra/estabelecimento
                  :db/valueType   :db.type/string
                  :db/cardinality :db.cardinality/one}

                 {:db/ident       :compra/categoria
                  :db/valueType   :db.type/string
                  :db/cardinality :db.cardinality/many}

                 {:db/ident       :compra/cartao
                  :db/valueType   :db.type/long
                  :db/cardinality :db.cardinality/one}])

"Adiciona o schema no banco
Usa-se @(d/transact conn schema) para derreferenciar"
(d/transact conn schema)

"Cria o map de compra com a
funcao definida por schema CompraSchema (ver semana5)"
(def compra
  (s5/nova-compra "2022-12-30" 999 "teste" "Casa" 9999999))

"No Datomic eh importante que os nomes dos schemas
sejam escritos por keywords (exemplo 'compra/')
que lembrem o tipo de dado de entrada"
(defn compra->compra-datomic [id compra]
  {:compra/id              id
   :compra/data            (:data compra)
   :compra/cartao          (:cartao compra)
   :compra/valor           (:valor compra)
   :compra/estabelecimento (:estabelecimento compra)
   :compra/categoria       (:categoria compra)})

(defn insere-compra! [conn id compra]
  (let  [compradb (compra->compra-datomic id compra)]
    (d/transact conn [compradb])))

(insere-compra! conn 19
         (s5/nova-compra "2022-12-30" 999 "Mrk1" "Casa" 9999999))

(insere-compra! conn 18
         (s5/nova-compra "2023-01-10" 8888 "Mrk2" "Lazer" 888888))

"Banco db apenas para leitura"
(def db (d/db conn))
(defn lista-compras! [db]
  (d/q '[:find (pull ?e [*])
         :in $
         :where [?e :compra/id]]
       db))

(lista-compras! db)

(defn lista-compras-por-cartao! [db numb]
  (d/q '[:find (pull ?e [*])
         :in $ ?numbcard
         :where [?e :compra/cartao ?numbcard]]
       db numb))

(lista-compras-por-cartao! db 888888)

(defn apaga-banco! []
  (d/delete-database db-uri))


