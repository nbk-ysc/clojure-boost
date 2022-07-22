(ns clojure-boost.utils
  (:require
    [java-time :as jt]
    [clojure.string :as str]))

(defn formata-data-cartao
  [data formato-data-atual formato-data-nova]
  (->> data
       (jt/year-month formato-data-atual)
       (jt/format formato-data-nova)))

(defn formata-data
  [data formato-data-atual formato-data-nova]
  (->> data
       (jt/local-date formato-data-atual)
       (jt/format formato-data-nova)))

(defn formata-data-cartao-mes
  [data]
  (->> data
       (jt/local-date "yyyy-MM-dd")
       (jt/format "MM")
       (Integer/parseInt)))

(defn valida-formatador
  [data formato-data-atual formato-data-nova]
  (if (< (count (str/split data #"-")) 3) (formata-data-cartao data formato-data-atual formato-data-nova)
                                          (formata-data data formato-data-atual formato-data-nova))
  )

(defn retorna-data-atualizada
  [lista chave formato-data-atual formato-data-nova]
  (->> lista
       (map #(update % chave (fn [x] (valida-formatador x formato-data-atual formato-data-nova))))
       ))