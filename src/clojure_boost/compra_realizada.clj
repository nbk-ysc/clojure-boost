(ns clojure-boost.compra-realizada
  (:require [clojure-boost.lista-compras :as lista]))

;Neste cenário, eu retorno um determinado cartão mas sem mostrar o nuúmero do cartao para a segurancça do cliente ou não?

(def compra {:cartão {:data            "2022-01-01"
                      :valor           129.90
                      :estabelecimento "Outback"
                      :categoria       "Alimentação"
                      :cartão          1234123412341234}})

(defn nova-compra [compra]
  (println "Compra" (class compra) compra))

(println (map nova-compra compra))

;(defn nova-compra
;  [numerocartao cartoes]
;  (->> cartoes
;       (filter #(= (get % :cartao) numerocartao))
;       (map :valor)))

;(println (nova-compra 1234123412341234 lista/lista-compras))