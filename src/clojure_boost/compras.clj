(ns clojure-boost.compras
  (:use clojure.pprint)
  (:require
    [clojure-boost.utils :as utils]
    [clojure-boost.schemas :as schema]
    [schema.core :as s
     :include-macros true]))

(println "Nova compra" (utils/nova-compra "2022-10-03" 40.10 "Fake SA" "Alimentação" 10101010110101010))

(s/defn filtra-cartao
  "Filtra cartão e gera os valores para outra função"
  [cartao :- Long lista-compras :- schema/ListaCompraSchema]
  (->> lista-compras
       (filter #(= (get % :cartao) cartao))))

(s/defn total-gasto [lista-compras :- schema/ListaCompraSchema]
  "Retorna o valor total gasto nas compras"
  (->> lista-compras
       (map :valor)
       (reduce +)))

(println "Valor total gasto foi" (total-gasto (utils/lista-compras)))

(s/defn total-gasto-por-cartao [lista-compras :- schema/ListaCompraSchema cartao :- Long]
  "Retorna o valor total gasto nas compras por cartão"
  (let [valor-cartao (filtra-cartao cartao lista-compras)]
  (->> valor-cartao
       (map :valor)
       (reduce +))))

(println "Valor gasto do cartao" (total-gasto-por-cartao (utils/lista-compras) 3939393939393939))


(s/defn lista-compras-mes
  "Retorna a lista de compras a partir de um mês"
  [lista-compras :- schema/ListaCompraSchema mes :- s/Int]
  (->> lista-compras
       (filter #(= mes (utils/filtra-mes (:data %))))))

(println "Lista de compras por mês" (lista-compras-mes (utils/lista-compras) 04))

(s/defn total-gasto-no-mes
  "Função que calcula o total dos valores passados"
  [lista-compras :- schema/ListaCompraSchema mes :- s/Int]
  (->> (lista-compras-mes lista-compras mes)
       (map :valor)
       (reduce +)))

(println "Total gasto no mês" (total-gasto-no-mes (utils/lista-compras) 01))

(defn lista-valores [[categoria valor]]
  "Função que gera os vetores com categoria e valores somados"
  [categoria (reduce + (map :valor valor))])

(s/defn lista-todas-categorias
  "Função que gera o mapa com categorias e valores já somados"
  [lista-compras :- schema/ListaCompraSchema]
  (into {} (map lista-valores (utils/agrupar-categoria lista-compras))))

(println "Valor somado de todas as categorias")
(pprint (lista-todas-categorias (utils/lista-compras)))


(s/defn filtra-compras-valor [valor-maximo valor-minimo]
  (->> (utils/lista-compras)
       (filter #(and (<= (:valor %) valor-maximo) (>= (:valor %) valor-minimo)))))
(println "Filtro por valor máximo e mínimo" (filtra-compras-valor 100.0 50.0))


(println "-----------LISTA DE CARTOES FORMATADO-----------")
(pprint (->> (utils/lista-cartoes)
             (map #(update % :validade utils/formata-data-cartao "yyyy-MM" "yyyy/MM"))))


(println "---------------LISTA DE COMPRAS COM DATA JAVA TIME-----------------")

(s/defn lista-compras-mes-cartao
  "Retorna a lista de compras a partir de um mês"
  [lista-compras :- schema/ListaCompraSchema mes :- s/Int]
  (->> lista-compras
       (filter #(= mes (utils/formata-data-cartao-mes (:data %))))))


(println "Lista de compras por mês" (lista-compras-mes-cartao (utils/lista-compras) 04))

(println "Total gasto no mês" (total-gasto-no-mes (utils/lista-compras) 01))

