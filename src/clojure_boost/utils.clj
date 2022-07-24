(ns clojure-boost.utils
  (:require [ultra-csv.core :as csv]))

(def lista-compras
  (csv/read-csv "compras.csv"
                {:field-names [:data,:valor,:estabelecimento,:categoria,:cartao]}))
