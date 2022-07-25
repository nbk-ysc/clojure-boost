(ns clojure-boost.total-spent
  (:require [clojure-boost.db :as l.db]))

(defn total-spent
  "This function will receive and sum all purchases from one card"
  [card]
  (bigdec (reduce + (map :value card)))
  )

;use local database purchases-card-1 as parameter
;(println (total-spent l.db/purchases-card-1))


