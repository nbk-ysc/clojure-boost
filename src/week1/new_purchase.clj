(ns week1.new-purchase
    (:require [week1.db :as l.db]))

(defn new-purchase
  "This function receive parameters from one purchase and return its maps"
  [date value establishment category card]
  {:date          (str date)
   :value         (bigdec value)
   :establishment (str establishment)
   :category      (str category)
   :card          (long card)})

;-------------------------------------------------------------------------------------------------------------------
;----------------------------------------------------EXTRA----------------------------------------------------------


;PRONTO - assoc em um map
(defn new-purchase-assoc-ldb
  "This function receive parameters from one purchase, assoc to the local database and return its maps"
  [date value establishment category card]
  (assoc l.db/purchase-map
    :date          (str date)
    :value         (bigdec value)
    :establishment (str establishment)
    :category      (str category)
    :card          (long card)))


;PRONTO - assoc-in em um vetor
(defn new-purchase-associn-vector-ldb
  "This function receive parameters from one purchase, assoc to a vector in local database and return its maps"
  [id date value establishment category card]
  (assoc-in l.db/purchases [id]
            {:date               date
             :value              value
             :establishment      establishment
             :category           category
             :card               card}))


;PRONTO - funcao do java que gera um id unico(uuid)
(defn uuid
  "This function creates a unique id that will be used for each purchase"
  []
  (.toString (java.util.UUID/randomUUID)))


;FALTA - fazer if para nao quebrar caso entrar numero inteiro no lugar de string para a funcao abaixo
(defn remove-blank-from-string [card]
  "This function removes all blank spaces from a string, will be used to remove the
   blank spaces from the card numbers string before convert to long"
  (->> card
       (#(clojure.string/split % #"\s"))
       (remove clojure.string/blank?)
       (clojure.string/join "")))


;PRONTO - assoc em um mapa com UUID
(defn new-purchase-with-uuid-assoc-map-ldb
  "This function receive parameters from one purchase, create one uuid and
   assoc to the local database and return its maps"
  [date value establishment category card]
  (assoc l.db/purchase-map (keyword (uuid)) {
                                             :date          (str date)
                                             :value         (bigdec value)
                                             :establishment (str establishment)
                                             :category      (str category)
                                             :card          (Long/valueOf (remove-blank-from-string card))}))


;FALTA - Estudar como transformar o udid em um inteiro, pois como o purchases é um vetor ele só aceita index int
(defn new-purchase-with-uuid-associn-vector-ldb
  "This function receive parameters from one purchase, create one uuid and
   assoc to the local database and return its maps"
  [date value establishment category card]
  (assoc l.db/purchase-map (keyword (uuid)) {
                                             :date          (str date)
                                             :value         (bigdec value)
                                             :establishment (str establishment)
                                             :category      (str category)
                                             :card          (Long/valueOf (remove-blank-from-string card))}))
