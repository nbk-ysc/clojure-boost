(ns persistencia.datomic
  (:require [datomic.api :as d])
  )

(def db-uri "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexao!
  "Cria Conexao no Datomic"
  []
  (d/create-database db-uri)
  )

(defn connecta! []
  (d/connect db-uri)
  )

(def schema
  [
   {:db/ident :compra/dia
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    },
   {:db/ident :compra/valor
    :db/valueType :db.type/bigdec
    :db/cardinality :db.cardinality/one
    },
   {:db/ident :compra/estabelecimento
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    },
   {
    :db/ident :compra/categoria
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    },
   {:db/ident :compra/cartao
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    }]
  )

(defn add-compras [dia valor estabelecimento categoria cartao]
  {:compra/dia dia
   :compra/valor valor
   :compra/estabelecimento estabelecimento
   :compra/categoria categoria
   :compra/cartao cartao
   }
  )

(def conn (connecta!))

(d/transact conn schema)

(let [compras (add-compras "2022-01-01" 129M "Clube" "Saúde" "1234123412341234")]
  (d/transact conn [compras])
  )

