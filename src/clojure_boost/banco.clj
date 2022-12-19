(ns clojure-boost.banco
  (:require [clojure.pprint :as pprint]))

(def repositorio-de-compras (atom []))

(defrecord Compra [id data valor estabelecimento categoria cartao])

(defn insere-compra [compra vetor-de-compras]
  (let [id-gerado (+ (count vetor-de-compras) 1)
        compra-com-id (assoc compra :id id-gerado)]

    (conj vetor-de-compras compra-com-id)))
