(ns clojure-boost.semana5
  (:require [clojure.pprint :as pprint]
            [schema.core :as s]))

(s/set-fn-validation! true) ;Para validar os tipos nas funcoes - usa-se :-

(s/defn teste-simples [x :- Long]
  (println x))

(s/defn teste-string [x :- s/Str]
  (println x))