(ns clojure-boost.persistencia.datomic
  (:require [datomic.api :as d]
            [clojure-boost.persistencia.model :as persistencia.model]))

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
(defn insere-compra!
  "Função para inserir uma compra no Datomic"
  [nova-compra]
  (d/transact (cria-conexao!) [nova-compra])
  )

(insere-compra! {:compra/ID              2
                 :compra/data            "2022-06-25",
                 :compra/valor           10.0M,
                 :compra/estabelecimento "Saúde",
                 :compra/categoria       "Saúde",
                 :compra/cartao          12341234123412})

;-----------------------------------------------------------------------------------------------------------------------
(defn lista-compras!
  "Função para listar todas as compras do banco"
  [db]
  (d/q '[:find ?entidade
         :where [?entidade :compra/ID]] db))

(lista-compras! db)

;-----------------------------------------------------------------------------------------------------------------------

(defn lista-compras-por-cartao!
  "Função para listar todas as compras do banco"
  [db cartao]
  (d/q '[:find ?entidade
         :in $ ?cartao-a-ser-buscado
         :where [?entidade :compra/cartao ?cartao-a-ser-buscado]] db cartao))

(lista-compras-por-cartao! db 12341234123412)





