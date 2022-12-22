(ns clojure-boost.compra-test
  (:require [clojure-boost.funcoes :refer [total-gasto]]
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
