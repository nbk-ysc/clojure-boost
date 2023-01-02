(ns clojure-boost.Schemaex
  (:require [clojure.pprint :as pprint]
            [schema.core :as s]))


;Schema é um mapa
;Para deixar a chave opcional usa-se s/optional-key
;Schema com chaves fixas
(def schemateste {:id s/Num, :nome s/Str (s/optional-key :optkeys) s/Str})

;Schema com chaves dinamicas
;Note que existe duas entradas na funcao
;um numero e um echema
(def schemadinamico {s/Num schemateste} )

(pprint/pprint (s/explain schemateste))
(pprint/pprint (s/validate schemateste {:id 3, :nome "str"}))

(pprint/pprint (s/explain schemadinamico))
(pprint/pprint (s/validate schemadinamico{3 {:id 3, :nome "str"}}))

;Para validar os tipos nas funcoes
(s/set-fn-validation! true)

;Funcao novoschemateste e definida como um tipo
;x :- y significa que x deve satisfazer o esquema y.
;x :- y é equivalente a ^y x
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