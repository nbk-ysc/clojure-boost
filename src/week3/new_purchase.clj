(ns week3.new-purchase
    (:use [clojure pprint])
    (:require [week3.buy-schema :as schema]
              [schema.core :as s]))


(s/defn new-purchase :- schema/ValidBuySchema
  "This function receive parameters from one purchase, valid its schema and return its maps"
  [date value establishment category card]
  {:date          date
   :value         value
   :establishment establishment
   :category      category
   :card          card})

;(pprint (new-purchase "2022-04-12" 10M "Restaurante" "Alimentação" 1000))