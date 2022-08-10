(ns clojure-boost.core
  (:use [clojure.pprint])
  (:require [clojure-boost.week_1.logic :as logic.week_1]
            [clojure-boost.week_2.logic :as logic.week_2]
            [clojure-boost.week_1.utils :as utils.week_1]
            [clojure-boost.week_2.utils :as utils.week_2]
            [clojure-boost.week_3.logic :as logic.week_3]
            [clojure-boost.persistencia.datomic :as persistencia.datomic]
            [java-time :as jt]))

;---------------------------------------------------------------------------------------------------------
;Chamadas de funcoes da semana 1:
;---------------------------------------------------------------------------------------------------------
;Inserir uma nova compra:
;(pprint (logic.week_1/nova-compra "2022-04-10", 85.0, "Alura", "Educação", 3939393939393939))

;Retornar a soma de todas as compras:
(logic.week_1/total-gasto utils.week_1/lista-compras)

;Retornar o valor das compras para um cartao especifico
(logic.week_1/selecionar-cartao 3939393939393939 utils.week_1/lista-compras)

;Retornar a soma de todas as compras para um cartao especifico:
(logic.week_1/total-gastos-por-cartao 3939393939393939 utils.week_1/lista-compras)

;retornar uma lista com todas as compras para um mes especifico
(logic.week_1/obter-compras-por-mes utils.week_1/lista-compras 4)

;retorna o total da fatura de um cartao para um mes especifico
(logic.week_1/total-gasto-no-mes utils.week_1/lista-compras 4 3939393939393939)

;retorna as compras agrupadas por categoria
(logic.week_1/obtem-categorias utils.week_1/lista-compras)

;retorna o valor total por categoria
(pprint (logic.week_1/agrupar-por-categoria utils.week_1/lista-compras))

;filtrar um intervalo de valores
(logic.week_1/filtra-intervalo-valor utils.week_1/lista-compras 100 230)

;Converter datas de validade do cartao e compra
(logic.week_1/convert-datas-cartao utils.week_1/cartoes)
(logic.week_1/convert-datas-compras utils.week_1/lista-compras)

;Visualizar a lista de compras e cartões
(pprint utils.week_1/lista-compras)
(pprint utils.week_1/cartoes)

;---------------------------------------------------------------------------------------------------------
;Chamadas de funcoes da semana 1:
;---------------------------------------------------------------------------------------------------------
;Chamada do atomo
(pprint utils.week_2/repositorio-de-compras)

;insere uma nova compra de um vetor com compras
(pprint (logic.week_2/insere-compra @utils.week_2/repositorio-de-compras (logic.week_2/->compra nil "2022-10-25" 10.00M "pp" "Saúde" 1234123412341234)))

;inserir uma nova compra em um atomo
(pprint (logic.week_2/insere-compra! utils.week_2/repositorio-de-compras (logic.week_2/->compra nil (jt/local-date "2022-07-25") 32.00M "LM" "Saúde" 1234123412341234)))

;Listar o conteudo do atomo
(logic.week_2/lista-compras! utils.week_2/repositorio-de-compras)

;excluir uma compra do atomo
(pprint (logic.week_2/exclui-compra! utils.week_2/repositorio-de-compras 23))

;Validacao de uma compra
(logic.week_2/valida-compra (logic.week_2/->compra nil (jt/local-date "2022-02-10") 10.0M "pastel" "Saúde" 1234123412341234))

;---------------------------------------------------------------------------------------------------------
;Chamadas de funcoes da semana 3:
;---------------------------------------------------------------------------------------------------------
;Chamada do record compra
;(logic.week_3/->compra 0 (jt/local-date "2022-06-25") 10.0M "pp" "Saúde" 1234123412341234)

;Chamada da função nova-compra
(logic.week_3/nova-compra (jt/local-date "2022-06-25") 10M "Saúde" "Saúde" 1234123412341234)

;---------------------------------------------------------------------------------------------------------
;Chamadas de funcoes da semana 4:
;---------------------------------------------------------------------------------------------------------
;Função para inserir uma compra no Datomic
(persistencia.datomic/insere-compra! (logic.week_3/nova-compra-db 1 "2022-06-25" 10.0M "Zé Lanches" "Alimentação" 1234123412341234))

;-----------------------------------------------------------------------------------------------------------
;Bônus:
;------------------------------------------------------------------------------------------------------------
;Chamada da função que preenche o atomo com compras já com ID a partir do CSV de compras sem IDs
(logic.week_2/insere-id! utils.week_1/lista-compras utils.week_2/repositorio-de-compras)

;Listar o conteudo do atomo
(logic.week_2/lista-compras! utils.week_2/repositorio-de-compras)