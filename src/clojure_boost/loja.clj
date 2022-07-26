(ns clojure-boost.loja
  (:use clojure.pprint)
  (:require [clojure-boost.utils :as utils]))

(defn lista-de-compras
  []
  (let [repositorio-de-compras (atom [])]
    (pprint repositorio-de-compras)))

(lista-de-compras)

(defrecord nova-compra [^Long id, ^String data, ^BigDecimal valor,
                        ^String estabelecimento, ^String categoria, ^Long cartao])

;(pprint (->nova-compra 10.0, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))

(defn lista-compras-vazia []
  []
  )

(defn lista-compras []
  [{
    :id              1,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              2,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              3,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              4,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              10,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   ]
  )

(defn gera-id [lista-compras]
  (if-let [count-id (> (count (map :id lista-compras)) 0)]
    (->> lista-compras
         (map :id)
         (apply max)
         inc)
    1))

;(println (gera-id (lista-compras-vazia)))
(defn insere-compra
  [compra compras]
  (let [id (gera-id compras)
        compra-new (assoc compra :id id)]
    (conj compras compra-new)
    ))


(def compra-temp (->nova-compra nil, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))


(pprint (insere-compra compra-temp (lista-compras)))

