(ns clojure-boost.semana1)

;Tarefa 1
(defn nova-compra
  "Retorna estrutura de dados de uma compra realizada pelo cartão"
  [data, valor, estabelecimento, categoria, cartao]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

; Invocation
(nova-compra 10, 10, "teste", 11, 12)