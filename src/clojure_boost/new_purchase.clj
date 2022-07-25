(ns clojure-boost.new-purchase
    (:require [clojure-boost.db :as l.db]))


(defn new-purchase
  "This function receive parameters from one purchase, assoc to the local database and return its maps"
  [date value establishment category card]
  (assoc l.db/purchase1
    :date          (str date)
    :value         (bigdec value)
    :establishment (str establishment)
    :category      (str category)
    :card          (long card)))

;-------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------EXTRA----------------------------------------------------------

(defn uuid
  "This function creates a unique id that will be used for each purchase"
  []
  (.toString (java.util.UUID/randomUUID)))


;falta fazer if para nao quebrar caso entrar numero inteiro no lugar de string para a funcao abaixo
(defn remove-blank-from-string [card]
  "This function removes all blank spaces from a string, will be used to remove the
   blank spaces from the card numbers string before convert to long"
  (->> card
       (#(clojure.string/split % #"\s"))
       (remove clojure.string/blank?)
       (clojure.string/join "")))

(defn new-purchase-with-uuid
  "This function receive parameters from one purchase, create one uuid and
   assoc to the local database and return its maps"
  [date value establishment category card]
  (assoc l.db/purchase (keyword (uuid))
                       {
                        :date          (str date)
                        :value         (bigdec value)
                        :establishment (str establishment)
                        :category      (str category)
                        :card          (Long/valueOf (remove-blank-from-string card))}))









