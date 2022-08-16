(ns week3.clojure_boost_week3
  (:require [clojure.pprint :as p]
            [schema.core :as s]
            [clojure.spec.alpha :as a]
            )
  )

(s/set-fn-validation! true)

(def categoria ["Alimentação", "Saúde", "Casa", "Lazer", "Educação", "Automóvel"])

(def Id-Valido (s/pred pos-int?))

(defn conta-caractere-estabelecimento
  "Conta os caracteres do estabelecimento para validação"
  [estabelecimento]
  (>=  (count estabelecimento) 2)
  )

(def Estabelecimento-Valido (s/pred conta-caractere-estabelecimento 'estabelecimento))

(defn valida-categoria
  "Verifica se a categoria está entre as permitidas"
  [categoria-nova]
  (some #(= categoria-nova %) categoria)
  )

(def Categoria-Valida (s/pred valida-categoria 'categoria-nova))

(defn verifica-cartao-valido
  "verifica se o cartao esta valido"
  [cartao]
  (a/int-in-range? 1 10000000000000000 cartao)
  )

(def Cartao-Valido (s/pred verifica-cartao-valido 'cartao))

(defn verifica-valor-valido
  "Verifica o valor valido da compra"
  [valor]
  (if (pos? valor)
    (decimal? valor)
    false
    )
  )

(def Valor-Valido (s/pred verifica-valor-valido 'valor))

(def ComprasSchema "Define Schema de Compra"
{:id Id-Valido :data s/Str :valor Valor-Valido :estabelecimento Estabelecimento-Valido :categoria Categoria-Valida :cartao Cartao-Valido})

(s/defn nova-compra :- ComprasSchema
  "Valida Schema de Compras"
    [id :- Id-Valido
    data :- s/Str
    valor :- Valor-Valido
    estabelecimento :- Estabelecimento-Valido
    categoria :- Categoria-Valida
    cartao :- Cartao-Valido]
  {:id id, :data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao}
  )

(println (nova-compra 1, "26/02/2022", 5M, "Starbucks", "Alimentação", 1234123412341234))
