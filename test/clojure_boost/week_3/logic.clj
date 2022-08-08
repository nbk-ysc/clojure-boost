(ns clojure-boost.week-3.logic
  (:require [clojure.test :refer :all]
            [clojure-boost.week_3.logic :refer :all]
            [schema.core :as s]
            [java-time :as jt]))

(s/set-fn-validation! true)

(deftest nova-compra-test
  (testing "Validando que a função nova-compra devolve uma compra valida"
    (is (= (nova-compra (0
                          (jt/local-date "2022-05-09")
                          100M
                          "Amazon"
                          "Casa"
                          1111222233334444))
           (s/validate CompraSchema (->compra 0
                                              (jt/local-date "2022-05-09")
                                              100M
                                              "Amazon"
                                              "Casa"
                                              1111222233334444))))))

