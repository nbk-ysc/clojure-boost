(ns clojure-boost.loja
  (:use clojure.pprint)
  (:require [clojure-boost.utils :as utils]))

(def repositorio-de-compras (atom []))

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
    :valor           150.0,
    :estabelecimento "Tordesilhas",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              3,
    :data            "10/10/1000",
    :valor           10.0,
    :estabelecimento "DOM",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              4,
    :data            "10/10/1000",
    :valor           1000.0,
    :estabelecimento "MCDonalds",
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
   {
    :id              6,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              22,
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

(defn insere-compra
  [compra compras]
  (let [id (gera-id compras)
        compra-new (assoc compra :id id)]
    (conj compras compra-new)
    ))


(def compra-temp (->nova-compra nil, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))

;(pprint (insere-compra compra-temp (lista-compras)))


(defn insere-compra!
  [compra repositorio-de-compra]
  (swap!  repositorio-de-compra  conj compra))

(defn insere-compra-atom
  [compra compras repositorio-de-compras]
  (let [id (gera-id compras)
        compra-new (assoc compra :id id)]
    (insere-compra! (conj compras compra-new) repositorio-de-compras)
    ))

(insere-compra-atom compra-temp (lista-compras) repositorio-de-compras)

;(pprint repositorio-de-compras)

(defn lista-compras!
  [repositorio-de-compras]
  (pprint @repositorio-de-compras))

(lista-compras! repositorio-de-compras)

;(pprint (->> @repositorio-de-compras
;             comp)

;
;(pprint (->> (lista-compras)
;             (into ())))
;

