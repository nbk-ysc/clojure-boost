(ns clojure-boost.utils
  (:require [ultra-csv.core :as csv])
  (:import [java.text SimpleDateFormat]))

(defn ler-csv
  "Retorna dados a partir de um arquivo csv, deve enviar tbm o nome dos campos na mesma ordem do cabeçalho"
  [arquivo nome-campos]
  (csv/read-csv arquivo {:field-names nome-campos}))

(defn formata-data
  "Retorna a string da data formatada conforme enviado no simbolo formato-novo"
  [data-string formato-atual formato-novo]
  (->> data-string
       (.parse (SimpleDateFormat. formato-atual))
       (.format (SimpleDateFormat. formato-novo))))

(defn atualiza-formato-data
  "Atualiza dentro da lista o campo data enviado, conforme novo padrão de formatação no simbolo formato-novo"
  [lista chave formato-atual formato-novo]
  (update lista chave #(formata-data % formato-atual formato-novo)))