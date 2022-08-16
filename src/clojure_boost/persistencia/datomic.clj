(ns clojure-boost.persistencia.datomic
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(defn lista-compras [id data valor estabelecimento categoria cartao]
  {:produto/id              id
   :produto/data            data
   :produto/preco           valor
   :produto/estabelecimento estabelecimento
   :produto/categoria       categoria
   :produto/cartao          cartao})

(def db-uri "datomic:dev://localhost:4334/lista-compras")

(defn cria-conexão! []
  (d/create-database db-uri)
  (d/connect db-uri))


(defn apaga-banco []
  (d/delete-database db-uri))

(cria-conexão!)


(def schema [{:db/ident       :produto/id
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}
             {:db/ident       :produto/data
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :produto/preco
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one}
             {:db/ident       :produto/estabelecimento
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :produto/categoria
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :produto/cartao
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}])


(defn cria-schema [conn]
  (d/transact conn schema))

(def db (d/db cria-conexão!))

(defn lista-compras! []
  (d/q '[:find ?ent
         :where [?ent :produto/data]] db))


