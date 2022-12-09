(ns clojure-boost.funcoes)

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao})
