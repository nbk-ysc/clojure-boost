(ns clojure-boost.week1.buscar-by-mes
  (:require [clojure-boost.week1.lista-compras :as lista]))


(defn mes-da-data [data]
  (-> (clojure.string/split data #"-")
      (get 1)
      (Integer/parseInt)))

(defn lista-compras-mes
  [lista-compras mes]
  (->> lista-compras
       (filter #(= mes (mes-da-data (:data %))))))

(println "Buscando compra no mês"(lista-compras-mes lista/lista-compras 01))