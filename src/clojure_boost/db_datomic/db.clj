(ns clojure-boost.db-datomic.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexão!
  []
  (d/create-database db-uri)
  (d/connect db-uri))
