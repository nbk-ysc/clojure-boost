(ns clojure-boost.week_1.utils
  (:use [clojure.pprint])
  (:require [ultra-csv.core :as csv]
            [clojure-boost.week_1.logic :as logic.week_1]))

(def lista-compras
  (logic.week_1/convert-bigdec
    (logic.week_1/convert-datas-compras
      (csv/read-csv "files/compras.csv"
                    {:field-names [:data,
                                   :valor,
                                   :estabelecimento,
                                   :categoria,
                                   :cartao]}))))

(def cartoes
  (logic.week_1/convert-datas-cartao (csv/read-csv "files/cartoes.csv"
                                                   {:field-names [:numero,
                                                                  :cvv,
                                                                  :validade,
                                                                  :limite,
                                                                  :cliente]})))


