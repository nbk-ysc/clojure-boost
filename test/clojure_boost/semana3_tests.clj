(ns clojure_boost.semana3_tests
  (:require [clojure.test :refer :all]
            [clojure-boost.testes :refer :all]
            [clojure-boost.compras :as compras]
            [clojure-boost.loja :as validacoes]))

(deftest total-gasto-test
  (let [lista-compras
        [{:data            "2022/02/10",
          :valor           100.0,
          :estabelecimento "Posto de gasolina",
          :categoria       "Automóvel",
          :cartao          4321432143214321}
         {:data            "2022/02/20",
          :valor           200.0,
          :estabelecimento "iFood",
          :categoria       "Alimentação",
          :cartao          4321432143214321}
         {:data            "2022/03/01",
          :valor           300.0,
          :estabelecimento "Alura",
          :categoria       "Educação",
          :cartao          4321432143214321}]]
    (testing "Validando se o total gasto funciona"
      (is (= (compras/total-gasto lista-compras) 600.0)))))


(deftest agrupa-gastos-por-categoria-test
  (let [lista-compras [{:data "2022/02/10",
                        :valor 100.0,
                        :estabelecimento "Posto de gasolina",
                        :categoria "Automóvel",
                        :cartao 4321432143214321}
                       {:data "2022/02/10",
                        :valor 30000.0,
                        :estabelecimento "BMW 320i",
                        :categoria "Automóvel",
                        :cartao 4321432143214321}
                       {:data "2022/02/20",
                        :valor 200.0,
                        :estabelecimento "iFood",
                        :categoria "Alimentação",
                        :cartao 4321432143214321}
                       {:data "2022/02/20",
                        :valor 400.0,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartao 4321432143214321}
                       {:data "2022/03/01",
                        :valor 300.0,
                        :estabelecimento "Alura",
                        :categoria "Educação",
                        :cartao 4321432143214321}
                       {:data "2022/03/01",
                        :valor 200.0,
                        :estabelecimento "Escolinha do batatinha",
                        :categoria "Educação",
                        :cartao 4321432143214321}]]
    (testing "Validar que esteja somando os valores"
      (is (= {"Automóvel" 30100.0, "Alimentação" 600.0, "Educação" 500.0}
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

;(deftest lista-compras-de-um-atomo?
;  (let [atomo-de-teste (atom [{:id 2,
;                               :data "10-10-1996",
;                               :valor 100.0M,
;                               :estabelecimento "Outback",
;                               :categoria "Alimentação",
;                               :cartao 1000000000000}
;                              {:id 3,
;                               :data "09-11-1990",
;                               :valor 100.0M,
;                               :estabelecimento "Outback",
;                               :categoria "Casa",
;                               :cartao 1000000000000}])
;        atomo-valida {:id 2,
;                       :data "10-10-1996",
;                       :valor 100.0M,
;                       :estabelecimento "Outback",
;                       :categoria "Alimentação",
;                       :cartao 1000000000000}
;                      {:id 3,
;                       :data "09-11-1990",
;                       :valor 100.0M,
;                       :estabelecimento "Outback",
;                       :categoria "Casa",
;                       :cartao 1000000000000}]
;    (testing "Deve trazer uma lista com um atomo"
;      (is (= atomo-valida
;             (validacoes/lista-compras! atomo-de-teste))))))

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
             (compras/filtra-compras-valor 150.0 100.0))))))



