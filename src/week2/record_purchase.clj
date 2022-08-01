(ns week2.record-purchase
  (:use [clojure pprint])
  (:require [week2.db :as l.db]))

(defrecord Purchase
  "This record its a model for one purchase"
  [^Long id ^String date ^BigDecimal value ^String establishment ^String category ^Long card ])




