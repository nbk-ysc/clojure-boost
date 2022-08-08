(ns clojure-boost.schemas.base-test
  (:require [clojure.test :refer :all]
            [clojure-boost.schemas.base :refer :all]
            [schema.core :as s]))

(s/set-fn-validation! true)


(deftest Atom-test
  (testing "Quando atomo válido"
    (let [atomo (atom [])]
      (is (= atomo
             (s/validate Atom atomo)))))
  (testing "Quando não é um atomo"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\batom?\b"
                          (s/validate Atom 10)))))

(deftest SeqInt-test
  (testing "Quando é uma sequência contendo itens inteiro"
    (let [seq-valida '(0 9 2)]
      (is (= seq-valida
             (s/validate SeqInt seq-valida)))))

  (testing "Quando não é uma sequência"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bseq-int?\b"
                          (s/validate SeqInt [10]))))

  (testing "Quando é uma sequência não contendo itens inteiro"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\bseq-int?\b"
                          (s/validate SeqInt '(10 "30" 10))))))

(deftest LocalDate-test
  (testing "Quando é um localDate válido"
    (let [data-valida (java-time/local-date)]
      (is (= data-valida
             (s/validate LocalDate data-valida)))))

  (testing "Quando é não é um localDate"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"\blocal-date?\b"
                          (s/validate LocalDate (java-time/instant))))))
