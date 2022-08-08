(ns clojure-boost.week3.logic
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [clojure-boost.week3.schemata.compra_client :as sc]
            [clojure-boost.week1.compra-realizada :as c]))

(s/set-compile-fn-validation! true)

;(s/defn nova-compra [compra :- sc/CompraSchema]
;  (->> compra
;       (get compra)))

;(pprint (s/validate nova-compra sc/CompraSchema))




;(pprint (s/validate sc/CompraSchema {:id              01
;                                     :data            "2022-01-01"
;                                     :valor           129.90
;                                     :estabelecimento "Outback"
;                                     :categoria       "Alimentação"
;                                     :cartão          1234123412341234}))


















; teste
;(s/defn nova-compra :- compra-realizada/nova-compra
;  [id-cliente :- s/Uuid]
;  (->> {:data  :valor
;        :estabelecimento :categoria
;        :cartao {:id id-cliente}}))
;
;(pprint nova-compra)
