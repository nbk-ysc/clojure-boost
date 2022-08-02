(ns clojure-boost.semana2.exercicio3
  (:use (clojure pprint))
  (:require [java-time :as jt])
  (:import (jogamp.graph.font.typecast.ot.table ID)))

;Tarefa
;Criar a função insere-compra. Ela vai atribuir um id a uma compra e armazená-la num vetor.
;
;Parâmetros da função:
;um record de uma compra sem id;
;um vetor com as compras já cadastradas..
;Retorno da função:
;um vetor com a nova compra inserida nele.
;Critérios de aceitação:
;O ID da nova compra deve ser o valor máximo de ID da lista de compras mais 1;
;Se a lista de compras estiver vazia, o ID deve ser o valor 1.

(defrecord Compra [^Long id ^String data ^BigDecimal valor
                   ^String estabelecimento
                   ^String categoria ^Long cartao])
(pprint (->Compra 2 "2022-07-28" 100.0 "Farmácia" "Saúde" 1111222233334444))


(def repositorio-de-compras
    [{:id              1
      :data            "2022-01-01"
      :valor           129.90
      :estabelecimento "Outback"
      :categoria       "Alimentação"
      :cartao          1234123412341234},
     {:id              2
      :data            "2022-01-02"
      :valor           260.00
      :estabelecimento "Dentista"
      :categoria       "Saúde"
      :cartao          1234123412341234}])

(defn gera-id [repositorio-de-compras]
  (if-not (empty? repositorio-de-compras)
    (+ 1 (apply max (map :id repositorio-de-compras)))
    1))

(defn insere-compra [repositorio-de-compras nova-compra]
  (let [id-gerado (gera-id repositorio-de-compras) ; passa as compras pra gerar id
        compra-com-id (assoc nova-compra :id id-gerado)]
    (conj repositorio-de-compras compra-com-id)))

(pprint (insere-compra repositorio-de-compras
(->Compra nil "2022-07-31" 150.0 "Farmácia" "Saúde" 1111222233334444)))








;(def repositorio-de-compras
;  (atom
;    [{:data            (jt/local-date "2022-01-01")
;      :valor           129.90
;      :estabelecimento "Outback"
;      :categoria       "Alimentação"
;      :cartao          1234123412341234},
;     {:data            (jt/local-date "2022-01-02")
;      :valor           260.00
;      :estabelecimento "Dentista"
;      :categoria       "Saúde"
;      :cartao          1234123412341234}]))