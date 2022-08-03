(ns clojure-boost.week_1.logic
  (:use [clojure.pprint])
  (:require [java-time :as jt]))

;-----------------------------------------------------------
(defn nova-compra
  "Funcao para inserir uma nova compra"
  [data, valor, estabelecimento, categoria, cartao]
  {:data            (str data)
   :valor           (bigdec valor)
   :estabelecimento (str estabelecimento)
   :categoria       (str categoria)
   :cartao          (long cartao)})

;-----------------------------------------------------------
(defn total-gastos
  "Funcao para retornar o total gasto em todos os cartoes"
  [lista-compras]
  (->>
    lista-compras
    (map :valor)
    (reduce +)
    ))

(defn selecionar-cartao
  "Funcao para retorna uma lista de gastos para um cartao especifico"
  [numero-cartao lista-compras]
  (->>
    lista-compras
    (filter #(= (get % :cartao) numero-cartao))
    (map :valor)))

(defn total-gastos-por-cartao
  "Funcao para retorna o total gasto em um cartao especifico"
  [numero-cartao lista-compras]
  (->>
    (selecionar-cartao numero-cartao lista-compras)
    (reduce +)
    ))

;-----------------------------------------------------------
(defn splitar-mes
  "Funcao que transforma a data em um mes (inteiro)"
  [lista-compras]
  (->
    (clojure.string/split lista-compras #"-")
    (get 1)
    (Integer/parseInt)))

(defn obter-compras-por-mes-string
  "Funcao para obter todas as compras de um mes
  Utilizando string"
  [lista-compras mes]
  (->>
    lista-compras
    (filter #(= mes (splitar-mes (:data %))))))

(defn obter-compras-por-mes-java-time
  "Funcao para obter todas as compras de um mes
  Utilizando Java-Time"
  [lista-compras mes]
  (->>
    lista-compras
    (filter #(= (jt/month mes) (jt/month (:data %))))))

(defn obter-compras-por-mes
  "Funcao para obter a lista de compras para um determinado mes
  utiliza funcoes que entendem java-time e string para filtar"
  [lista-compras mes]
  (if (= String (nth (map #(class (get % :data)) lista-compras) 1))
    (obter-compras-por-mes-string lista-compras mes)
    (obter-compras-por-mes-java-time lista-compras mes)))

;-----------------------------------------------------------
(defn total-gasto-no-mes
  "Funcao para calcular o total de gastos em um mes para um cartao especifico"
  [lista-compras mes cartao]
  (->>
    (selecionar-cartao cartao (obter-compras-por-mes lista-compras mes))
    (reduce +)))

;-----------------------------------------------------------
(defn obtem-categorias
  "Funcao para obter as categorias e os valores individuais"
  [lista-compras]
  (->> lista-compras
       (group-by :categoria)))

(defn agrupar-por-categoria
  "Funcao que retorna o total por categoria"
  [lista-compras]
  (into (sorted-map) (map (fn [[categoria compras]]
                            [categoria (total-gastos compras)])
                          (obtem-categorias lista-compras))))

;-----------------------------------------------------------
(defn filtra-intervalo-valor
  "Funcao para filtrar um intervalo de valores"
  [lista-compras valor-minimo valor-maximo]
  (->> lista-compras
       (map :valor)
       (filter #(and (>= % valor-minimo) (<= % valor-maximo)))
       ))

;-----------------------------------------------------------
(defn convert-datas-cartao
  "Funcao para converter em datas as strings de validades dos cartoes de credito"
  [cartoes]
  (->> cartoes
       (map #(update % :validade jt/year-month))))

(defn convert-datas-compras
  "Funcao para converter o formato das datas de string para java-time/local-date de compras"
  [lista-compras]
  (->> lista-compras
       (map #(update % :data jt/local-date))))

