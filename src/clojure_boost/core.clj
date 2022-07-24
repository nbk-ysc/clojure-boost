(ns clojure-boost.core
  (:require [clojure-boost.logic :as l])
  (:require [clojure-boost.utils :as csv])
  (:use [clojure.pprint]))

;-----------------------------------------------------------
;Inserir uma nova compra:
(l/nova-compra "2022-04-10", 85.0, "Alura", "Educação", 3939393939393939)

;Retornar a soma de todas as compras:
(l/total-gastos csv/lista-compras)

;Retornar o valor das compras para um cartao especifico
(l/selecionar-cartao 3939393939393939 csv/lista-compras)

;Retornar a soma de todas as compras para um cartao especifico:
(l/total-gastos-por-cartao 3939393939393939 csv/lista-compras)

;retornar um vetor com todas as compras para um mes especifico
(l/obter-compras-por-mes csv/lista-compras 4)

;retorna o total da fatura de um cartao para um mes especifico
(l/total-gasto-no-mes csv/lista-compras 4 3939393939393939)

;retorna as compras agrupadas por categoria
(l/obtem-categorias csv/lista-compras)

;retorna o valor total por categoria
(l/agrupar-por-categoria csv/lista-compras)

;Manipulação da coleção de lista de compras:
(count csv/lista-compras)
(class csv/lista-compras)
(pprint csv/lista-compras-csv)

;-----------------------------------------------------------
