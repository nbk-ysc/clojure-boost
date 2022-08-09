(ns clojure-boost.persistencia.datomic.compras
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure-boost.persistencia.datomic.db :as db]))

(def conn (db/cria-conexao!))

(db/cria-schema conn)

(defn insere-compra [id, data, valor, estabelecimento, categoria, cartao]
  {:compra/id              id,
   :compra/data            data,
   :compra/valor           valor,
   :compra/estabelecimento estabelecimento,
   :compra/categoria       categoria,
   :compra/cartao          cartao})

(defn insere-compra! [db compra]
    (d/transact db [compra]))

(defn lista-compras! [db]
  (d/q '[:find (pull ?id [:compra/id, :compra/data, :compra/valor, :compra/estabelecimento, :compra/categoria,
                          :compra/cartao])
         :where [?id :compra/id]] db))

(defn lista-compras-por-cartao! [db cartao]
  (d/q '[:find (pull ?id [:compra/id, :compra/data, :compra/valor, :compra/estabelecimento, :compra/categoria,
                      :compra/cartao])
         :in $ ?cartao
         :where [?id :compra/cartao ?cartao]] db cartao))
;insere uma compra nova
;(pprint (insere-compra! conn (insere-compra 4, "11-11-1990", 100.11M, "Outback", "Alimentação", 2939393939393939)))

;Lista todas as compras
(pprint (lista-compras! (d/db conn)))

;Lista por cartão
(pprint (lista-compras-por-cartao! (d/db conn) 2939393939393939))

