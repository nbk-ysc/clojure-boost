(ns clojure-boost.persistencia.model)

(def schema-compra [{:db/ident       :compra/ID
                     :db/valueType   :db.type/long
                     :db/cardinality :db.cardinality/one}
                    {:db/ident       :compra/data
                     :db/valueType   :db.type/instant
                     :db/cardinality :db.cardinality/one}
                    {:db/ident       :student/valor
                     :db/valueType   :db.type/bigdec
                     :db/cardinality :db.cardinality/one
                     :db/unique      :db.unique/identity}
                    {:db/ident       :semester/estabelecimento
                     :db/valueType   :db.type/string
                     :db/cardinality :db.cardinality/one}
                    {:db/ident       :semester/categoria
                     :db/valueType   :db.type/string
                     :db/cardinality :db.cardinality/one}
                    {:db/ident       :semester/cartao
                     :db/valueType   :db.type/long
                     :db/cardinality :db.cardinality/one}])
