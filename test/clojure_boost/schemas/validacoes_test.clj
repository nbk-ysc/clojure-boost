(ns clojure-boost.schemas.validacoes-test
  (:require [clojure.test :refer :all]
            [clojure-boost.schemas.validacoes :refer :all]
            [java-time :as java-time]
            [schema.core :as s]))

(s/set-fn-validation! true)


(deftest valor-valido?-test
  (testing "Quando valor é do tipo bigdecimal positivo"
    (is (true? (valor-valido? 345.98848442M))))

  (testing "Quando valor não é do tipo bigdecimal"
    (is (false? (valor-valido? 10))))

  (testing "Quando valor não é positivo"
    (is (false? (valor-valido? -0.01M))))

  (testing "Quando valor é zero"
    (is (false? (valor-valido? 0)))))

(deftest estabelecimento-valido?-test
  (testing "Quando estabelecimento é maior que 2 caracteres"
    (is (true? (estabelecimento-valido? "Outback"))))

  (testing "Quando estabelecimento com 2 caracteres"
    (is (true? (estabelecimento-valido? "Au"))))

  (testing "Quando estabelecimento menor que 2 caracteres"
    (is (false? (estabelecimento-valido? "O"))))

  (testing "Quando estabelecimento vazio caracteres"
    (is (false? (estabelecimento-valido? "")))))

(deftest data-menor-or-igual-data-atual?-test
  (let [data-atual            (java-time/local-date)
        data-atual-mais-1-dia (java-time/plus data-atual (java-time/days 1))]

    (testing "Quando data é menor que a data atual"
      (is (true? (data-menor-or-igual-data-atual? (java-time/local-date 2022 1 16)))))

    (testing "Quando data é igual que a data atual"
      (is (true? (data-menor-or-igual-data-atual? data-atual))))

    (testing "Quando data é igual que a data atual"
      (is (false? (data-menor-or-igual-data-atual? data-atual-mais-1-dia))))))

(deftest limite-numero-do-cartao-valido?-test
  (testing "Quando cartão do dentro do limite maior que 0 e menor que 10000000000000000"
    (is (true? (limite-numero-do-cartao-valido? 12358812341))))

  (testing "Quando número do cartão com o valor minimo zero (0)"
    (is (true? (limite-numero-do-cartao-valido? 0))))

  (testing "Quando número do cartão com o valor máximo 10000000000000000"
    (is (true? (limite-numero-do-cartao-valido? 10000000000000000))))

  (testing "Quando número cartão limite negativo"
    (is (false? (limite-numero-do-cartao-valido? -1))))

  (testing "Quando número cartão limite maior que o limite"
    (is (false? (limite-numero-do-cartao-valido? 10000000000000001)))))
