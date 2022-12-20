(ns clojure-boost.semana4)

(def repositorio-de-compras (atom []))

(defrecord Compra [id data valor estabelecimento categoria cartao])
