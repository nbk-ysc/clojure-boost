(ns clojure-boost.utils
  (:require [ultra-csv.core :as csv]))

(defn ler-csv
  "Retorna dados a partir de um arquivo csv, deve enviar path do arquivo e o nome dos campos na mesma ordem do cabeçalho"
  [arquivo nome-campos]
  (csv/read-csv arquivo {:field-names nome-campos}))