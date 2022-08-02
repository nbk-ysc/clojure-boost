(ns clojure-boost.semana1.lista-de-compras-csv
(:require [ultra-csv.core :as ultra]))

(defn lista-compras []
  (ultra/read-csv "compras.csv" {:field-names [:data :valor :estabelecimento :categoria :cartao]}))
(println (lista-compras))