(ns clojure-boost.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure-boost.persistencia.datomic :as db]))


(def conn (db/cria-conexão!))

(db/cria-schema conn)

(let [salvar (db/lista-compras 01 "2022/02/20" 30.0M "Outback" "alimentacao" 123123123123123)]
  (d/transact conn [salvar]))

(db/apaga-banco)


(db/lista-compras!)