(ns clojure-boost.persistencia.datomic
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexao! []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco! []
  (d/delete-database db-uri))







