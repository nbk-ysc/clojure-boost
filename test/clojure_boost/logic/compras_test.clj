(ns clojure-boost.logic.compras-test
  (:require [clojure.test :refer :all]
            [clojure-boost.logic.compras :refer :all]
            [java-time :as java-time]
            [schema.core :as s]))

(s/set-fn-validation! true)


(def lista-compras-teste [{:id              1,
                           :data            (java-time/local-date 2022 7 28),
                           :valor           993.13M,
                           :estabelecimento "Alura",
                           :categoria       "Alimentação",
                           :cartao          99888773133333}
                          {:id              2,
                           :data            (java-time/local-date 2022 8 02),
                           :valor           4412.33M,
                           :estabelecimento "Alura",
                           :categoria       "Alimentação",
                           :cartao          1234123412341234}
                          {:id              3,
                           :data            (java-time/local-date 2022 2 03),
                           :valor           123.0M,
                           :estabelecimento "Alura",
                           :categoria       "Educação",
                           :cartao          99888773133333}
                          {:id              4,
                           :data            (java-time/local-date 2022 3 13),
                           :valor           309099.8844M,
                           :estabelecimento "Alura",
                           :categoria       "Educação",
                           :cartao          1234123412341234}
                          {:id              5,
                           :data            (java-time/local-date 2021 1 16),
                           :valor           1.0M,
                           :estabelecimento "Alura",
                           :categoria       "Saúde",
                           :cartao          76766789033312}
                          {:id              6,
                           :data            (java-time/local-date 2022 8 01),
                           :valor           0.99M,
                           :estabelecimento "Alura",
                           :categoria       "Saúde",
                           :cartao          1234123412341234}
                          {:id              7,
                           :data            (java-time/local-date 2022 8 03),
                           :valor           1003.44M,
                           :estabelecimento "Alura",
                           :categoria       "Saúde",
                           :cartao          99888773133333}])


(deftest total-gasto-test
  (testing "Soma total dos valores corretamente"
    (let [lista-compras [{:id              1,
                          :data            (java-time/local-date 2022 7 28),
                          :valor           200.0M,
                          :estabelecimento "Alura",
                          :categoria       "Alimentação",
                          :cartao          1234123412341234}
                         {:id              2,
                          :data            (java-time/local-date 2022 8 02),
                          :valor           100.0M,
                          :estabelecimento "Alura",
                          :categoria       "Alimentação",
                          :cartao          1234123412341234}
                         {:id              3,
                          :data            (java-time/local-date 2022 8 03),
                          :valor           300.0M,
                          :estabelecimento "Alura",
                          :categoria       "Educação",
                          :cartao          1234123412341234}]]
      (is (= 600.0M (total-gasto lista-compras))))))

(deftest total-compras-por-categoria-test
  (testing "Quando categoria retorna valores somados corretamente"
    (is (= {"Alimentação" 5405.46M,
            "Educação"    309222.8844M,
            "Saúde"       1005.43M}
           (total-compras-por-categoria lista-compras-teste)))))

(deftest agrupa-categoria-test
  (testing "Quando retorna categorias agrupadas"
    (let [resultado-esperado {"Alimentação"
                              [{:id              1,
                                :data            (java-time/local-date 2022 7 28),
                                :valor           993.13M,
                                :estabelecimento "Alura",
                                :categoria       "Alimentação",
                                :cartao          99888773133333}
                               {:id              2,
                                :data            (java-time/local-date 2022 8 02),
                                :valor           4412.33M,
                                :estabelecimento "Alura",
                                :categoria       "Alimentação",
                                :cartao          1234123412341234}],
                              "Educação"
                              [{:id              3,
                                :data            (java-time/local-date 2022 2 03),
                                :valor           123.0M,
                                :estabelecimento "Alura",
                                :categoria       "Educação",
                                :cartao          99888773133333}
                               {:id              4,
                                :data            (java-time/local-date 2022 3 13),
                                :valor           309099.8844M,
                                :estabelecimento "Alura",
                                :categoria       "Educação",
                                :cartao          1234123412341234}],
                              "Saúde"
                              [{:id              5,
                                :data            (java-time/local-date 2021 1 16),
                                :valor           1.0M,
                                :estabelecimento "Alura",
                                :categoria       "Saúde",
                                :cartao          76766789033312}
                               {:id              6,
                                :data            (java-time/local-date 2022 8 1),
                                :valor           0.99M,
                                :estabelecimento "Alura",
                                :categoria       "Saúde",
                                :cartao          1234123412341234}
                               {:id              7,
                                :data            (java-time/local-date 2022 8 3),
                                :valor           1003.44M,
                                :estabelecimento "Alura",
                                :categoria       "Saúde",
                                :cartao          99888773133333}]}]
      (is (= resultado-esperado
             (agrupa-categoria lista-compras-teste))))))


