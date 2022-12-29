(ns clojure-boost.logic-test
  (:require [clojure.test :refer :all]
            [clojure-boost.week3 :refer :all])
  (:use clojure.pprint))

(def compra [{:data            "2022-01-01"
              :valor           100
              :estabelecimento "Outback"
              :categoria       "Alimentação"
              :cartao          1234123412341234}
             {:data            "2022-01-02"
              :valor           200.00
              :estabelecimento "Dentista"
              :categoria       "Saúde"
              :cartao          1234123412341234}
             {:data            "2022-01-03"
              :valor           300.00
              :estabelecimento "Cinema"
              :categoria       "Lazer"
              :cartao          1234123412341234}
             ])

(deftest total-gasto-test
  (testing "Testa se a soma dos gastos passados está correto")
  (is (total-gasto compra) 600)
  )

(deftest agrupa-compras-por-categoria-test
  (testing "Testa se o agrupamento de gastos por categoria")
  (is (keys (agrupa-compras-por-categoria compra)) ["Alimentação" "Saúde" "Lazer"])
  )

(pprint (vals (agrupa-compras-por-categoria compra)))



