(ns clojure-boost.week1.calcular-total-mes
  (:require [clojure-boost.week1.buscar-by-mes :as mes]
            [clojure-boost.week1.lista-compras :as lista]))

(defn total-de-gasto-no-mes
  [mes lista]
  (let [filtrando (mes/lista-compras-mes lista mes)]
    (->> filtrando
         (map :valor)
         (reduce +))))

; Com pair programing com o Filipe, trouxemos uma discussão sobre defn padrões ou certo e errado, existe para o clojue??
;(defn mes-da-data [data]
;  (-> (clojure.string/split data #"-")
;      (get 1)
;      (Integer/parseInt)))
;
;(defn lista-compras-mes
;  [lista-compras mes]
;  (->> lista-compras
;       (filter #(= mes (mes-da-data (:data %))))))
;
;(defn total-de-gasto-no-mes
;  [mes lista]
;  (->> lista
;       (filter #(= mes (mes-da-data (:data %))))
;       (map :valor)
;       (reduce +)))

(println "Meu total de gastos com o cartão no mês é R$" (total-de-gasto-no-mes 04 lista/lista-compras))





