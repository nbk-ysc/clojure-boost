(ns clojure-boost.week_1.utils
  (:require [ultra-csv.core :as csv]
            [java-time :as jt]
            [clojure-boost.week_1.logic :as logic.week_1])
  (:use [clojure.pprint]))

(def lista-compras
  (logic.week_1/convert-datas-compras (csv/read-csv "files/compras.csv"
                                                    {:field-names [:data,
                                                                   :valor,
                                                                   :estabelecimento,
                                                                   :categoria,
                                                                   :cartao]})))

  (def cartoes
    (logic.week_1/convert-datas-cartao (csv/read-csv "files/cartoes.csv"
                                                     {:field-names [:numero,
                                                                    :cvv,
                                                                    :validade,
                                                                    :limite,
                                                                    :cliente]})))


