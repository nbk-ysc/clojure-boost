(ns week2.record-purchase
  (:use [clojure pprint]))

(defrecord Purchase
  "This record its a model for one purchase"
  [^Long id ^String date ^BigDecimal value ^String establishment ^String category ^Long card ])




