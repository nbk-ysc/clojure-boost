(ns clojure-boost.week_2.utils
  (:use [clojure.pprint]))

(def repositorio-de-compras
  (atom [])
  )

(def compras-exemplo [{:ID              3
                       :data            "2022-04-01",
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

