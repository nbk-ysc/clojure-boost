(ns clojure-boost.compra-client-test
  (:require [clojure.test :refer :all]
            [clojure-boost.week3.schemata.compra_client :refer :all]
            [clojure-boost.week1.agrupados-by-categoria :refer :all]
            [clojure-boost.week1.lista-compras :refer :all]
            [schema.core :as s]
            [clojure-boost.week1.buscar-by-mes :refer :all]))


(deftest valida-categoria-test
  (testing "testing category validate"
    (is (= true (valida-categoria "Casa")))
    (is (= false (valida-categoria "farmacia")))))

(deftest total-gasto-test
  (testing "Validar funcao total gastos retornando 600"
    (is (= 600 (total-gasto trescompras)))
    (is (= -600 (total-gasto trescompras)))
    (is (= "" (total-gasto trescompras)))
    (is (= 1000 (total-gasto trescompras)))))

(deftest calcula-e-lista-categoria-test
  (testing "Validar funcao calculo e categoria"
    (is (= {"Lazer" 500}
           (calcula-e-lista-categoria
             [{:valor     300,
               :categoria "Lazer",}
              {:valor     300,
               :categoria "Lazer",}])))
    (is (= {"Lazer" -500}
           (calcula-e-lista-categoria
             [{:valor     300,
               :categoria "Lazer",}
              {:valor     300,
               :categoria "Lazer",}])))
    (is (= {"Lazer" 600}
           (calcula-e-lista-categoria
             [{:valor     300,
               :categoria "Lazer",}
              {:valor     300,
               :categoria "Lazer",}])))
    (is (= {"Lazer" 499}
           (calcula-e-lista-categoria
             [{:valor     300,
               :categoria "Lazer",}
              {:valor     300,
               :categoria "Lazer",}])))
    (is (= {"Lazer" 601}
           (calcula-e-lista-categoria
             [{:valor     300,
               :categoria "Lazer",}
              {:valor     300,
               :categoria "Lazer",}])))))