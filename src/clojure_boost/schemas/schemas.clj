(ns clojure-boost.schemas.schemas
  (:require [schema.core :as s
             :include-macros true]
            [clojure-boost.validations.validations_compras :as logic]))

(defn cartao-correto?
  [cartao]
  (and (> cartao 0) (<= cartao 10000000000000000)))


(def ValorCompra (s/constrained s/Num logic/cond-valor-e-bigdecimal?))
(def DataCompra (s/constrained s/Str logic/cond-data-formato-correto?))
(def Estabelecimento (s/constrained s/Str logic/cond-mais-de-duas-letras-no-estabelecimento?))
(def Categoria (s/constrained s/Str logic/cond-categoria-correta?))
(def Cartao (s/constrained s/Int cartao-correto?))
(def Atom? clojure.lang.Atom)


(def CompraSchema {
                   :data  DataCompra
                   :valor ValorCompra
                   :estabelecimento Estabelecimento
                   :categoria Categoria
                   :cartao Cartao})


(def ListaCompraSchema [CompraSchema])

(def CompraSchemaWithId [{
                          :id              Long
                          :data            DataCompra
                          :valor           ValorCompra
                          :estabelecimento Estabelecimento
                          :categoria       Categoria
                          :cartao          Cartao}])

(def CompraSchemaWithIdOptional
  (assoc CompraSchema (s/optional-key :id) (s/maybe Long)))
