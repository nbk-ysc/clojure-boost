(ns clojure-boost.week5
  (:require [schema.core :as s])
  )

(s/def PosInt (s/constrained Long pos-int?))
(s/def Moeda (s/constrained BigDecimal pos?))
(s/def EsbelecimentoValido (s/constrained String #(-> % count (>= 2))))
(s/def CategoriaValida (s/enum "Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"))
(s/def CartaoValido (s/constrained Long #(and (pos-int? %) (<= % 10000000000))))
(s/def DataRetroativa (s/pred #(re-matches #"[0-9]{4}-[0-9]{2}-[0-9]{2}" %)))
(s/def Compra {(s/optional-key :id) PosInt
               :valor Moeda
               :estabelecimento EsbelecimentoValido
               :categoria CategoriaValida
               :cartao CartaoValido
               :data DataRetroativa
               })

(s/defn cria-compra [id :- PosInt] :- Compra)

(s/defn nova-compra :- Compra
  "Retorna lista de compras em formato de Schema"
  [valor :- Moeda estabelecimento :- EsbelecimentoValido categoria :- CategoriaValida cartao :- CartaoValido data :- DataRetroativa]
  {:valor valor :estabelecimento estabelecimento :categoria categoria :cartao cartao :data data}
  )

(println (s/validate Compra (nova-compra (bigdec 2000) "Lala" "Alimentação" 32323 "1222-02-20")))