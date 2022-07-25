(ns clojure-boost.list-purchases-by-month
  (:require [clojure-boost.db :as l.db]))


(defn get-month
  "This function get a month in a given date"
  [date]
  (->
    (str/split date #"-")
    (get 1)
    (Integer/parseInt)))

(defn purchases-by-month
  "This function returns all purchases in the selected month"
  [month purchase-list]
  (filter #(= month (get-month (:date %))) purchase-list))

