(ns clojure-boost.list-purchases
    (:require [clojure-boost.db :as l.db]))


(defn list-purchases
    "This function will return a map with all the purchases from the local database"
    []
    l.db/purchases)
