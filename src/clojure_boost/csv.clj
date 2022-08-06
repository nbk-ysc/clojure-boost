(ns clojure-boost.csv
  (:require [clojure-boost.utils :as utils]
            [clojure-boost.schemas.compras :as schemas.compra]
            [schema.core :as s]))

(s/defn lista-compras [] :- schemas.compra/CompraSchemaVetor
  "Retorna lista de compras formatada com a data padrão dd/MM/yyyy e campo valor como bigDecimal"
  (let [arquivo "arquivos/compras.csv"
        campos  [:id :data, :valor, :estabelecimento, :categoria, :cartao]]
    (->> (utils/ler-csv arquivo campos)
         (map #(update % :data java-time/local-date))
         (map #(update % :valor bigdec))
         vec)))
