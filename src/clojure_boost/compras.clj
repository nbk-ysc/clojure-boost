(ns clojure-boost.compras
  (:use clojure.pprint)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [ultra-csv.core :as csv]
            [java-time :as jt]))

(defn nova-compra
  "Retorna uma nova compra"
  [data valor estabelecimento categoria cartao]
  [{
                :data data
                :valor valor
                :estabelecimento estabelecimento
                :categoria categoria
                :cartao cartao
                }])

(defn lista-compras []
  "retorna lista via csv"
  (csv/read-csv "compras.csv"
                {:field-names [:data :valor :estabelecimento :categoria :cartao]}))

(defn filtra-cartao
  "Filtra cartão e gera os valores para outra função"
  [cartao lista-compras]
  (->> lista-compras
       (filter #(= (get % :cartao) cartao))))

(defn total-gasto [lista-compras]
  "Retorna o valor total gasto nas compras"
  (->> lista-compras
       (map :valor)
       (reduce +)))

(println "Valor total gasto foi" (total-gasto (lista-compras)))

(defn total-gasto-por-cartao [lista-compras cartao]
  "Retorna o valor total gasto nas compras por cartão"
  (let [valor-cartao (filtra-cartao cartao lista-compras)]
  (->> valor-cartao
       (map :valor)
       (reduce +))))

(println "Valor gasto do cartao" (total-gasto-por-cartao (lista-compras) 3939393939393939))

(defn filtra-mes
  "Filtra as datas"
  [data]
  (-> data
      (str/split #"-")
      (second)
      (Integer/parseInt)))

(defn lista-compras-mes
  "Retorna a lista de compras a partir de um mês"
  [lista-compras mes]
  (->> lista-compras
       (filter #(= mes (filtra-mes (:data %))))))

(println "Lista de compras por mês" (lista-compras-mes (lista-compras) 04))

(defn total-gasto-no-mes
  [lista-compras mes]
  (->> (lista-compras-mes lista-compras mes)
       (map :valor)
       (reduce +)))

(println "Total gasto no mês" (total-gasto-no-mes (lista-compras) 01))

(defn agrupar-categoria
  [lista-compras]
  (->> lista-compras
       (group-by :categoria)))

(defn lista-valores [[categoria valor]]
  [categoria (reduce + (map :valor valor))])

(defn lista-todas-categorias
  [lista-compras]
  (into {} (map lista-valores (agrupar-categoria lista-compras))))

(println "Valor somado de todas as categorias")
(pprint (lista-todas-categorias (lista-compras)))


(defn filtra-compras-valor [valor-maximo valor-minimo]
  (->> (lista-compras)
       (filter #(and (<= (:valor %) valor-maximo) (>= (:valor %) valor-minimo)))))
(println "Filtro por valor máximo e mínimo" (filtra-compras-valor 100.0 50.0))

































