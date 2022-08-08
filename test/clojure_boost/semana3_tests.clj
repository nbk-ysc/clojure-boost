(ns clojure_boost.semana3_tests
  (:require [clojure.test :refer :all]
            [clojure-boost.testes :refer :all]
            [clojure-boost.compras :as compras]
            [clojure-boost.loja :as validacoes]))

(deftest total-gasto-test
  (let [lista-compras
        [{:data            "10-02-1990",
          :valor           100.0M,
          :estabelecimento "Posto de gasolina",
          :categoria       "Automóvel",
          :cartao          4321432143214321}
         {:data            "10-02-1991",
          :valor           200.0M,
          :estabelecimento "iFood",
          :categoria       "Alimentação",
          :cartao          4321432143214321}
         {:data            "10-02-1996",
          :valor           300.0M,
          :estabelecimento "Alura",
          :categoria       "Educação",
          :cartao          4321432143214321}]]
    (testing "Validando se o total gasto funciona"
      (is (= (compras/total-gasto lista-compras) 600.0M)))))


(deftest agrupa-gastos-por-categoria-test
  (let [lista-compras [{:data "10-02-2020",
                        :valor 100.0M,
                        :estabelecimento "Posto de gasolina",
                        :categoria "Automóvel",
                        :cartao 4321432143214321}
                       {:data "10-02-2021",
                        :valor 30000.0M,
                        :estabelecimento "BMW 320i",
                        :categoria "Automóvel",
                        :cartao 4321432143214321}
                       {:data "10-02-2021",
                        :valor 200.0M,
                        :estabelecimento "iFood",
                        :categoria "Alimentação",
                        :cartao 4321432143214321}
                       {:data "10-02-2022",
                        :valor 400.0M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartao 4321432143214321}
                       {:data "10-02-2019",
                        :valor 300.0M,
                        :estabelecimento "Alura",
                        :categoria "Educação",
                        :cartao 4321432143214321}
                       {:data "10-02-2017",
                        :valor 200.0M,
                        :estabelecimento "Escolinha do batatinha",
                        :categoria "Educação",
                        :cartao 4321432143214321}]]
    (testing "Validar que esteja somando os valores"
      (is (= {"Automóvel" 30100.0M, "Alimentação" 600.0M, "Educação" 500.0M}
             (compras/lista-todas-categorias lista-compras))))))


(deftest compra-valida?
  (let [compra-valida {:data            "23-10-1996",
                       :valor           10.0M,
                       :estabelecimento "Outback",
                       :categoria       "Alimentação",
                       :cartao          10000000000000000}]
    (testing "Compra válida"
      (is (= compra-valida
             (nova-compra-validate "23-10-1996", 10.0M, "Outback", "Alimentação", 10000000000000000)))))
  (testing "Compra com data inválida"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-data-formato-correto*"
                 (nova-compra-validate "23/10/1996", 10.0M, "Outback", "Alimentação", 10000000000000000))))
  (testing "Compra com data vazia"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-data-formato-correto*"
                          (nova-compra-validate "", 10.0M, "Outback", "Alimentação", 10000000000000000))))
  (testing "Compra com valor inválido não BigDecimal"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-valor-e-bigdecimal*"
                 (nova-compra-validate "23-10-1996", 10.0, "Outback", "Alimentação", 10000000000000000))))
  (testing "Compra com valor negativo"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-valor-e-bigdecimal*"
                 (nova-compra-validate "23-10-1996", -10.0, "Outback", "Alimentação", 10000000000000000))))
  (testing "Compra com valor zero"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-valor-e-bigdecimal*"
                          (nova-compra-validate "23-10-1996", 0, "Outback", "Alimentação", 10000000000000000))))
  (testing "Compra com estabelecimento vazio"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-mais-de-duas-letras-no-estabelecimento*"
                 (nova-compra-validate "23-10-1996", 10.0M, "", "Alimentação", 10000000000000000))))
  (testing "Compra com estabelecimento menos de 2 letras"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-mais-de-duas-letras-no-estabelecimento*"
                 (nova-compra-validate "23-10-1996", 10.0M, "O", "Alimentação", 10000000000000000))))
  (testing "Compra com Categoria inválida"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-categoria-correta*"
                 (nova-compra-validate "23-10-1996", 10.0M, "Outback", "Alimentação batata", 10000000000000000))))
  (testing "Compra com Categoria vazia"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cond-categoria-correta*"
                          (nova-compra-validate "23-10-1996", 10.0M, "Outback", "", 10000000000000000))))
  (testing "Compra com cartão inválido"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #".*cartao-correto*"
                 (nova-compra-validate "23-10-1996", 10.0M, "Outback", "Alimentação", 0)))))

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

