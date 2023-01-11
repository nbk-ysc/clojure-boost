(ns semana6.db
  (:require [datomic.api :as d]
            [semana6.adapters.compra :as adapter-compra]))

(def db-url "datomic:dev://localhost:4334/clojure-boost")

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
              :db/cardinality :db.cardinality/one}

             {:db/ident       :compra/cartao
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}])

(defn cria-schema [conexao]
  (d/transact conexao schema))

(defn cria-conexao! []
  (let [conexao (d/connect db-url)]
    (d/create-database db-url)
    (cria-schema conexao)
    conexao))

(defn insere-compra! [conexao compra]
  (d/transact conexao [(adapter-compra/compra->compra-datomic compra)]))

(defn lista-compras! [conexao]
  (let [snapshot-db (d/db conexao)] 
    (map adapter-compra/compra-datomic->compra (-> (d/q '[:find (pull ?compra-id [*])
                                                          :where [?compra-id :compra/id]] snapshot-db)))))

(defn lista-compras-por-cartao! [conexao numero-cartao]
  (let [snapshot-db (d/db conexao)] 
    (d/q '[:find (pull ?id-compra [*])
           :in $ ?numero-cartao
           :where [?id-compra :compra/cartao ?numero-cartao]]
         snapshot-db numero-cartao)))
