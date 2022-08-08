(ns clojure-boost.week_2.utils
  (:use [clojure.pprint])
  (:require [ultra-csv.core :as csv]))

(def lista-compras-id
  (vec (csv/read-csv "files/compras-com-id.csv"
                     {:field-names [:ID,
                                    :data,
                                    :valor,
                                    :estabelecimento,
                                    :categoria,
                                    :cartao]})))

(def repositorio-de-compras
  (atom []))

(def compras-vazio [])

(def categorias ["Alimentação",
                 "Automóvel",
                 "Casa",
                 "Educação",
                 "Lazer",
                 "Saúde"])
