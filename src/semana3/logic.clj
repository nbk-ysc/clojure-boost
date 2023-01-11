(ns semana3.logic
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [clojure.string :as st]
            [java-time.api :as jt]))


(defn converte-string-para-long [texto]
  (Long. texto))

(defn prepara-cartao [cartao]
  (-> cartao
      (st/replace " " "")
      (converte-string-para-long)))

(defn separa-data [data]
  (->> (st/split data #"-")
       (map converte-string-para-long)))

(defn nova-compra
  [data, valor, estabelecimento, categoria, cartao]
  (let [[ano mes dia] (separa-data data)]
    {:data            (jt/local-date-time ano mes dia)
     :valor           (bigdec valor)
     :estabelecimento estabelecimento
     :categoria       categoria
     :cartao          (prepara-cartao cartao)}))

(defn popula-e-retorna-nova-compra [row]
 (let [[data, valor, estabelecimento, categoria, cartao] row]
   (nova-compra data, valor, estabelecimento, categoria, cartao)))

(defn lista-compras []
  (with-open [reader (io/reader "resources/compras.csv")]
    (->> (rest (doall (csv/read-csv reader)))
         (map popula-e-retorna-nova-compra)
         (into []))))

(defn valor-total-compra [compra]
  (:valor compra))

(defn total-gasto [compras]
  (->> compras
       (map valor-total-compra)
       (reduce +)))

(defn verifica-mes-compra
  [mes compra]
  (= (jt/month (:data compra)) (jt/month mes)))

(defn verifica-cartao-compra
  [cartao compra]
  (= (:cartao compra) cartao))

(defn busca-compras-por-mes
  [mes compras]
  (filter #(verifica-mes-compra mes %) compras))

(defn busca-compras-por-cartao
  [cartao compras]
  (filter #(verifica-cartao-compra cartao %) compras))

(defn total-gasto-por-cartao-e-mes
  [mes cartao compras]
  (->> compras
       (busca-compras-por-cartao (if (instance? String cartao) (prepara-cartao cartao) cartao))
       (busca-compras-por-mes mes)
       (total-gasto)))


(defn total-compras-agrupada-por-categoria
  [[categoria compras]]
  {categoria (total-gasto compras)})

(defn agrupa-compras-por-categoria
  [compras]
  (->> compras
       (group-by #(:categoria %))
       (map total-compras-agrupada-por-categoria)))

(defn verifica-intervalo-de-valor [max min compra]
  (and (>= (:valor compra) min) (<= (:valor compra) max))
)

(defn busca-compras-por-intervalo-de-valor
  [max min compras]
  (->> compras
       (filter #(verifica-intervalo-de-valor max min %))))


