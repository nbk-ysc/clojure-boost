(ns clojure-boost.schemas.compras
  (:require [clojure-boost.schemas.validacoes :as schemas.validacoes]
            [schema.core :as s]))

(def ^:private categorias #{"Alimentação"
                            "Automóvel"
                            "Casa"
                            "Educação"
                            "Saúde"
                            "Lazer"})

(s/def Valor (s/pred schemas.validacoes/valor-valido?))
(s/def Estabelecimento (s/pred schemas.validacoes/estabelecimento-valido?))
(s/def Data (s/pred schemas.validacoes/data-menor-or-igual-data-atual?))
(s/def Categoria (apply s/enum categorias))
(s/def Cartao (s/pred schemas.validacoes/limite-numero-do-cartao-valido?))
(s/def BigDec (s/pred bigdec))

(s/def CompraSchema
  {:id              Long
   :data            Data
   :valor           Valor
   :estabelecimento Estabelecimento
   :categoria       Categoria
   :cartao          Cartao})

(s/def CompraSchemaVetor [CompraSchema])
(s/def CompraSchemaSemId (dissoc CompraSchema :id))
(s/def CompraSchemaComIdNulo (conj CompraSchemaSemId {:id (s/maybe Long)}))

(s/def TotalGastoPorCategoria
  {Categoria
   Valor})

(s/def AgrupaCategoria
  {Categoria
   CompraSchemaVetor})
