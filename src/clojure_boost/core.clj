(ns clojure-boost.core
  (:use [clojure.pprint])
  (:require [clojure-boost.week_1.logic :as logic.week_1]
            [clojure-boost.week-2.logic :as logic.week_2]
            [clojure-boost.week_1.utils :as utils.week_1]
            [clojure-boost.week_2.utils :as utils.week_2]))

;-----------------------------------------------------------
;Chamadas de funcoes da semana 1:
;-----------------------------------------------------------
;Inserir uma nova compra:
(logic.week_1/nova-compra "2022-04-10", 85.0, "Alura", "Educação", 3939393939393939)

;Retornar a soma de todas as compras:
(logic.week_1/total-gastos csv/lista-compras)

;Retornar o valor das compras para um cartao especifico
(logic.week_1/selecionar-cartao 3939393939393939 csv/lista-compras)

;Retornar a soma de todas as compras para um cartao especifico:
(logic.week_1/total-gastos-por-cartao 3939393939393939 csv/lista-compras)

;retornar uma lista com todas as compras para um mes especifico
(logic.week_1/obter-compras-por-mes csv/lista-compras 4)

;retorna o total da fatura de um cartao para um mes especifico
(logic.week_1/total-gasto-no-mes csv/lista-compras 4 3939393939393939)

;retorna as compras agrupadas por categoria
(logic.week_1/obtem-categorias csv/lista-compras)

;retorna o valor total por categoria
(pprint (logic.week_1/agrupar-por-categoria csv/lista-compras))

;filtrar um intervalo de valores
(logic.week_1/filtra-intervalo-valor csv/lista-compras 100 230)

;Converter datas de validade do cartao e compra
(logic.week_1/convert-datas-cartao csv/cartoes)
(logic.week_1/convert-datas-compras csv/lista-compras)

;-----------------------------------------------------------
;Chamadas de funcoes da semana 1:
;-----------------------------------------------------------
;Chamada do atomo
(pprint utils.week_2/repositorio-de-compras)

;insere uma nova compra em um vetor com compras
(pprint (logic.week_2/insere-compra (logic.week_2/->compra nil "25-10-2022" 10.00 "pp" "games" 1234123412341234) utils.week_2/compras-exemplo))

;insere uma nova compra em um vetor vazio
(pprint (logic.week_2/insere-compra (logic.week_2/->compra nil "25-10-2022" 10.00 "pp" "games" 1234123412341234) utils.week_2/compras-vazio))

;inserir uma nova compra em um atomo
(pprint (logic.week_2/insere-compra! (->compra nil "25-10-2022" 10.00 "pp22" "games" 1234123412341234) utils.week_2/repositorio-de-compras))