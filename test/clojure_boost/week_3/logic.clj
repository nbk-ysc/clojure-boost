(ns clojure-boost.week-3.logic
  (:require [clojure.test :refer :all]
            [clojure-boost.week_3.logic :refer :all]
            [schema.core :as s]
            [java-time :as jt]))

(s/set-fn-validation! true)


(deftest nova-compra-test
  (testing "Validando que a função nova-compra devolve uma compra valida"
    (let [validacao-schema (s/validate CompraSchemaSemId {:data            (jt/local-date "2022-05-09")
                                                          :valor           100M
                                                          :categoria       "Casa"
                                                          :estabelecimento "Amazon"
                                                          :cartao          1111222233334444})]
      (is (= validacao-schema {:data            (jt/local-date "2022-05-09")
                               :valor           100M
                               :categoria       "Casa"
                               :estabelecimento "Amazon"
                               :cartao          1111222233334444}))))

  (testing "Validar uma compra com data inválida"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bdata-invalida\b"
                          (s/validate CompraSchema
                                      {:ID              0
                                       :data            nil
                                       :valor           100M
                                       :categoria       "Casa"
                                       :estabelecimento "Amazon"
                                       :cartao          1111222233334444}))))

  (testing "Validar um valor negativo"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bnao-eh-bigdec-positivo\b"
                          (s/validate CompraSchema
                                      {:ID              0
                                       :data            (jt/local-date "2022-05-09")
                                       :valor           -100M
                                       :categoria       "Casa"
                                       :estabelecimento "Amazon"
                                       :cartao          1111222233334444}))))

  (testing "Validar um estabelecimento com string vazia"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bnumero-de-caracteres-inferior-a-dois\b"
                          (s/validate CompraSchema
                                      {:ID              0
                                       :data            (jt/local-date "2022-05-09")
                                       :valor           100M
                                       :categoria       "Casa"
                                       :estabelecimento ""
                                       :cartao          1111222233334444}))))

  (testing "Validar uma categoria inválida"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bcategoria-nao-existe\b"
                          (s/validate CompraSchema
                                      {:ID              0
                                       :data            (jt/local-date "2022-05-09")
                                       :valor           100M
                                       :categoria       "Casa do meu tio"
                                       :estabelecimento "Amazon"
                                       :cartao          1111222233334444}))))

  (testing "Validar uma cartão com númro inválido"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bnumeracao-incorreta\b"
                          (s/validate CompraSchema
                                      {:ID              0
                                       :data            (jt/local-date "2022-05-09")
                                       :valor           100M
                                       :categoria       "Casa do meu tio"
                                       :estabelecimento "Amazon"
                                       :cartao          -1111222233334444})))))

;--------------------------------------------------------------------------------------------------------------
