(ns clojure-boost.Schemaex
  (:require [clojure.pprint :as pprint]
            [schema.core :as s]))


;Schema é um mapa

(def schemateste {:id s/Num, :nome s/Str})

(pprint/pprint (s/explain schemateste))
(pprint/pprint (s/validate schemateste {:id 3, :nome "str"}))

;Funcao novoschemateste e definida como um tipo
(s/defn novoschemateste :- schemateste [id :- s/Num, nome :- s/Str]
  {:id id :nome nome})

(defn estritamente-positivo? [x]
  (> x 0))
(def EstritamentePositivo (s/pred estritamente-positivo?))
(pprint/pprint (s/validate EstritamentePositivo 15))

;Inteiro positivo usando s/constrained
(def teste
  "s/constrained"
  {:id (s/constrained s/Int pos?), :nome s/Str})