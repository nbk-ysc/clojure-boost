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
  (atom [])
  )

(def repositorio-de-compras
  (atom lista-compras-id)
  )

(def compras-exemplo [{:ID              3
                       :data            "2022-10-25",
                       :valor           976.88,
                       :estabelecimento "Oficina",
                       :categoria       "Automóvel",
                       :cartao          3939393939393939}
                      {:ID              4
                       :data            "2022-04-10",
                       :valor           85.0,
                       :estabelecimento "Alura",
                       :categoria       "Educação",
                       :cartao          3939393939393939}])

(def compras-vazio [])

(def categorias ["Alimentação",
                 "Automóvel",
                 "Casa",
                 "Educação",
                 "Lazer",
                 "Saúde"])
