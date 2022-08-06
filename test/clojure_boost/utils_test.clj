(ns clojure-boost.utils-test
  (:require [clojure.test :refer :all]
            [clojure-boost.utils :refer :all]
            [schema.core :as s]))


(s/set-fn-validation! true)


(deftest id-maximo-da-lista-incrementado-test
  (testing "Quando enviado sequência"
    (let [sequencia '(99, 2, 0, 101, 4, -1, 30, 99, 03, 12)]
      (is (= 102 (id-maximo-da-lista-incrementado sequencia)))))

  (testing "Quando enviado sequência vazia retorna 1"
    (is (= 1 (id-maximo-da-lista-incrementado '())))))

(deftest gera-id-test
  (testing "Quando vetor de coleções de ids"
    (let [vetor-ids [{:id 10}, {:id 99}, {:id 199}, {:id 0}, {:id -2134}, {:id 584}]]
      (is (= 585 (gera-id vetor-ids)))))

  (testing "Quando enviado vetor de coleções com apenas 1 ids"
    (let [vetor-ids [{:id 9991}]]
      (is (= 9992 (gera-id vetor-ids)))))

  (testing "Quando enviado vetor vazio, retorna valor 1"
    (is (= 1 (gera-id [])))))
