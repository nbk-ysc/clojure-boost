(ns clojure-boost.core
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(defn nova-compra [data valor estabelecimento
              categoria cartao]
  {:data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao}
  )

(defn csv-nova-compra [linha]
  (let [[data, valor, estabelecimento, categoria, cartao] linha]
    (nova-compra data  (Double/valueOf valor)
                 estabelecimento categoria cartao)))

(defn lista-compras []
  (with-open [reader (io/reader "src/clojure_boost/compras.csv")]
    (->> (rest (doall (csv/read-csv reader)))
         (map csv-nova-compra)
         (into []))))

(defn total-gasto [compras]
  (->> compras
        (map :valor)
        (reduce +)))

(defn mes-igual?
  [mes compra]
  (let [data (clojure.string/split (:data compra) #"-")
        mesdata (Integer/valueOf (get data 1))]
    (= mes mesdata)))

(defn compras-mes
  [mes compras]
  (filter (fn [x] (mes-igual? mes x)) compras))


(defn cartao-igual? [cartao compras]
  (= cartao (:cartao compras)))

(defn total-gasto-no-mes [mes cartao compras]
  (let[mapas (compras-mes mes compras)]
  (->> mapas
       (filter (fn [x] (cartao-igual? cartao x)))
       (map :valor)
       (reduce +)))
  )

(defn total-gasto-categoria
  [compras]
  (->> compras
       (group-by  (fn [x] (get x :categoria)))
       (map (fn [[categ dados]] {categ (total-gasto dados)}))
       (into {})))

(defn intervalo [min max compras]
  (->> compras
       (filter (fn [x] (if (< min (get x :valor)) true false)))
       (filter (fn [x] (if (> max (get x :valor)) true false)))
       ))
