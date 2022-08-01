(ns clojure-boost.week2.new-problem
  (:use clojure.pprint))

;lista-vazia
(def lista-compras
  [])

(defrecord CompraRealizada [id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^long cartao])
(def compra (CompraRealizada. 1 "27/07/2022" 100.90 "Outback" "Alimentação" 123412341234))

;(:id (last lista-compras))
;(inc (:id (last lista-compras)))
;(assoc compra :id (inc (:id (last lista-compras))))

(defn insere-compra [lista-compras compra]
  (let [nova-compra (assoc compra :id (inc (:id (last lista-compras))))]
    (conj lista-compras nova-compra)))

(defn insere-compra! [compra repositorio-de-compras]
  (swap! repositorio-de-compras insere-compra compra)
  (pprint compra))
