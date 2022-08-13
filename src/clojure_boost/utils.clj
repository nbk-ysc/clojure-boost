(ns clojure-boost.utils
  (:require [ultra-csv.core :as csv]
            [schema.core :as s]
            [java-time :as java-time]
            [clojure-boost.schemas.base :as schemas.base]))

(defn ler-csv
  "Retorna dados a partir de um arquivo csv, deve enviar path do arquivo e o nome dos campos na mesma ordem do cabeçalho"
  [arquivo nome-campos]
  (csv/read-csv arquivo {:field-names nome-campos}))

(s/defn numero-maximo-da-lista-incrementado :- s/Int
  "A partir do id maximo da lista retorna o mesmo incrementando 1"
  [seq :- schemas.base/SeqInt]
  (if (not-empty seq)
    (->> seq
         (apply max)
         inc)
    1))

(s/defn gera-id :- s/Int
  "Gera id para cadastro a partir da lista, caso a lista esteja vazia o número será 1"
  [vetor :- (s/pred vector?)]
  (if (not-empty vetor)
    (->> vetor
         (map :id)
         numero-maximo-da-lista-incrementado)
    1))

(defn convert-date-inst-date
  [data]
  (->> (.format (java.text.SimpleDateFormat. "yyyy-MM-dd") data)
       (java-time/local-date)))
