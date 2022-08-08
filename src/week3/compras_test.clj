(ns week3.compras-test
  (:require [clojure.test :refer :all]
            [week3.compras-functions :refer :all]
            [week3.mock :as mock]))

(deftest test-total-compras
  (testing "Testando total de compras de um mês"
    (is (= 600 (week3.compras-functions/total-compras-mes mock/compras 01)))
    (is (not (= 600 (week3.compras-functions/total-compras-mes mock/compras 00))))
    (is (not (= 600 (week3.compras-functions/total-compras-mes mock/compras-sem-valor 01))))
    ))

(deftest test-agrupa-categoria
  (testing "Testando agrupa categoria")
  (is (= (contains? (week3.compras-functions/total-compras-grupo mock/compras-agrupar) {:categoria :compras})))
  (is (not (contains? (week3.compras-functions/total-compras-grupo mock/compras-sem-categoria) {:categoria :compras})))
  (is (not (contains? (week3.compras-functions/total-compras-grupo mock/compras-agrupar-sem-valor) {:categoria :compras})))
  )

(deftest test-filtra-cartao-mes
  (testing "Testando o filtro de compras por cartão de credito")
  (is (= 600 (week3.compras-functions/total-compras-cartao mock/compras "1234 1234 1234 1234")))
  (is (not (= 600 (week3.compras-functions/total-compras-cartao mock/compras "1234 1234 1234 5678"))))
  (is (not (= 600 (week3.compras-functions/total-compras-cartao mock/compras "a"))))
  (is (not (= 600 (week3.compras-functions/total-compras-cartao mock/compras nil))))
  )

