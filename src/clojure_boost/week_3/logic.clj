(ns clojure-boost.week_3.logic
  (:use [clojure.pprint])
  (:require [schema.core :as s]
            [clojure-boost.week_2.logic :as logic.week_2]))

(s/set-fn-validation! true)

(def DataValida (s/pred logic.week_2/data-valida? 'data-invalida))
(def BigDecPositivo (s/pred logic.week_2/valor-e-bigdec? 'nao-eh-bigdec-positivo))
(def CategoriaValida (s/pred logic.week_2/categoria-valida? 'categoria-nao-existe))
(def NomeEstabelecimento (s/pred logic.week_2/nome-do-estabelecimento-e-valido? 'numero-de-caracteres-inferior-a-dois))
(def CartaoAtende (s/constrained s/Int logic.week_2/numeracao-do-cartao-atende? 'numeracao-incorreta))

;(s/validate DataValida nil)

;----------------------------------------------------------------------------

(s/defschema CompraSchema
  {:ID              Long
   :data            DataValida
   :valor           BigDecPositivo
   :estabelecimento NomeEstabelecimento
   :categoria       CategoriaValida
   :cartao          CartaoAtende})

(s/def CompraSchemaVetor [CompraSchema])
(s/def CompraSchemaSemId (dissoc CompraSchema :ID))

;(s/defrecord compra [ID :- s/Int
;                     data :- DataValida
;                     valor :- BigDecPositivo
;                     estabelecimento :- NomeEstabelecimento
;                     categoria :- CategoriaValida
;                     cartao :- CartaoAtende])

;----------------------------------------------------------------------------

(s/defn nova-compra :- CompraSchemaSemId
  "Funcao para definir a estrutura de uma nova compra"
  [data :- DataValida,
   valor :- BigDecPositivo,
   estabelecimento :- NomeEstabelecimento,
   categoria :- CategoriaValida,
   cartao :- CartaoAtende]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartao          cartao})

;----------------------------------------------------------------------------