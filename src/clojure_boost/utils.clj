(ns clojure-boost.utils
  (:require [ultra-csv.core :as csv])
  (:use [clojure.pprint]))

(def lista-compras
  (csv/read-csv "compras.csv"
                {:field-names [:data,:valor,:estabelecimento,:categoria,:cartao]}))

(def cartoes
  (csv/read-csv "cartoes.csv"
                {:field-names [:numero,:cvv,:validade,:limite,:cliente]}))

(def repositorio-de-compras
  (atom [])
  )