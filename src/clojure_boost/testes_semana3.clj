(ns clojure-boost.testes_semana3
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true]
            [clojure-boost.schemas.schemas :as schema]))
(s/set-fn-validation! true)

(println "-----------------------VALIDANDO ENTRADA--------------------------------------")
(s/defn nova-compra :- schema/CompraSchema
  [data :- schema/DataCompra
   valor :- schema/ValorCompra
   estabelecimento :- schema/Estabelecimento
   categoria :- schema/Categoria
   cartao :- schema/Cartao]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao})

;(pprint (nova-compra "23-10/1996", 10.0M, "Outback", "Alimentação", 10000000000000000))

(println "-----------------------VALIDANDO SAIDA--------------------------------------")
(s/defn nova-compra :- schema/CompraSchema
  [data, valor, estabelecimento, categoria cartao]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao})

;(pprint (nova-compra "23-10/1996", 10.0M, "Outback", "Alimentação", 10000000000000000))

(println "-----------------------VALIDANDO USANDO VALIDATE--------------------------------------")
(defn nova-compra-validate
  [data, valor, estabelecimento, categoria, cartao]
  (s/validate schema/CompraSchema {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria
                            :cartao cartao}))

(pprint (nova-compra-validate "23-10-1996", 10.0M, "Outback", "Saúde", 10000000000000000))


(def compra-temp [{:id 1, :data "23-10-1995", :valor 10.0M, :estabelecimento "Outback",
                   :categoria "Alimentação", :cartao 1000000000}])




















