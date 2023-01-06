(ns clojure-boost.db-datomic.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexão!
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco!
  []
  (d/delete-database db-uri))

(def schema [{:db/ident       :compra/id
              :db/valueType   :db.type/uuid
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

(defn cria-schema!
  [conn]
  (d/transact conn schema))

(defn insere-compra!
  [db compra]
  (d/transact db compra))

(defn lista-compras!
  [db]
  (d/q '[:find (pull ?compra [*])
         :where [?compra :compra/id]]
       db))

(defn lista-compras-por-cartao!
  ([conn cartao-a-ser-buscado]
   (d/q '[:find (pull ?compra [*])
          :in $ ?cartao
          :where [?compra :compra/cartao ?cartao]]
        conn cartao-a-ser-buscado))

  ([conn cartao-a-ser-buscado mes-a-ser-buscado]
   (d/q '[:find (pull ?compra [*])
          :in $ ?cartao ?mes-a-ser-buscado
          :where
          [?compra :compra/cartao ?cartao]
          [?compra :compra/data ?mes]
          [(clojure.string/includes? ?mes ?mes-a-ser-buscado)]]
        conn cartao-a-ser-buscado (format "-%s-" mes-a-ser-buscado))))
