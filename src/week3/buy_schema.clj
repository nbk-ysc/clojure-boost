(ns week3.buy-schema
  (:use [clojure pprint])
  (:require [week3.validations :as validations]
            [schema.core :as s]))

(s/def Date (s/pred validations/valid-date? 'WrongValue))
(s/def Value (s/pred validations/valid-value? 'WrongValue))
(s/def Establishment (s/pred validations/valid-establishment? 'WrongEstablishment))
(s/def Category (s/pred validations/valid-category? 'WrongCategory))
(s/def Card (s/pred validations/valid-card? 'WrongCard))

(s/def ValidBuySchema
  "Schema for a valid buy"
  {:date          Date
   :value         Value
   :establishment Establishment
   :category      Category
   :card          Card})


;(pprint (s/validate ValidBuySchema {:date "2022-04-12" :value 10M :establishment "Restaurante" :category "Alimentação" :card 1000 }))