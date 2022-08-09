(ns clojure-boost.tests-loja
  (:require [clojure.test :refer :all]
            [clojure-boost.loja :as validacoes]))

(deftest gera-id?
  (let [id-mapa 3
        id-mapa-mais-um (+ id-mapa 1)
        id-mapa-menos-um (- id-mapa 1)
        compra [{:id              id-mapa
                 :data            "23-10-1996",
                 :valor           10.0M,
                 :estabelecimento "Outback",
                 :categoria       "Alimentação",
                 :cartao          10000000000000000}]]
    (testing "Deve gerar id com 1+ do id da compra"
      (is (= (validacoes/gera-id compra) id-mapa-mais-um)))
    (testing "Id não deve ser o mesmo id da compra"
      (is (not (= (validacoes/gera-id compra) id-mapa))))
    (testing "Não deve gerar id com id inferior ao id da compra"
      (is (not (= (validacoes/gera-id compra) id-mapa-menos-um))))))

(deftest insere-compra-de-um-atomo
  (let [lista-compra-valida [{:id 1,
                              :data "10-10-1996",
                              :valor 100.0M,
                              :estabelecimento "Outback",
                              :categoria "Alimentação",
                              :cartao 1000000000000}]
        lista-compra {:id 1
                      :data "10-10-1996",
                      :valor 100.0M,
                      :estabelecimento "Outback",
                      :categoria "Alimentação",
                      :cartao 1000000000000}
        repositorio-compra (atom [])]
    (testing "Inserindo compra válida"
      (is (= lista-compra-valida
             (validacoes/insere-compra! lista-compra repositorio-compra))))))


(deftest lista-compras-de-um-atomo?
  (let [atomo-de-teste (atom [{:id 2,
                               :data "10-10-1996",
                               :valor 100.0M,
                               :estabelecimento "Outback",
                               :categoria "Alimentação",
                               :cartao 1000000000000}
                              {:id 3,
                               :data "09-11-1990",
                               :valor 100.0M,
                               :estabelecimento "Outback",
                               :categoria "Casa",
                               :cartao 1000000000000}])]
    (testing "Trazer nil na lista de atomos"
      (is (nil?
            (validacoes/lista-compras! atomo-de-teste))))))

(deftest exclui-compras-de-um-atomo
  (let [lista-compras-atomo (atom [{:id 2,
                                    :data "10-10-1996",
                                    :valor 100.0M,
                                    :estabelecimento "Outback",
                                    :categoria "Alimentação",
                                    :cartao 1000000000000}
                                   {:id 3,
                                    :data "09-11-1990",
                                    :valor 100.0M,
                                    :estabelecimento "Outback",
                                    :categoria "Casa",
                                    :cartao 1000000000000}])
        lista-compras-validacao [{:id 3,
                                  :data "09-11-1990",
                                  :valor 100.0M,
                                  :estabelecimento "Outback",
                                  :categoria "Casa",
                                  :cartao 1000000000000}]]
    (testing "Excluindo uma compra de um átomo"
      (is (= lista-compras-validacao
             (validacoes/exclui-compra! lista-compras-atomo 2))))
    (testing "Excluindo uma compra de um átomo que não existe"
      (is (= lista-compras-validacao
             (validacoes/exclui-compra! lista-compras-atomo 20))))))