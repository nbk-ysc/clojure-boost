(ns clojure-boost.semana2.logic
  (:use [clojure pprint])
  (:require [java-time :as jt]))


(defn valid-date? [date]
  (let [now (jt/local-date)
        date-test (if (instance? String date)
                    (jt/local-date "dd/MM/yyyy" date)
                    date)]
    (if (jt/not-before? now date-test)
      true
      (throw (ex-info "Oops! Something went wrong with date!" {})))))

(defn valid-value? [value]
  (if (and (= (bigdec value) value) (>= (bigdec value) 0))
    true
    (throw (ex-info "Oops! Something went wrong with value!" {}))))

(defn valid-establishment? [estabelecimento]
  (if (>= (count estabelecimento) 2) true
       (throw (ex-info "Algum erro ocorreu com esse estabelecimento!" {}))))

(defn valid-category? [category]
  (if (some #(= category %)
            ["Alimentação"
             "Automóvel"
             "Casa"
             "Educação"
             "Lazer"
             "Saúde"])
    true
    (throw (ex-info "Algum erro ocorreu com essa categoria!" {}))))


(defrecord CompraRealizada [^Long id, ^String data, ^BigDecimal valor, ^String estabelecimento, ^String categoria, ^Long cartao])


(defn valida-compra [CompraRealizada]
  (valid-date? (:data CompraRealizada))
  (valid-value? (:valor CompraRealizada))
  (valid-establishment? (:estabelecimento CompraRealizada))
  (valid-category? (:categoria CompraRealizada)))


(defn gera-id [lista-compras]
  (let [ultima-compra (last (sort-by :id lista-compras))]
    (if (= ultima-compra nil) 1
                              (inc (:id ultima-compra)))))

(defn insere-compra [lista-compras compra]
  (let [ultimo-id (gera-id lista-compras)]
    (conj lista-compras (assoc compra :id ultimo-id))))

(defn exclui-compra [lista-compras id]
  (remove #(= (:id %) id) lista-compras))