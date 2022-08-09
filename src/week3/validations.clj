(ns week3.validations
  (:use [clojure pprint])
  (:require [week3.db :as l.db]
            [week1.list-purchases-by-month :as week1]
            [schema.core :as s]
            [clojure.string :as str]))

(s/set-fn-validation! true)


(s/defn valid-value?
  "This function valid if a value is positive and bigdecimal"
  [value :- BigDecimal]
  (pos? value))

(s/defn valid-establishment?
  "This function valid if a establishment have more than 2 characters, therefore are valid"
  [establishment :- s/Str]
  (let [characters (count establishment)]
       (>= characters 2)))

(s/defn valid-category?
  "This function valid if the given category is in the valid list"
  [category :- s/Str]
  (let [valid-categorys #{"Alimentação"
                           "Automóvel"
                           "Casa"
                           "Educação"
                           "Lazer"
                           "Saúde"}]
    (contains? valid-categorys category)))

(defn valid-card?
  "This function valid the card number"
  [card]
  (and (>= card 0)
       (<= card 10000000000000000)))

(s/defn valid-date?
  "This function valid if String is in yyyy-mm-dd format"
  [date :- s/Str]
  (re-matches #"\d{4}-\d{2}-\d{2}" date))



;===================================================Before Refactor========================================================
;==========================================================================================================================


;Was using "if" condition to check if the value was decimal and pos
;(defn valid-value?
;  "This function valid if a value is positive and bigdecimal"
;  [value]
;  (->
;    (if (decimal? value)
;      (pos? value))))