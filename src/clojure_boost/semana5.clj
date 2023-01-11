(ns clojure-boost.semana5
  (:require [clojure.pprint :as pprint]
            [schema.core :as s]))

;Para validar os tipos nas funcoes
(s/set-fn-validation! true)

(s/defschema StrCount (s/constrained
                        String (fn [x] (-> x count (>= 2)))))
(s/defschema CardValid
  (s/constrained Long (fn [x]
                          (and (> x 0) (< x 10000000000000000)))))

(s/defschema CompraSchema
  {
   (s/optional-key :id) (s/constrained Long pos-int?)
   :valor  (s/constrained BigDecimal pos?)
   :estabelecimento StrCount
   :categoria (s/enum "Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde")
   :cartao CardValid
   :data (s/pred #(re-matches #"[0-9]{4}-[0-9]{2}-[0-9]{2}" %))
   })

(pprint/pprint (s/explain CompraSchema))

(s/validate CompraSchema {:id 3
                          :valor 999M
                          :estabelecimento "teste"
                          :categoria "Casa"
                          :cartao 9999999
                          :data "2022-12-30"})

(s/defn nova-compra :- CompraSchema
  [data valor estabelecimento
                   categoria cartao]
  {:data data
   :valor (bigdec valor)
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao}
  )

(pprint/pprint
  (nova-compra "2022-12-30" 999 "teste" "Casa" 9999999))

