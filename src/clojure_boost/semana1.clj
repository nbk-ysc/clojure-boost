(ns clojure-boost.semana1
  (:use [clojure.pprint])
  (:require [ultra-csv.core :as csv]))

;Compras realizdas
(defn nova-compra
  "Inserir uma nova compra"
  [data, valor, estabelecimento, categoria, cartao]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

;-----------------------------------------------------------
;Listar Compras - manual
(def lista-compras
  [{:data            "2022-01-01",
    :valor           129.9,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1234123412341234}
   {:data            "2022-01-02",
    :valor           260.0,
    :estabelecimento "Dentista",
    :categoria       "Saúde",
    :cartao          1234123412341234}
   {:data            "2022-02-01",
    :valor           20.0,
    :estabelecimento "Cinema",
    :categoria       "Lazer",
    :cartao          1234123412341234}
   {:data            "2022-01-10",
    :valor           150.0,
    :estabelecimento "Show",
    :categoria       "Lazer",
    :cartao          4321432143214321}
   {:data            "2022-02-10",
    :valor           289.99,
    :estabelecimento "Posto de gasolina",
    :categoria       "Automóvel",
    :cartao          4321432143214321}
   {:data            "2022-02-20",
    :valor           79.9,
    :estabelecimento "iFood",
    :categoria       "Alimentação",
    :cartao          4321432143214321}
   {:data            "2022-03-01",
    :valor           85.0,
    :estabelecimento "Alura",
    :categoria       "Educação",
    :cartao          4321432143214321}
   {:data            "2022-01-30",
    :valor           85.0,
    :estabelecimento "Alura",
    :categoria       "Educação",
    :cartao          1598159815981598}
   {:data            "2022-01-31",
    :valor           350.0,
    :estabelecimento "Tok&Stok",
    :categoria       "Casa",
    :cartao          1598159815981598}
   {:data            "2022-02-01",
    :valor           400.0,
    :estabelecimento "Leroy Merlin",
    :categoria       "Casa",
    :cartao          1598159815981598}
   {:data            "2022-03-01",
    :valor           50.0,
    :estabelecimento "Madero",
    :categoria       "Alimentação",
    :cartao          6655665566556655}
   {:data            "2022-03-01",
    :valor           70.0,
    :estabelecimento "Teatro",
    :categoria       "Lazer",
    :cartao          6655665566556655}
   {:data            "2022-03-04",
    :valor           250.0,
    :estabelecimento "Hospital",
    :categoria       "Saúde",
    :cartao          6655665566556655}
   {:data            "2022-04-10",
    :valor           130.0,
    :estabelecimento "Drogaria",
    :categoria       "Saúde",
    :cartao          6655665566556655}
   {:data            "2022-03-10",
    :valor           100.0,
    :estabelecimento "Show de pagode",
    :categoria       "Lazer",
    :cartao          3939393939393939}
   {:data            "2022-03-11",
    :valor           25.9,
    :estabelecimento "Dogão",
    :categoria       "Alimentação",
    :cartao          3939393939393939}
   {:data            "2022-03-12",
    :valor           215.87,
    :estabelecimento "Praia",
    :categoria       "Lazer",
    :cartao          3939393939393939}
   {:data            "2022-04-01",
    :valor           976.88,
    :estabelecimento "Oficina",
    :categoria       "Automóvel",
    :cartao          3939393939393939}
   {:data            "2022-04-10",
    :valor           85.0,
    :estabelecimento "Alura",
    :categoria       "Educação",
    :cartao          3939393939393939}])

;Listar Compras - read csv
(def lista-compras-csv
  (csv/read-csv "compras.csv"))

;-----------------------------------------------------------
;Calcular o total gasto em compras de um cartão

(defn total-gastos
  "Retorna o total gasto em todos os cartoes"
  [lista-compras]
  (->>
    lista-compras
    (map :valor)
    (reduce +)
    ))

(defn selecionar-cartao
  "Retorna uma lista de gastos para um cartao especifico"
  [numero-cartao lista-compras]
  (->>
    lista-compras
    (filter #(= (get % :cartao) numero-cartao))
    (map :valor)))

(defn total-gastos-por-cartao
  "Retorna o total gasto em um cartao especifico"
  [numero-cartao lista-compras]
  (->>
    (selecionar-cartao numero-cartao lista-compras)
    (reduce +)
    ))

;-----------------------------------------------------------
;Buscar compras por mês

(defn splitar-mes
  "funcao que transforma a data em um mes (inteiro)"
  [lista-compras]
  (->
    (clojure.string/split lista-compras #"-")
    (get 1)
    (Integer/parseInt)))

(defn obter-compras-por-mes
  "funcao para obter todas as compras baseado em um mes (inteiro)"
  [lista-compras mes]
  (->>
    lista-compras
    (filter #(= mes (splitar-mes (:data %))))))

;-----------------------------------------------------------
;Calcular o total da fatura de um mês

(defn total-gasto-no-mes
  "funcao para calcular o total de gastos em um mes para um cartao especifico"
  [lista-compras mes cartao]
  (->>
    (selecionar-cartao cartao (obter-compras-por-mes lista-compras mes))
    (reduce +)))

;-----------------------------------------------------------
;Agrupar gastos por categoria

(defn obtem-categorias
  "funcao para obter as categorias e os valores individuais"
  [lista-compras]
  (->> lista-compras
       (group-by :categoria)))

(defn agrupar-por-categoria
  "funcao que retorna o total por categoria"
  [lista-compras]
  (into (sorted-map) (map (fn [[categoria compras]]
                            [categoria (total-gastos compras)])
                          (obtem-categorias lista-compras))))

;-----------------------------------------------------------

;Inserir uma nova compra:
(nova-compra "2022-04-10", 85.0, "Alura", "Educação", 3939393939393939)

;Retornar a soma de todas as compras:
(total-gastos lista-compras)

;Retornar o valor das compras para um cartao especifico
(selecionar-cartao 3939393939393939 lista-compras)

;Retornar a soma de todas as compras para um cartao especifico:
(total-gastos-por-cartao 3939393939393939 lista-compras)

;retornar um vetor com todas as compras para um mes especifico
(obter-compras-por-mes lista-compras 4)

;retorna o total da fatura de um cartao para um mes especifico
(total-gasto-no-mes lista-compras 4 3939393939393939)

;retorna as compras agrupadas por categoria
(obtem-categorias lista-compras)

;retorna o valor total por categoria
(agrupar-por-categoria lista-compras)

;Manipulação da coleção de lista de compras:
(count lista-compras)
(pprint lista-compras)
(class lista-compras)

;-----------------------------------------------------------