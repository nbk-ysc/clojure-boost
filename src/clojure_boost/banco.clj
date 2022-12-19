(ns clojure-boost.banco)

(def repositorio-de-compras (atom []))

(defrecord Compra [id data valor estabelecimento categoria cartao])
