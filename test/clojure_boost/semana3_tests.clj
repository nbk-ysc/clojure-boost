(ns clojure_boost.semana3_tests
  (:require [clojure.test :refer :all]
            [clojure-boost.testes :refer :all]
            [clojure-boost.compras :as compras]))

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
                 (nova-compra-validate "23-10-1996", 10.0M, "Outback", "Alimentação", 0))))















