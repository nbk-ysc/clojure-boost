(ns clojure-boost.week_1.utils
  (:require [ultra-csv.core :as csv])
  (:use [clojure.pprint]))

(def lista-compras
  (csv/read-csv "files/compras.csv"
                {:field-names [:data,
                               :valor,
                               :estabelecimento,
                               :categoria,
                               :cartao]}))

(def cartoes
  (csv/read-csv "files/cartoes.csv"
                {:field-names [:numero,
                               :cvv,
                               :validade,
                               :limite,
                               :cliente]}))


