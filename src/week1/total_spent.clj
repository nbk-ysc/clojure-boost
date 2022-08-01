(ns week1.total-spent
  (:require [week1.db :as l.db]))


(defn purchases-by-card
  "This function will receive a card and the purchase list, and filter all purchases from this card "
  [card purchase-list]
  (->>
      purchase-list
      (filter #(= card (:card %)))))

(defn total-spent-by-card
  "This function will receive a card and the purchase list, and sum all purchases from this card "
  [card purchase-list]
  (->>
    (purchases-by-card card purchase-list)
    (map :value)
    (reduce +)
    (bigdec)
    ))

;-------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------Before Refactor------------------------------------------------


;it was working whitout threading last
; also it was doing the filter and sum in the same function

;(defn total-spent-by-card
;  "This function will receive a card and the purchase list, and sum all purchases from this card "
;  [card purchase-list]
;  (bigdec (reduce + (map :value (filter #(= card (:card %)) purchase-list))))
;  )


