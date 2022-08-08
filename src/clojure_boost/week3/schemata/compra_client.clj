(ns clojure-boost.week3.schemata.compra_client
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [clojure-boost.week1.lista-compras :as lista]))

; É um caminho de validação também, mas pode melhorar utilizando validação por :atributo  e valor
(s/set-fn-validation! true)

(def CompraSchema {:id              s/Num
                   :data            s/Str
                   :valor           s/Num
                   :estabelecimento s/Str
                   :categoria       s/Str
                   :cartão          s/Num})

; Ele explica o que é aquele schaema
;(comment (pprint (s/explain CompraSchema)))

(s/def compra :- CompraSchema {:id              01
                               :data            "2022/01/01"
                               :valor           100M
                               :estabelecimento "Amazon"
                               :categoria       "Casa"
                               :cartão          1234123412341234})


;---------------------------------------------------------------------------------------------------

 ;Validacao por atributo
(s/set-fn-validation! true)

; valida categoria
(def testes ["Alimentacao", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"])
(get testes 1)

(defn valida-categoria [categoria]
  (if (nil? (some #(= categoria %) testes)) false true)); perguntar para o Cácio!!

(valida-categoria "Ifood")

(defn dois-caracteres? [estabelecimento]
  (>= (count estabelecimento) 2))

;(dois-caracteres? "n")

(defn cartao-valido-Int? [cartao]
  (and (> cartao 0)
       (< cartao 10000000000000000)))

(defn dataFormat? [data]
  (re-matches #"\d{4}-\d{2}-\d{2}" data))

(def ValidaData (s/constrained s/Str dataFormat?))
(def ValidaId (s/pred pos-int?))
(def ValidaDecimal (s/constrained BigDecimal pos?))
(def ValidaCartaoAtende (s/constrained s/Int cartao-valido-Int?))
(def ValidaCaracter (s/constrained s/Str dois-caracteres?))
(def ValidarCategoria (s/constrained s/Str valida-categoria))

;(s/validate ValidaData "2022-01-01")

; Depois quero extrair para um arquivo só
(def CompraSchema
  {:id              ValidaId
   :data            ValidaData
   :valor           ValidaDecimal
   :estabelecimento ValidaCaracter
   :categoria       ValidarCategoria
   :cartao          ValidaCartaoAtende})

(s/defn nova-compra :- CompraSchema
  [id :- ValidaId
   data :- ValidaData
   valor :- ValidaDecimal
   estabelecimento :- ValidaCaracter
   categoria :- ValidarCategoria
   cartao :- ValidaCartaoAtende]
  {:id id, :data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria :cartao cartao})

(pprint (nova-compra 02, "2022-01-01", 129M, "CA", "Automóvel", 1231231234123123))

;----------------------------------------------------------------------------------------------------------


(s/def trescompras [{:id              1
                     :data            "2022-01-01"
                     :valor           100
                     :estabelecimento "Outback"
                     :categoria       "Alimentação"
                     :cartao          1234123412341234}
                    {:id              2
                     :data            "2022-01-02"
                     :valor           200
                     :estabelecimento "Dentista"
                     :categoria       "Saúde"
                     :cartao          1234123412341234}
                    {:id              3
                     :data            "2022-02-01"
                     :valor           300
                     :estabelecimento "Cinema"
                     :categoria       "Lazer"
                     :cartao          1234123412341234}])

;(pprint trescompras)

(defn total-gasto [trescompras]
  (if-let [soma (map :valor trescompras)]
    (reduce + soma)))

(total-gasto trescompras)


;(reduce + [1 2 3 4 5])
;(map :valor trescompras))


;----------------------------------------------------------------------------------------------------------
;agrupa valores
(def agrupa-por-categoria [{:id              1
                            :data            "2022-01-01"
                            :valor           100
                            :estabelecimento "Outback"
                            :categoria       "Alimentação"
                            :cartao          1234123412341234}
                           {:id              2
                            :data            "2022-01-02"
                            :valor           200
                            :estabelecimento "Dentista"
                            :categoria       "Lazer"
                            :cartao          1234123412341234}
                           {:id              3
                            :data            "2022-02-01"
                            :valor           300
                            :estabelecimento "Cinema"
                            :categoria       "Lazer"
                            :cartao          1234123412341234}])

(pprint agrupa-por-categoria)



;(defn get-result
;  [coll m]
;  (take-while
;    #(= (:valor :categoria %) m) coll))
;
;(get-result agrupa-por-categoria 100)

;(defn categoria-valida
;  [agrupa-por-categoria]
;  (->> agrupa-por-categoria
;       (map :valor)
;       (reduce +)))

(defn categoria-valida
  [agrupa-por-categoria]
  (group-by :categoria agrupa-por-categoria))

(categoria-valida agrupa-por-categoria)


;--------------------------------------------------------------------------------------------------------------

;Teste de compra válida
;(s/def CompraSchema {:data            s/Str
;                     :valor           s/Num
;                     :estabelecimento s/Str
;                     :categoria       s/Str
;                     :cartão          s/Num})
;
;(s/validate CompraSchema {:data            "2022/01/01"
;                          :valor           100M
;                          :estabelecimento "Amazon"
;                          :categoria       "Casa"
;                          :cartão          1234123412341234})