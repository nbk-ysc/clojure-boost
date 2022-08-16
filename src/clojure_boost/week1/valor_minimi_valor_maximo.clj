(ns clojure-boost.week1.valor-minimi-valor-maximo
  (:require [clojure-boost.week1.lista-compras :as lista]))


(let [minimo 100
      maximo 1000])


(defn compras-filtro [minimo maximo compras]
  (->> compras
       (filter (fn [compra]
                 (and (>= (:valor compra) minimo)
                      (<= (:valor compra) maximo))))))

(println (compras-filtro 100 1000 lista/lista-compras))Ú