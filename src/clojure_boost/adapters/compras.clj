(ns clojure-boost.adapters.compras
  (:require [clojure-boost.schemas.compras :as schemas.compras]
            [clojure-boost.schemas.base :as schemas.base]
            [clojure-boost.utils :as utils]
            [schema.core :as s]))

(def repositorio-de-compras (atom []))


(defrecord Compra [^Long id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^Long cartao])

(s/defn insere-compra :- schemas.compras/CompraSchemaVetor
  "Retorna uma lista a partir de uma lista de compras e uma nova compra inserida"
  [lista-compras :- schemas.compras/CompraSchemaVetor
   nova-compra :- schemas.compras/CompraSchemaComIdNulo]
  (let [id-nova-compra     (utils/gera-id lista-compras)
        nova-compra-com-id (assoc nova-compra :id id-nova-compra)]
    (conj lista-compras nova-compra-com-id)))

(s/defn insere-compra! :- schemas.compras/CompraSchemaVetor
  "Insere uma nova compra dentro de um atomo"
  [repositorio-de-compras :- schemas.base/Atom
   compra :- schemas.compras/CompraSchemaComIdNulo]
  (swap! repositorio-de-compras insere-compra compra))

(s/defn lista-compras! :- (s/pred nil?)
  "Lista as compras de um atomo"
  [repositorio-de-compras :- schemas.base/Atom]
  (println @repositorio-de-compras))

(s/defn exclui-compra :- schemas.compras/CompraSchemaVetor
  "Recebe lista de compras e id, retornando uma lista sem a compra do id enviado"
  [lista-de-compras :- schemas.compras/CompraSchemaVetor
   id :- Long]
  (->> lista-de-compras
       (filter #(not= (:id %) id))
       vec))

(s/defn exclui-compra! :- schemas.compras/CompraSchemaVetor
  "Exclui a compra por id do atomo enviado"
  [repositorio-de-compras :- schemas.base/Atom
   id :- Long]
  (swap! repositorio-de-compras exclui-compra id))

(s/defn nova-compra :- schemas.compras/CompraSchemaSemId
  "Retorna estrutura de dados de uma compra realizada"
  [data :- schemas.compras/Data
   valor :- schemas.compras/Valor
   estabelecimento :- schemas.compras/Estabelecimento
   categoria :- schemas.compras/Categoria
   cartao :- schemas.compras/Cartao]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})
