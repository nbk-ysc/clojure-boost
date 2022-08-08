(ns clojure-boost.week1.agrupados-by-categoria
  (:require [clojure-boost.week1.lista-compras :as lista]))

(defn calcula-total
  [lista-compras]
  (->> lista-compras
       (map :valor)
       (reduce +)))

(defn total-categoria
  [[categoria valor]]
  [categoria (calcula-total valor)])

(defn calcula-e-lista-categoria [lista-compras]
  (->> lista-compras
       (group-by :categoria)
       (map total-categoria)
       (into {})))

(calcula-e-lista-categoria lista/lista-compras)