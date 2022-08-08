(ns clojure-boost.week_3.logic
  (:use [clojure.pprint])
  (:require [schema.core :as s]
            [clojure-boost.week_2.logic :as logic.week_2]))

(s/set-fn-validation! true)

(def DataValida (s/pred logic.week_2/data-valida? '"Valid Date"))
(def BigDecPositivo (s/pred logic.week_2/valor-e-bigdec? '"BigDec ou Positivo"))
(def CategoriaValida (s/pred logic.week_2/categoria-valida? '"Categoria previamente registrada"))
(def NomeEstabelecimento (s/pred logic.week_2/nome-do-estabelecimento-e-valido? '"Numeracao minima de 2 caracteres"))
(def CartaoAtende (s/constrained s/Int logic.week_2/numeracao-do-cartao-atende? '"correct number"))

;----------------------------------------------------------------------------

(s/defschema CompraSchema
  {:ID              s/Int
   :data            DataValida
   :valor           BigDecPositivo
   :categoria       CategoriaValida
   :estabelecimento NomeEstabelecimento
   :cartao          CartaoAtende})

;----------------------------------------------------------------------------
(s/defn nova-compra :- CompraSchema
  "Funcao para inserir uma nova compra e atribuir um ID para essa compra caso seja necessario.
  A Funcao espera receber uma compra e um vetor de compras"
  [nova-compra :- CompraSchema]
  nova-compra)

;(s/defn nova-compra :- CompraSchema
;  "Funcao para inserir uma nova compra e atribuir um ID para essa compra caso seja necessario.
;  A Funcao espera receber uma compra e um vetor de compras"
;  [compras nova-compra :- CompraSchema]
;  (->>
;    (assoc nova-compra :ID (logic.week_2/gera-id compras))
;    ))

;(s/defn nova-compra :- CompraSchema
;  "Funcao para inserir uma nova compra e atribuir um ID para essa compra caso seja necessario.
;  A Funcao espera receber uma compra e um vetor de compras"
;  [ID :- s/Int,
;   data :- DataValida,
;   valor :- BigDecPositivo,
;   categoria :- CategoriaValida,
;   estabelecimento :- NomeEstabelecimento,
;   cartao :- CartaoAtende]
;  {:ID              ID,
;   :data            data,
;   :valor           valor,
;   :categoria       categoria,
;   :estabelecimento estabelecimento,
;   :cartao          cartao})

(s/defrecord compra [ID :- s/Int
                     data :- DataValida
                     valor :- BigDecPositivo
                     estabelecimento :- NomeEstabelecimento
                     categoria :- CategoriaValida
                     cartao :- CartaoAtende])

;----------------------------------------------------------------------------