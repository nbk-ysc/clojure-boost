(ns clojure-boost.semana5-test
  (:require [clojure.test :refer :all]
            [clojure-boost.semana3 :refer :all])
  (:use clojure.pprint))

(def listacompra
            [{:data            "2022-11-01"
              :valor           100
              :estabelecimento "teste1"
              :categoria       "Alimentação"
              :cartao          412341234}
             {:data            "2022-06-02"
              :valor           200
              :estabelecimento "teste2"
              :categoria       "Saúde"
              :cartao          99999999}
             {:data            "2022-12-23"
              :valor           300
              :estabelecimento "teste3"
              :categoria       "Lazer"
              :cartao          4567897099}
             ])

(deftest total-gasto-test
  (testing
    (is
      (= (total-gasto listacompra) 600)
      )))

(deftest total-gasto-categoria-test
  (testing
    (is (= {"Alimentação" 100, "Lazer" 300, "Saúde" 200}
           (total-gasto-categoria listacompra)
           ))))
