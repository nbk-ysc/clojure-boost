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

(->> lista/lista-compras
     (group-by :categoria)
     (map total-categoria)
     (into {})
     println)
