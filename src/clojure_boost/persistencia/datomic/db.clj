(ns clojure-boost.persistencia.datomic.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-url "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexao! []
  (d/create-database db-url)
  (d/connect db-url))

(def schema [{:db/ident       :compra/id
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one
              :db/doc         "Id da compra"}
             {:db/ident       :compra/data
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Data da compra"}
             {:db/ident       :compra/valor
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "Valor da compra"}
             {:db/ident :compra/estabelecimento
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc "Estabelecimento da compra"}
             {:db/ident :compra/categoria
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc "Categoria da compra"}
             {:db/ident :compra/cartao
              :db/valueType :db.type/long
              :db/cardinality :db.cardinality/one
              :db/doc "Cartão da compra"}])

(defn cria-schema [conn]
  (d/transact conn schema))




(defn apaga-banco []
  (d/delete-database db-url))
