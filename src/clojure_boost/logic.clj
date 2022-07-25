(ns clojure-boost.logic
  (:use clojure.pprint)
  (:require [clojure-boost.utils :as utils]
            [java-time :as jt]))

(defn nova-compra
  "Retorna estrutura de dados de uma compra realizada"
  [data, valor, estabelecimento, categoria, cartao]
  {:data            (jt/local-date data)
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

(pprint (nova-compra "2022-01-01", 129.90, "Outback", "Alimentação", 1234123412341234))


(defn lista-compras []
  "Retorna lista de compras a partir do csv"
  (let [arquivo "arquivos/compras.csv"
        campos [:data, :valor, :estabelecimento, :categoria, :cartao]]
    (utils/ler-csv arquivo campos)))

;(pprint (lista-compras))


(defn lista-compras-formatada
  "Retorna lista de compras formatada com a data padrão dd/MM/yyyy e campo valor como bigDecimal"
  [lista-compras-original]
  (->> lista-compras-original
       (map #(update % :data jt/local-date))
       (map #(update % :valor bigdec))))

;(pprint (lista-compras-formatada (lista-compras)))


(defn total-gasto
  "Retorna o total gasto a partir de uma lista de compras"
  [compras]
  (->>
    compras
    (map :valor)
    (reduce +)
    bigdec))

;(pprint (total-gasto (lista-compras)))


(defn compras-por-cartao
  "Retorna lista de compras a partir de um determinado número de cartão"
  [compras cartao]
  (filter #(= (:cartao %) cartao) compras))

;(pprint (compras-por-cartao (lista-compras) 3939393939393939))


(defn compras-por-mes
  "Retorna lista de compras a partir de um mês.
  Formato da data espero na lista de compras é yyyy-MM-dd"
  [mes compras]
  (->> compras
       (filter #(= (jt/as (jt/local-date (:data %)) :month-of-year) mes))))

;(pprint (compras-por-mes 3 (lista-compras)))
;(pprint (total-gasto (compras-por-cartao (compras-por-mes 03 (lista-compras)) 3939393939393939)))


(defn total-gasto-no-mes
  "Retorna o valor total de gasto em bigDecimal das compras de um determinado mês"
  [mes compras]
  (let [compras-do-mes (compras-por-mes mes compras)]
    (total-gasto compras-do-mes)))

;(pprint (total-gasto-no-mes 1 (lista-compras)))


(defn agrupa-categoria
  "Retorna uma lista com as compras agrupadas por categoria "
  [compras]
  (->> compras
       (group-by :categoria)))

(defn mapeia-compras-por-categoria
  "Retorna uma lista com vetores que contém o nome da categoria e total de compras da categoria"
  [[categoria compras]]
  [categoria
   (total-gasto compras)])

;(pprint (map mapeia-compras-por-categoria (agrupa-categoria (lista-compras))))

(defn total-compras-por-categoria
  "Retorna uma coleção com o nome da categoria e o valor total da categoria"
  [compras]
  (->> compras
       agrupa-categoria
       (map mapeia-compras-por-categoria)
       (into {})))

;(pprint (total-compras-por-categoria (lista-compras)))


(defn filtra-compras-por-valor
  "Retorna compras a partir de um valor máximo e valor minimo"
  [valor-maximo valor-minimo]
  (->> (lista-compras)
       (filter #(>= (:valor %) valor-minimo))
       (filter #(<= (:valor %) valor-maximo))))

;(pprint (filtra-compras-por-valor 100 0))


(defn lista-cartoes []
  "Retorna lista de cartões a partir do csv"
  (let [arquivo "arquivos/cartao.csv"
        campos [:numero, :cvv, :validade, :limite, :cliente]]
    (utils/ler-csv arquivo campos)))

(defn lista-cartao-formatada
  "Retorna lista de cartões formatada com a validade padrão dd/MM/yyyy e campo limite como bigDecimal"
  [lista-cartao-original]
  (->> lista-cartao-original
       (map #(update % :validade jt/year-month))
       (map #(update % :limite bigdec))))

;(pprint (lista-cartao-formatada (lista-cartoes)))