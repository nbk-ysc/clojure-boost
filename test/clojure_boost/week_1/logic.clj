(ns clojure-boost.week-1.logic
  (:require [clojure.test :refer :all]
            [clojure-boost.week_1.logic :refer :all]
            [schema.core :as s]))

(s/set-fn-validation! true)

(deftest total-gasto-test
  (testing "Calcula o total de gastos de algumas compras"
    (is (= (total-gasto [{:ID              18,
                          :data            "2022-04-01",
                          :valor           200.00M,
                          :estabelecimento "Oficina",
                          :categoria       "Automóvel",
                          :cartao          3939393939393939}
                         {:ID              19,
                          :data            "2022-04-10",
                          :valor           300.00M,
                          :estabelecimento "Alura",
                          :categoria       "Automóvel",
                          :cartao          3939393939393939}
                         {:ID              20,
                          :data            "2022-04-10",
                          :valor           100.00M,
                          :estabelecimento "LM",
                          :categoria       "Saúde",
                          :cartao          1234123412341234}]) 600.00M)))
  (testing "Calcula o total quando minha coleção de compras está vazia"
    (is (= (total-gasto []) 0)))
  (testing "Calcula o total quando minha coleção de compras possui uma única compra"
    (is (= (total-gasto [{:ID              18,
                          :data            "2022-04-01",
                          :valor           200.00M,
                          :estabelecimento "Oficina",
                          :categoria       "Automóvel",
                          :cartao          3939393939393939}]) 200.00M))))

;---------------------------------------------------------------------------------------------------------------

(deftest agrupar-por-categoria-test
  (testing "Validar se a funcao traz as compras por categoria - sucesso"
    (is (= (agrupar-por-categoria [{:ID              18,
                                    :data            "2022-04-01",
                                    :valor           200.00M,
                                    :estabelecimento "Oficina",
                                    :categoria       "Automóvel",
                                    :cartao          3939393939393939}
                                   {:ID              19,
                                    :data            "2022-04-10",
                                    :valor           300.00M,
                                    :estabelecimento "Alura",
                                    :categoria       "Automóvel",
                                    :cartao          3939393939393939}
                                   {:ID              20,
                                    :data            "2022-04-10",
                                    :valor           100.00M,
                                    :estabelecimento "LM",
                                    :categoria       "Saúde",
                                    :cartao          1234123412341234}]) {"Automóvel" 500.00M
                                                                          "Saúde"     100.00M})))
  (testing "Validar uma coleção vazia - sucesso"
    (is (= (agrupar-por-categoria []) {}))))

;---------------------------------------------------------------------------------------------------------------



