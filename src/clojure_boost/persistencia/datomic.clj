(ns clojure-boost.persistencia.datomic
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure-boost.persistencia.model :as persistencia.model]
            [clojure-boost.week_1.utils :as utils.week_1]
            [clojure-boost.week_2.logic :as logic.week_2]))

(def db-uri "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexao!
  "Funcao para criar uma conexao com o datomic"
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(def db (d/db (cria-conexao!)))

(d/transact (cria-conexao!) persistencia.model/schema-compra)

;-----------------------------------------------------------------------------------------------------------------------
(defn apaga-banco! []
  (d/delete-database db-uri))

(apaga-banco!)

;-----------------------------------------------------------------------------------------------------------------------
(defn lista-compras!
  "Função para listar todas as compras do banco"
  [db]
  (d/q '[:find (pull ?entidade [*])
         :where [?entidade :compra/ID]] db))

(count (lista-compras! db))

;-----------------------------------------------------------------------------------------------------------------------

(defn insere-compra!
  "Função para inserir uma compra no Datomic"
  [nova-compra]
  (d/transact (cria-conexao!) [nova-compra])
  )

(defn insere-compras-com-id!
  "Funacao para preencher um atomo com ID, baseado num vetor com compras sem ID"
  [lista-compras]
  (let [compra-a-validar (first (map #(get % :ID) lista-compras))]
    (if (not (nil? compra-a-validar))
      (do (def db (d/db (cria-conexao!)))
          (insere-compra! (assoc (first lista-compras) :compra/ID (logic.week_2/gera-id (lista-compras! db))))
          (insere-compras-com-id! (next lista-compras)))
      (if (> (count lista-compras) 0)
        (do (def db (d/db (cria-conexao!)))
            (insere-compra! (assoc (first lista-compras) :compra/ID (logic.week_2/gera-id (lista-compras! db))))
            (insere-compras-com-id! (next lista-compras)))
        (do (pprint "Conversão finalizada com sucesso!")
            false)))))

(insere-compras-com-id! utils.week_1/lista-compras-db)

(insere-compra! {:compra/data            "2022-06-25",
                 :compra/valor           10.0M,
                 :compra/estabelecimento "Cursos",
                 :compra/categoria       "Saúde",
                 :compra/cartao          1
                 :compra/ID              2})

;-----------------------------------------------------------------------------------------------------------------------

(defn lista-compras-por-cartao!
  "Função para listar todas as compras do banco"
  [db cartao]
  (d/q '[:find (pull ?entidade [*])
         :in $ ?cartao-a-ser-buscado
         :where [?entidade :compra/cartao ?cartao-a-ser-buscado]] db cartao))

(lista-compras-por-cartao! db 1234123412341234)

;-----------------------------------------------------------------------------------------------------------------------
;Drafts

(defn lista-de-cartoes!
  "Função para listar os cartões"
  [db]
  (d/q '[:find ?cartoes
         :where [_ :compra/estabelecimento ?cartoes]] db))

(defn lista-de-brinks!
  "Função para listar os cartões"
  [db]
  (d/q '[:find ?cartao ?estabelecimento
         :keys compra/cartao, compra/estabelecimento
         :where [?e-one :compra/estabelecimento ?estabelecimento]
         [?e-one :compra/cartao ?cartao]] db))

(lista-de-brinks! db)





