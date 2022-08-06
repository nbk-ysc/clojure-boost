(ns clojure-boost.schemas.compras-test
  (:require [clojure.test :refer :all]
            [clojure-boost.schemas.compras :refer :all]
            [java-time :as java-time]
            [schema.core :as s]))

(s/set-fn-validation! true)


(deftest CompraSchema-test
  (testing "Quando é uma compra válida com id "
    (let [compra-valida {:id              1,
                         :data            (java-time/local-date 2022 5 9),
                         :valor           4412.33M,
                         :estabelecimento "Alura",
                         :categoria       "Alimentação",
                         :cartao          1234123412341234}]
      (is (= compra-valida
             (s/validate CompraSchema compra-valida)))))

  (testing "Quando é uma compra válida sem keyword id"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bid missing-required-key\b"
                          (s/validate CompraSchema
                                      {:data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Alimentação",
                                       :cartao          1234123412341234}))))

  (testing "Quando é uma compra válida id nulo"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bjava.lang.Long nil\b"
                          (s/validate CompraSchema
                                      {:id              nil
                                       :data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Alimentação",
                                       :cartao          1234123412341234}))))

  (testing "Quando data da compra não é válida"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bdata-menor-or-igual-data-atual?\b"
                          (s/validate CompraSchema
                                      {:id              1,
                                       :data            nil,
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Alimentação",
                                       :cartao          1234123412341234}))))

  (testing "Quando valor da compra não é valido"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bvalor-valido?\b"
                          (s/validate CompraSchema
                                      {:id              1,
                                       :data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33,
                                       :estabelecimento "Alura",
                                       :categoria       "Alimentação",
                                       :cartao          1234123412341234}))))

  (testing "Quando estabelecimento da compra não é valido"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bestabelecimento-valido?\b"
                          (s/validate CompraSchema
                                      {:id              1,
                                       :data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "A",
                                       :categoria       "Alimentação",
                                       :cartao          1234123412341234}))))

  (testing "Quando categoria da compra não é válida"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bcategoria\b"
                          (s/validate CompraSchema
                                      {:id              1,
                                       :data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Laze",
                                       :cartao          1234123412341234}))))

  (testing "Quando cartão da compra não é valido"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\blimite-numero-do-cartao-valido?\b"
                          (s/validate CompraSchema
                                      {:id              1,
                                       :data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Lazer",
                                       :cartao          -1})))))

(deftest CompraSchemaVetor-test
  (testing "Quando é um vetor de compras válidas"
    (let [vetor-compras [{:id              1,
                          :data            (java-time/local-date 2022 5 9),
                          :valor           44142.33M,
                          :estabelecimento "Alura",
                          :categoria       "Alimentação",
                          :cartao          1234123412341234}
                         {:id              2,
                          :data            (java-time/local-date 2022 5 9),
                          :valor           44152.33M,
                          :estabelecimento "Alura",
                          :categoria       "Alimentação",
                          :cartao          1234123412341234}]]
      (is (= vetor-compras
             (s/validate CompraSchemaVetor vetor-compras)))))

  (testing "Quando não é vetor de compras válidas"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bsequential?\b"
                          (s/validate CompraSchemaVetor
                                      {:id              1,
                                       :data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Lazer",
                                       :cartao          -1})))))

(deftest CompraSchemaSemId-test
  (testing "Quando schema compra não tem id"
    (let [compra-sem-id {:data            (java-time/local-date 2022 5 9),
                         :valor           44142.33M,
                         :estabelecimento "Alura",
                         :categoria       "Alimentação",
                         :cartao          1234123412341234}]
      (is (= compra-sem-id
             (s/validate CompraSchemaSemId compra-sem-id)))))

  (testing "Quando schema compra tem id"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bid disallowed-key\b"
                          (s/validate CompraSchemaSemId
                                      {:id              1,
                                       :data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Lazer",
                                       :cartao          44555})))))

(deftest CompraSchemaComIdNulo-test
  (testing "Quando schema compra com id nulo"
    (let [compra-com-id-nulo {:id              nil
                              :data            (java-time/local-date 2022 5 9),
                              :valor           44142.33M,
                              :estabelecimento "Alura",
                              :categoria       "Alimentação",
                              :cartao          1234123412341234}]
      (is (= compra-com-id-nulo
             (s/validate CompraSchemaComIdNulo compra-com-id-nulo)))))

  (testing "Quando schema compra com id preenchido"
    (let [compra-com-id {:id              1
                         :data            (java-time/local-date 2022 5 9),
                         :valor           44142.33M,
                         :estabelecimento "Alura",
                         :categoria       "Alimentação",
                         :cartao          1234123412341234}]
      (is (= compra-com-id
             (s/validate CompraSchemaComIdNulo compra-com-id)))))

  (testing "Quando schema compra sem keyword id"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bid missing-required-key\b"
                          (s/validate CompraSchemaComIdNulo
                                      {:data            (java-time/local-date 2022 5 9),
                                       :valor           4412.33M,
                                       :estabelecimento "Alura",
                                       :categoria       "Lazer",
                                       :cartao          44555})))))

(deftest TotalGastoPorCategoria-test
  (testing "Quando retorno total por categoria é valido válida"
    (let [categoria {"Alimentação" 5405.46M,
                     "Educação"    309222.8844M,
                     "Saúde"       1005.43M}]
      (is (= categoria
             (s/validate TotalGastoPorCategoria categoria)))))

  (testing "Quando categoria não é válida"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bEducação\b"
                          (s/validate TotalGastoPorCategoria
                                      {"Alimentação" 5405.46M,
                                       "Educacao"    309222.8844M,
                                       "Saúde"       1005.43M}))))

  (testing "Quando valor não é valido"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bvalor-valido?\b"
                          (s/validate TotalGastoPorCategoria
                                      {"Alimentação" 5405.46M,
                                       "Educação"    309222.8844,
                                       "Saúde"       1005.43M})))))

(deftest AgrupaCategoria-test
  (testing "Quando retorno é valido"
    (let [agrupado-categoria {"Alimentação"
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
      (is (= agrupado-categoria
             (s/validate AgrupaCategoria agrupado-categoria)))))

  (testing "Quando agrupado por categoria não é valido"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bsequential?\b"
                          (s/validate AgrupaCategoria
                                      {"Alimentação" 5405.46M,
                                       "Educação"    309222.8844,
                                       "Saúde"       1005.43M})))))
