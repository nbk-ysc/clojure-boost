(ns clojure-boost.calcular-total_cartao
  (:require [clojure-boost.lista-compras :as calculo]))

(defn total-de-gastos
  [cartao compras]
  (let [cartao-filtrado (calculo/filtrando-cartao cartao compras)]
    (->> cartao-filtrado
         (reduce +))))

(println "Meu total de gastos com o cartão é R$"(total-de-gastos 1234123412341234 calculo/lista-compras))

