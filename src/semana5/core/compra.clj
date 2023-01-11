(ns semana5.core.compra
   (:require [clojure.string :as st]
             [java-time.api :as jt]
             [schema.core :as s]))

(s/set-fn-validation! true)

(defn converte-string-para-long [texto]
  (Long. texto))

(defn valor-total-compra [compra]
  (:valor compra))

(defn total-gasto [compras]
  (->> compras
       (map valor-total-compra)
       (reduce +)))

(defn total-compras-agrupada-por-categoria
  [[categoria compras]]
  {categoria (total-gasto compras)})

(defn agrupa-compras-por-categoria
  [compras]
  (->> compras
       (group-by #(:categoria %))
       (map total-compras-agrupada-por-categoria)))

(defn prepara-cartao [cartao]
  (-> cartao
      (st/replace " " "")
      (converte-string-para-long)))

(defn separa-data [data]
  (->> (st/split data #"-")
       (map converte-string-para-long)))

(s/def Data (s/pred #(re-matches #"[0-9]{4}-[0-9]{2}-[0-9]{2}" %)))
(s/def PosNum (s/constrained s/Num pos? 'numero-positivo))
(s/def Estabelecimento (s/constrained
                        s/Str #(>= (count %) 2)))
(s/def Cartao
  (s/constrained Long (fn [x]
                        (and (> x 0) (< x 10000000000000000)))))

(s/def CompraSchema
  {(s/optional-key :id) pos-int?,
   :data Data
   :valor PosNum
   :estabelecimento Estabelecimento
   :categoria (s/enum "Alimentação", "Automóvel",
                      "Casa", "Educação", "Lazer", "Saúde")
   :cartao Cartao})


(s/defn nova-compra :- CompraSchema
  [data, valor, estabelecimento, categoria, cartao]
  (let [[ano mes dia] (separa-data data)]
    {:data            (jt/local-date-time ano mes dia)
     :valor           (bigdec valor)
     :estabelecimento estabelecimento
     :categoria       categoria
     :cartao          (prepara-cartao cartao)}))

