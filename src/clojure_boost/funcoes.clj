(ns clojure-boost.funcoes
  (:require clojure.java.io
            clojure.data.csv))

(defn nova-compra
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
