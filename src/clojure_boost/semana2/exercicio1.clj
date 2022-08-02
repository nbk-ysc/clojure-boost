(ns clojure-boost.semana2.exercicio1
  (:use [clojure pprint])
  (:require [clojure-boost.semana1.lista-de-compras-csv :refer [lista-compras]]))

;Tarefa Definir átomo como BD em memória
;Definir um átomo no símbolo repositorio-de-compras onde serão salvos os maps de compras.
; O átomo deve ser inicializado com um vetor vazio [].

;(def repositorio-de-compras
;  (let [compras (atom {:valor lista-compras})]
;    (println compras)))


(def repositorio-de-compras
  (atom {}))
(pprint repositorio-de-compras)

