(ns clojure-boost.filters)
(require '[clojure.string :as str])

(defn card-filtro
  "Função de filtro por cartão de crédito"
  [compras cartao]
  (filter #(= cartao (:cartao %)) compras)
  )

(defn mes-da-data [data]
  (-> (str/split data #"-")
      (get 1)
      (Integer/parseInt)))