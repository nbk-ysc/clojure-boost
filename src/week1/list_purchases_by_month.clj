(ns week1.list-purchases-by-month
  (:require [week1.db :as l.db]
            [clojure.string :as str]))

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
  (->>
    purchase-list
    (filter #(= month (get-month (:date %))) )))



;-------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------Before Refactor------------------------------------------------


;it was working whitout threading last

;(defn purchases-by-month
;  "This function returns all purchases in the selected month"
;  [month purchase-list]
;  (filter #(= month (get-month (:date %))) purchase-list))