(deftest compras-por-cartao-test
  (testing "Quando realizo filtro de compras por cartão existente"
    (is (= [{:id              2,
             :data            (java-time/local-date 2022 8 2),
             :valor           4412.33M,
             :estabelecimento "Alura",
             :categoria       "Alimentação",
             :cartao          1234123412341234}
            {:id              4,
             :data            (java-time/local-date 2022 3 13),
             :valor           309099.8844M,
             :estabelecimento "Alura",
             :categoria       "Educação",
             :cartao          1234123412341234}
            {:id              6,
             :data            (java-time/local-date 2022 8 1),
             :valor           0.99M,
             :estabelecimento "Alura",
             :categoria       "Saúde",
             :cartao          1234123412341234}]
           (compras-por-cartao lista-compras-teste 1234123412341234))))

  (testing "Quando realizo filtro de compras por cartão inexistente"
    (is (= []
           (compras-por-cartao lista-compras-teste 99999)))))

(deftest compras-por-mes-test
  (testing "Quando realizo filtro de compras por mes"
    (is (= [{:id              2,
             :data            (java-time/local-date 2022 8 2),
             :valor           4412.33M,
             :estabelecimento "Alura",
             :categoria       "Alimentação",
             :cartao          1234123412341234}
            {:id              6,
             :data            (java-time/local-date 2022 8 1),
             :valor           0.99M,
             :estabelecimento "Alura",
             :categoria       "Saúde",
             :cartao          1234123412341234}
            {:id              7,
             :data            (java-time/local-date 2022 8 3),
             :valor           1003.44M,
             :estabelecimento "Alura",
             :categoria       "Saúde",
             :cartao          99888773133333}]
           (compras-por-mes 8 lista-compras-teste))))

  (testing "Quando realizado filtro de compras por mes que não existe compras"
    (is (= []
           (compras-por-mes 10 lista-compras-teste)))))

(deftest total-gasto-no-mes-test
  (testing "Quando realizo filtro total de gasto no mês"
    (is (= 5416.76M
           (total-gasto-no-mes 8 lista-compras-teste))))

  (testing "Quando realizo filtro total de gasto no mês por mes que não existe compras"
    (is (= 0
           (total-gasto-no-mes 12 lista-compras-teste)))))

(deftest filtra-compras-por-valor-test
  (testing "Quando realizo filto por valor"
    (is (= [{:id              3,
             :data            (java-time/local-date 2022 2 3),
             :valor           123.0M,
             :estabelecimento "Alura",
             :categoria       "Educação",
             :cartao          99888773133333}
            {:id              5,
             :data            (java-time/local-date 2021 1 16),
             :valor           1.0M,
             :estabelecimento "Alura",
             :categoria       "Saúde",
             :cartao          76766789033312}
            {:id              6,
             :data            (java-time/local-date 2022 8 1),
             :valor           0.99M,
             :estabelecimento "Alura",
             :categoria       "Saúde",
             :cartao          1234123412341234}]
           (filtra-compras-por-valor lista-compras-teste 500 0)))))
