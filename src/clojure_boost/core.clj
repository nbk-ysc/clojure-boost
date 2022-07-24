(ns clojure-boost.core
  (:require [clojure-boost.lista-de-compras-csv :refer [lista-compras]])
  (:require [ultra-csv.core :as ultra])
  (:require [clojure.string :as cs])
  (:require [java-time :as jt]))

;---------------------
;Exercicio 1
;---------------------
;Tarefa
;Criar a função nova-compra, que retorne uma estrutura de dados que
; represente uma compra realizada em um determinado cartão.
;
;Parâmetros:
;data (String: yyyy-mm-dd)
;valor (BigDecimal)
;estabelecimento (String)
;categoria (String)
;cartao (Long)
;Retorno:
;Map com a seguinte estrutura:
;{:data ...
;  :valor ...
;  :estabelecimento ...
;  :categoria ...
;  :cartao ...}
;---------------------
(def nova-compra {:data            "2022-07-04"
                  :valor           400.98
                  :estabelecimento "Outback Bangu"
                  :categoria       "Alimentação"
                  :cartao          6655665566556655})
(println "---------------\nExercicio 1\n---------------")
(println nova-compra)
;---------------------
;Exercicio 2
;---------------------
;Crie as funções lista-compras que retorna uma coleção
; com todas as compras realizadas.
;
;Parâmetros
;A função não recebe parâmetros.
;
;Retorno
;Deve retornar um vetor de maps de compras.
;
;Critérios de aceitação
;O vetor deve ter os 19 maps de compras, com os dados da planilha
(println "---------------\nExercicio 2\n---------------")
(println (lista-compras))
;---------------------
;Exercicio 3
;---------------------
;Tarefa
;Criar a função total-gasto, que recebe um vetor de compras e
; retorna a soma dos valores gastos.
;
;Parametros
;compras (vetor com maps de compra)
;Retorno
;BigDecimal com a soma do valor das compras
;Exemplo:
;[{:valor: 100.00 ; demais chaves do mapa
;  {:valor: 250.00 ; demais chaves do mapa
;  {:valor: 400.00 ; demais chaves do mapa}]
;
;TOTAL: R$ 750,00

(defn filtrando-cartao
  [numerocartao cartoes]
  (filter #(= (get % :cartao) numerocartao) cartoes))

(defn valor-total-compras
  [valores]
  (->> valores
       (map :valor)
       (reduce +)))

(defn total-gasto
  [cartao lista]
  (valor-total-compras (filtrando-cartao cartao lista)))
(println "---------------\nExercicio 3\n---------------")
(println "O total do cartao:" (total-gasto 3939393939393939 (lista-compras)))

;---------------------
;Exercicio 4
;---------------------
;Tarefa
;Criar uma função que, dado um mês e uma lista de compras,
; retorne um vetor das compras feitas somente naquele mês.
;Parâmetros
;mes (inteiro)
;lista de compras (vetor ou list com maps de compras)
;Retorno
;vetor com maps de compra
(defn selecionando-o-mes
  [data]
  (-> (jt/as (jt/local-date data) :month-of-year)))

(defn compras-do-mes
  [mes compras]
  (->> compras
       (filter #(= mes (selecionando-o-mes (:data %))))))
(println "---------------\nExercicio 4\n---------------")
(println "Compras realizadas no mês 4" (compras-do-mes 4 (lista-compras)))

;---------------------
;Exercicio 5
;---------------------
;Tarefa Calcular o total da fatura de um mês
;Criar a função total-gasto-no-mes, que calcule a soma dos valores gastos num determinado cartão em um mês.
;
;Para facilitar, considere que todas as compras sejam de um mesmo cartão.

(defn total-gasto-no-mes
  [numerocartao mes compras]
  (->> compras
       (filter #(= (get % :cartao) numerocartao))
       (filter #(= mes (selecionando-o-mes (:data %))))
       (map :valor)
       (reduce +)))
(println "---------------\nExercicio 5\n---------------")
(println "Fatura do mês 2 do cartão 1234123412341234")
(println "Valor em R$" (total-gasto-no-mes 1234123412341234 2 (lista-compras)))

;---------------------
;Exercicio 6
;---------------------
;Tarefa Agrupar gastos por categoria
;Criar uma função que retorne os total gasto agrupados por categoria.
;
;Parâmetros
;compras (vetor com maps de compras)
;Retorno
;Map* com as categorias associadas ao valor gasto
;Exemplo
;[{:categoria  "Educação" :valor 700.00 ; demais chaves do mapa}
; {:categoria  "Saúde" :valor 1500.00 ; demais chaves do mapa}
; {:categoria  "Educação" :valor 50.00 ; demais chaves do mapa}
; {:categoria  "Alimentação" :valor 100.00 ; demais chaves do mapa}
; {:categoria  "Alimentação" :valor 89.90 ; demais chaves do mapa}]
;
;Saída
;{"Educação" 750.00
; "Saúde" 1500.00
; "Alimentação" 189.90}
;
;OBSERVAÇÃO
;A saída não precisa ser ordenada

(defn categorias-agrupadas
  [compra]
  (->> compra (group-by :categoria)))

(defn calcula-valor-das-categorias
  [[chave valor]]
  {chave (reduce + (map :valor valor))})

(defn total-gasto-por-categoria
  [valor]
  (->> valor (categorias-agrupadas) (into {} (map calcula-valor-das-categorias))))
(println "---------------\nExercicio 6\n---------------")
(println (total-gasto-por-categoria (lista-compras)))

;---------------------
;Exercicio 9
;---------------------
;Tarefa
;Adapte a função lista-compras para carregar os dados do arquivo compras.csv anexo nesta tarefa.
(defn transforma-csv-em-lista-compras []
  (into [] (ultra/read-csv "compras.csv")))
(println "---------------\nExercicio 9\n---------------")
(println (transforma-csv-em-lista-compras))