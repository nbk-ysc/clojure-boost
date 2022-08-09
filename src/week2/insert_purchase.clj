(ns week2.insert-purchase
  (:use [clojure pprint])
  (:require [week2.record-purchase :as r.purchase]))

(def purchase-no-id
  (r.purchase/map->Purchase {:date "2022-09-18" :value 22.30 :establishment "Carrefour" :category " Alimentação" :card 1234512345}))

(defn define-id
  "This function will check the purchase history, if its empty will give id the value 1. if not empty, will get the id with higher value and sum 1"
  [purchase-history]
  (if-not (empty? purchase-history)
    (+ 1 (apply max (map :id purchase-history)))
    1))

(defn assoc-id
  "This function will assoc a id to one purchase"
  [purchase purchase-history]
  (assoc purchase :id (define-id purchase-history)))

(defn insert-purchase
  "This function will insert one purchase in the local database"
  [purchase purchase-history]
    (let [purchase (assoc-id purchase purchase-history)]
      (conj purchase-history purchase)))

(defn insert-purchase!
  "This function insert one purchase into a atom"
  [purchase purchase-history]
    (swap! purchase-history conj purchase))

(defn list-atom
  "this function list a atom"
  [purchase-history]
  (pprint purchase-history))



