(ns clojure-boost.agrupados-by-categoria
  (:require [clojure-boost.lista-compras :as lista]))

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
