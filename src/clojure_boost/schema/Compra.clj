(ns clojure-boost.schema.Compra
  (:require [schema.core :as s]))

;; (s/set-fn-validation! true)

(def PosInt (s/constrained Long pos-int?))

(def DataRetroativa (s/constrained String (fn
                                            [valor]
                                            (re-matches #"[0-9]{4}-[0-9]{2}-[0-9]{2}" valor))))

(def ValorValido (s/pred (fn
                           [valor]
                           (and (bigdec valor) (pos? valor)))))

(def EstabelecimentoValido (s/constrained String (fn
                                                   [valor]
                                                   (-> valor
                                                       count
                                                       (>= 2)))))

(def CategoriasValidas (s/enum "Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"))

(def CartaoValido (s/constrained PosInt (fn
                                          [valor]
                                          (and (>= valor 1) (< valor 10000000000000000)))))

(def CompraSchema {(s/optional-key :id) PosInt,
                   :data DataRetroativa,
                   :valor ValorValido,
                   :estabelecimento EstabelecimentoValido,
                   :categoria CategoriasValidas,
                   :cartao CartaoValido})

;; (s/validate CompraSchema {:id 11,
;;                           :data-da-compra "2022-12-20"
;;                           :valor 20.0
;;                           :estabelecimento "Mercado"
;;                           :categoria "Alimentação"
;;                           :cartao 9999999999999999})
