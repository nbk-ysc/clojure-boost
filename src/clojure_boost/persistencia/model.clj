(ns clojure-boost.persistencia.model)

(def schema-compra [{:db/ident       :compra/ID
                     :db/valueType   :db.type/long
                     :db/cardinality :db.cardinality/one}
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

