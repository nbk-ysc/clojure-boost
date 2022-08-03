(ns clojure-boost.testes
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true]
            [clojure-boost.compras :as compras]
            [clojure-boost.loja :as validacoes]))
(s/set-fn-validation! true)

(defn cartao-correto?
  [cartao]
  (and (> cartao 0) (<= cartao 10000000000000000)))

(def ValorCompra (s/constrained s/Num validacoes/cond-valor-e-bigdecimal?))
(def DataCompra (s/constrained s/Str validacoes/cond-data-formato-correto?))
(def Estabelecimento (s/constrained s/Str validacoes/cond-mais-de-duas-letras-no-estabelecimento?))
(def Categoria (s/constrained s/Str validacoes/cond-categoria-correta?))
(def Cartao (s/constrained s/Int cartao-correto?))

(def CompraSchema {
                   :data  DataCompra
                   :valor ValorCompra
                   :estabelecimento Estabelecimento
                   :categoria Categoria
                   :cartao Cartao})

(println "-----------------------VALIDANDO ENTRADA--------------------------------------")
(s/defn nova-compra :- CompraSchema
  [data :- DataCompra
   valor :- ValorCompra
   estabelecimento :- Estabelecimento
   categoria :- Categoria
   cartao :- Cartao]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao})

(pprint (nova-compra "23-10-1996", 10.0M, "Outback", "Alimentação", 10000000000000000))

(println "-----------------------VALIDANDO SAIDA--------------------------------------")
(s/defn nova-compra :- CompraSchema
  [data, valor, estabelecimento, categoria cartao]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao})

(pprint (nova-compra "23-10-1996", 10.0M, "Outback", "Alimentação", 10000000000000000))

(println "-----------------------VALIDANDO USANDO VALIDATE--------------------------------------")
(defn nova-compra-validate
  [data, valor, estabelecimento, categoria, cartao]
  (s/validate CompraSchema {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria
                            :cartao cartao}))

;(pprint (nova-compra-validate "23-10-1996", 10.0M, "Outback", "Alimentação", 10000000000000000))




























