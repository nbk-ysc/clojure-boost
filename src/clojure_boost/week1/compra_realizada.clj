(ns clojure-boost.week1.compra-realizada
  (:use clojure.pprint)
  (:require [clojure-boost.week3.schemata.compra_client :as cs]
            [schema.core :as s]))

(s/set-compile-fn-validation! true)

(s/def compra :- cs/CompraSchema {:id               01
                                   :data            "2022-01-01"
                                   :valor           129.90
                                   :estabelecimento "Outback"
                                   :categoria       "Alimentação"
                                   :cartão          1234123412341234})


;(comment (pprint compra))