;testes de compras
(deftest total-gasto-por-cartao
  (let [lista-compras [{:data "01-01-2022",
                        :valor 129.9M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartao 1234123412341234}
                       {:data "02-01-2022",
                        :valor 260.0M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartao 1234123412341234}
                       {:data "01-02-2022",
                        :valor 20.0M,
                        :estabelecimento "Cinema",
                        :categoria "Lazer",
                        :cartao 1234123412341234}
                       {:data "10-01-2022",
                        :valor 150.0M,
                        :estabelecimento "Show",
                        :categoria "Lazer",
                        :cartao 4321432143214321}
                       {:data "10-02-2022",
                        :valor 289.99M,
                        :estabelecimento "Posto de gasolina",
                        :categoria "Automóvel",
                        :cartao 4321432143214321}]]
    (testing "Total gasto de um cartão com valor correto"
      (is (= 409.9M
             (compras/total-gasto-por-cartao lista-compras 1234123412341234))))
  (testing "Total gasto de um cartão com valor incorreto"
    (is (not (= 409.9M
                (compras/total-gasto-por-cartao lista-compras 4321432143214321)))))))

(deftest total-gasto-no-mes
  (let [lista-compras [{:data "01-01-2022",
                        :valor 129.9M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartao 1234123412341234}
                       {:data "02-01-2022",
                        :valor 260.0M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartao 1234123412341234}
                       {:data "01-02-2022",
                        :valor 20.0M,
                        :estabelecimento "Cinema",
                        :categoria "Lazer",
                        :cartao 1234123412341234}
                       {:data "10-01-2022",
                        :valor 150.0M,
                        :estabelecimento "Show",
                        :categoria "Lazer",
                        :cartao 4321432143214321}
                       {:data "10-02-2022",
                        :valor 289.99M,
                        :estabelecimento "Posto de gasolina",
                        :categoria "Automóvel",
                        :cartao 4321432143214321}]]
    (testing "Total gasto no mês com valor correto"
      (is (= 539.9M
             (compras/total-gasto-no-mes lista-compras 01))))
    (testing "Total gasto no mês com valor incorreto"
      (is (not (= 539.9M
                  (compras/total-gasto-no-mes lista-compras 02)))))))


(deftest compras-com-filtro-maximo-e-minimo
  (let [assert-compra [{:data            "01-01-2022",
                        :valor           129.9M,
                        :estabelecimento "Outback",
                        :categoria       "Alimentação",
                        :cartao          1234123412341234}
                       {:data            "10-01-2022",
                        :valor           150.0M,
                        :estabelecimento "Show",
                        :categoria       "Lazer",
                        :cartao          4321432143214321}
                       {:data            "10-04-2022",
                        :valor           130.0M,
                        :estabelecimento "Drogaria",
                        :categoria       "Saúde",
                        :cartao          6655665566556655}
                       {:data            "10-03-2022",
                        :valor           100.0M,
                        :estabelecimento "Show de pagode",
                        :categoria       "Lazer",
                        :cartao          3939393939393939}]]
    (testing "Filtro com valor correto"
      (is (= assert-compra
             (compras/filtra-compras-valor 150.0 100.0))))
    (testing "Filtro com valor correto"
      (is (= ()
             (compras/filtra-compras-valor 1544440.0 104440.0))))))



