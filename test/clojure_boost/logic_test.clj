(ns clojure-boost.logic-test
  (:require [clojure.test :refer :all]
            [clojure-boost.week3 :refer :all]
            [schema.core :as s]
            [clojure-boost.week5 :refer :all])
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

(def compras-agrupadas (agrupa-compras-por-categoria compra))

(deftest agrupa-compras-por-categoria-test
  (testing "Testa se o agrupamento de gastos por categoria")
  (is (keys compras-agrupadas) ["Alimentação" "Saúde" "Lazer"])
  )

(pprint compras-agrupadas)
(deftest valida-compra-test
  (testing "Testa se uma compra for válida")
  (s/validate Compra (nova-compra-schema (bigdec 100000000) "Amazon" "Casa" 11112222 "2022-05-09"))
  (is Compra (nova-compra-schema (bigdec 100000000) "Amazon" "Casa" 11112222 "2022-05-09"))
  )

(deftest nao-valida-compra-test
  (testing "Testa se uma compra for válida")
  (s/validate Compra (nova-compra-schema (bigdec 100000000) "Amazon" "Casa" 1111222233334444 "2022-05-09"))
  (is Compra (nova-compra-schema (bigdec 100000000) "Amazon" "Casa" 11112222333344444 "2022-05-09"))
  )


