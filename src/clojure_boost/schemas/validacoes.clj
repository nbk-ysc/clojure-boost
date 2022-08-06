(ns clojure-boost.schemas.validacoes
  (:require [java-time :as java-time]
            [clojure-boost.schemas.base :as schemas.base]
            [schema.core :as s]))

(s/defn valor-valido? :- s/Bool
  "Retorna true caso o valor for positivo e do tipo bigDecimal, falso caso for inválido"
  [valor :- s/Num]
  (let [valida-positivo   (pos? valor)
        valida-bigDecimal (decimal? valor)]
    (and valida-positivo valida-bigDecimal)))

(s/defn estabelecimento-valido? :- s/Bool
  "Retorna true se o estabelecimento contém mais de 2 letras, falso caso for inválido"
  [estabelecimento :- s/Str]
  (let [limite-caracteres 2]
    (>= (count estabelecimento) limite-caracteres)))

(s/defn data-menor-or-igual-data-atual? :- s/Bool
  "Retorna true caso a data esteja no padrão java-time, for menor que a data atual, falso caso for maior que a data atual"
  [data :- schemas.base/LocalDate]
  (let [data-atual (java-time/local-date)]
    (java-time/not-after? data data-atual)))

(s/defn limite-numero-do-cartao-valido? :- s/Bool
  "Retorna true caso o numero enviado seja maior ou igual a zero e menor que 10000000000000000, faslo caso for inválido"
  [cartao :- s/Int]
  (let [limite-numero-do-cartao 10000000000000000]
    (and (>= cartao 0) (<= cartao limite-numero-do-cartao))))
