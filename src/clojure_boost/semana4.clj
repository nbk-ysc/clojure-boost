(ns clojure-boost.semana4)

(def repositorio-de-compras (atom []))

(defrecord compra [id data valor estabelecimento categoria cartao])


