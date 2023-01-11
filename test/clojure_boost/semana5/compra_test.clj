(ns clojure-boost.semana5.compra-test
  (:require [semana5.core.compra :refer :all]
            [clojure.test :refer :all]))

(deftest total-gasto-test
  (testing "que soma todos os valores das compras"
    (let [compras [{:id 0,
                    :data "2022-12-15"
                    :valor 100.0
                    :estabelecimento "Parque"
                    :categoria "Lazer"
                    :cartao 1010010101001010},
                   {:id 1,
                    :data "2022-12-16"
                    :valor 200.00
                    :estabelecimento "Cinema"
                    :categoria "Lazer"
                    :cartao 1010010101001010},
                   {:id 2,
                    :data "2022-12-27"
                    :valor 300.00
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 1010010101001010}]]
      (is (= 600.0, (total-gasto compras))))))

(deftest total-gasto-por-categoria-test
  (testing "que soma todos os valores das compras por categoria"
    (let [compras [{:id 0,
                    :data "2022-12-15"
                    :valor 100.0
                    :estabelecimento "Parque"
                    :categoria "Lazer"
                    :cartao 1010010101001010},
                   {:id 1,
                    :data "2022-12-16"
                    :valor 200.00
                    :estabelecimento "Cinema"
                    :categoria "Lazer"
                    :cartao 1010010101001010},
                   {:id 2,
                    :data "2022-12-27"
                    :valor 300.00
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 1010010101001010},
                   {:id 3,
                    :data "2022-12-27"
                    :valor 300.00
                    :estabelecimento "Mercado"
                    :categoria "Alimentação"
                    :cartao 1010010101001010}]]
      (is (= [{"Lazer" 300.0} {"Alimentação" 600.0}] (agrupa-compras-por-categoria compras))))))