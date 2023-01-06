(ns clojure-boost.funcoes
  (:require clojure.data.csv
            clojure.java.io
            clojure.string
            [schema.core :as s]
            [clojure-boost.schema.Compra :refer [CompraSchema]]))

(s/set-fn-validation! true)

(s/defn nova-compra :- CompraSchema
  [[data valor estabelecimento categoria cartao]]
  {:data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao})

(defn lista-compras
  []
  (let [dados-das-compras
        (into []
              (with-open [reader (clojure.java.io/reader "compras.csv")]
                (doall (clojure.data.csv/read-csv reader))))]

    (->> dados-das-compras
         (rest)
         (map nova-compra)
         (into []))))

(defn total-gasto [compras]
  (let [valores (into [] (map :valor compras))]

    (->> valores
         (map #(Float/parseFloat %))
         (reduce +)
         (float))))

(defn busca-compras-do-mes
  [mes lista-de-compras]
  ;; (into [] (filter #(= mes (:data %)) lista-de-compras))
  (into [] (filter #(clojure.string/includes? (subs (:data %) 5 7) (str mes)) lista-de-compras)))

(defn total-gasto-no-mes
  [mes]
  (total-gasto (busca-compras-do-mes mes (lista-compras))))

(defn total-gasto-por-categoria
  [compras]
  (->> compras
       (group-by #(get % :categoria))
       (map (fn [[key vals]] {key (total-gasto vals)}))
       (into {})))

(defn filtrar-compras-por-intervalo-de-valores
  [valor-minimo valor-maximo]
  (let [compras (lista-compras)]
    (filter
     #(and (>= (Float/parseFloat (:valor %)) valor-minimo)
           (<= (Float/parseFloat (:valor %)) valor-maximo)) compras)))
