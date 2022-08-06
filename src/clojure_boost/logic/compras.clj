(ns clojure-boost.logic.compras
  (:use clojure.pprint)
  (:require [clojure-boost.schemas.compras :as schemas.compras]
            [java-time :as java-time]
            [schema.core :as s]))

(s/defn total-gasto :- schemas.compras/BigDec
  "Retorna o total gasto a partir de uma lista de compras"
  [compras :- schemas.compras/CompraSchemaVetor]
  (->>
   compras
   (map :valor)
   (reduce +)))

(s/defn agrupa-categoria :- schemas.compras/AgrupaCategoria
  "Retorna uma lista com as compras agrupadas por categoria "
  [compras :- schemas.compras/CompraSchemaVetor]
  (->> compras
       (group-by :categoria)))

(s/defn mapeia-compras-por-categoria :- (s/pred vector?)
  "Retorna uma lista com vetores que contém o nome da categoria e total de compras da categoria"
  [[categoria compras]] :- (s/pred vector?)
  [categoria
   (total-gasto compras)])

(s/defn total-compras-por-categoria :- schemas.compras/TotalGastoPorCategoria
  "Retorna uma coleção com o nome da categoria e o valor total da categoria"
  [compras :- schemas.compras/CompraSchemaVetor]
  (->> compras
       agrupa-categoria
       (map mapeia-compras-por-categoria)
       (into {})))

(s/defn compras-por-cartao :- schemas.compras/CompraSchemaVetor
  "Retorna lista de compras a partir de um determinado número de cartão"
  [compras :- schemas.compras/CompraSchemaVetor
   cartao :- schemas.compras/Cartao]
  (vec (filter #(= (:cartao %) cartao) compras)))

(s/defn compras-por-mes :- schemas.compras/CompraSchemaVetor
  "Retorna lista de compras a partir de um mês.
  Formato da data espero na lista de compras é yyyy-MM-dd"
  [mes :- s/Int
   compras :- schemas.compras/CompraSchemaVetor]
  (->> compras
       (filter #(= (java-time/as (java-time/local-date (:data %)) :month-of-year) mes))
       vec))

(s/defn total-gasto-no-mes :- schemas.compras/BigDec
  "Retorna o valor total de gasto em bigDecimal das compras de um determinado mês"
  [mes :- s/Int
   compras :- schemas.compras/CompraSchemaVetor]
  (let [compras-do-mes (compras-por-mes mes compras)]
    (total-gasto compras-do-mes)))

(s/defn filtra-compras-por-valor :- schemas.compras/CompraSchemaVetor
  "Retorna compras a partir de um valor máximo e valor minimo"
  [lista-compras :- schemas.compras/CompraSchemaVetor
   valor-maximo :- schemas.compras/BigDec
   valor-minimo :- schemas.compras/BigDec]
  (->> lista-compras
       (filter #(>= (:valor %) valor-minimo))
       (filter #(<= (:valor %) valor-maximo))
       vec))
