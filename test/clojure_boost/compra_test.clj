(ns clojure-boost.compra-test
  (:require [clojure-boost.funcoes :refer [total-gasto
                                           total-gasto-por-categoria]]
            [clojure-boost.schema.Compra :refer [CompraSchema]]
            [clojure.test :refer :all]
            [schema.core :as s]))

(s/set-fn-validation! true)

(deftest total-gasto-test
  (testing "Que a função retorno a soma de todos os gastos"
    (let [compras [{:id 1,
                    :data-da-compra "2022-12-20"
                    :valor "100"
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 9999999999999999},
                   {:id 2,
                    :data-da-compra "2022-12-20"
                    :valor "200"
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 9999999999999999},
                   {:id 3,
                    :data-da-compra "2022-12-20"
                    :valor "300"
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 9999999999999999}]]

      (is (= 600.0, (total-gasto compras))))))

(deftest total-gasto-por-categoria-test
  (testing "Que a função total-gasto-por-categoria retorna a soma de todos os gastos por categoria"
    (let [compras [{:id 1,
                    :data-da-compra "2022-12-20"
                    :valor "100"
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 9999999999999999},
                   {:id 2,
                    :data-da-compra "2022-12-20"
                    :valor "200"
                    :estabelecimento "Mercado"
                    :categoria "Casa"
                    :cartao 9999999999999999},
                   {:id 3,
                    :data-da-compra "2022-12-20"
                    :valor "300"
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 9999999999999999},
                   {:id 4,
                    :data-da-compra "2022-12-20"
                    :valor "700"
                    :estabelecimento "Mercado"
                    :categoria "Casa"
                    :cartao 9999999999999999}]]

      (is (= {"Alimentação" 400.0, "Casa" 900.0} (total-gasto-por-categoria compras))))))

(deftest compra-schema-test
  (testing "Que o esquema CompraSquema retorna os valores conforme os tipos esperados"
    (let [compra {:data "2022-05-09"
                  :valor 100M
                  :estabelecimento "Amazon"
                  :categoria "Casa"
                  :cartao 1111222233334444}]

      (is (= compra (s/validate CompraSchema {:data "2022-05-09"
                                              :valor 100M
                                              :estabelecimento "Amazon"
                                              :categoria "Casa"
                                              :cartao 1111222233334444})))))

  (testing "Que o esquema CompraSchema não aceita uma data invalida"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data ""
                                           :valor 100M
                                           :estabelecimento "Amazon"
                                           :categoria "Casa"
                                           :cartao 1111222233334444})))

    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data "09-05-2022"
                                           :valor 100M
                                           :estabelecimento "Amazon"
                                           :categoria "Casa"
                                           :cartao 1111222233334444}))))

  (testing "Que o esquema CompraSchema não aceita um valor negativo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data "2022-05-09"
                                           :valor -100M
                                           :estabelecimento "Amazon"
                                           :categoria "Casa"
                                           :cartao 1111222233334444}))))

  (testing "Que o esquema CompraSchema não aceita um estabelecimento com menos de 2 caracteres"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data "2022-05-09"
                                           :valor 100M
                                           :estabelecimento "A"
                                           :categoria "Casa"
                                           :cartao 1111222233334444})))

    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data "2022-05-09"
                                           :valor 100M
                                           :estabelecimento ""
                                           :categoria "Casa"
                                           :cartao 1111222233334444}))))

  (testing "Que o esquema CompraSchema não aceita uma categoria que não esteja entre as aceitas"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data "2022-05-09"
                                           :valor 100M
                                           :estabelecimento "Amazon"
                                           :categoria "Categoria invalida"
                                           :cartao 1111222233334444})))

    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data "2022-05-09"
                                           :valor 100M
                                           :estabelecimento "Amazon"
                                           :categoria ""
                                           :cartao 1111222233334444}))))

  (testing "Que o esquema CompraSchema não aceita um cartao invalido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data "2022-05-09"
                                           :valor 100M
                                           :estabelecimento "Amazon"
                                           :categoria "Casa"
                                           :cartao -1111222233334444}))))

  (is (thrown? clojure.lang.ExceptionInfo
               (s/validate CompraSchema {:data "2022-05-09"
                                         :valor 100M
                                         :estabelecimento "Amazon"
                                         :categoria "Casa"
                                         :cartao 0}))))
