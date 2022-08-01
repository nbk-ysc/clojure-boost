(ns clojure-boost.logic
  (:require [java-time :as java-time]
            [clojure-boost.logic :as logic]
            [clojure-boost.model.mensagens :as mensagens]))

(defn id-maximo-da-lista-incrementado
  "A partir do id maximo da lista retorna o mesmo incrementando 1"
  [lista]
  (->> lista
       (map :id)
       (apply max)
       inc))

(defn gera-id
  "Gera id para cadastro a partir da lista, caso a lista esteja vazia o número será 1"
  [lista]
  (if (not-empty lista)
    (id-maximo-da-lista-incrementado lista)
    1))

(defn valor-valido?
  "Retorna true caso o valor for positivo e do tipo bigDecimal, falso caso for inválido"
  [valor]
  (let [valida-positivo   (pos? valor)
        valida-bigDecimal (decimal? valor)]
    (and valida-positivo valida-bigDecimal)))

(defn estabelecimento-valido?
  "Retorna true se o estabelecimento contém mais de 2 letras, falso caso for inválido"
  [estabelecimento]
  (let [limite-caracteres 2]
    (>= (count estabelecimento) limite-caracteres)))

(defn data-menor-or-igual-data-atual?
  "Retorna true caso a data, no padrão java-time, for menor que a data atual, falso caso for maior que a data atual"
  [data]
  (let [data-atual (java-time/local-date)]
    (java-time/not-after? data data-atual)))

(defn categoria-valida?
  "Retorna true caso a categoria for valida, falso caso for inválido "
  [categoria]
  (let [categorias-validas ["Alimentação", "Automóvel", "Casa", "Educação", "Saúde", "Lazer"]]
    (.contains categorias-validas categoria)))

(defrecord Response [status campo valor mensagem])

(defn formata-response-validacao
  "Retorna um vetor com o resultado das validações das compras enviadas, no padrao do record Response"
  [compra]
  [(->Response (logic/valor-valido? (:valor compra)) :valor (:valor compra) mensagens/valor)
   (->Response (logic/data-menor-or-igual-data-atual? (:data compra)) :data (:data compra) mensagens/data)
   (->Response (logic/estabelecimento-valido? (:estabelecimento compra)) :estabelecimento (:estabelecimento compra) mensagens/estabelecimento)
   (->Response (logic/categoria-valida? (:categoria compra)) :categoria (:categoria compra) mensagens/categoria)])

(defn valida-compra
  "Retorna um vetor com os erros que tiverem nas validações da compra, caso não tenha volta vazio"
  [compra]
  (->> (logic/formata-response-validacao compra)
       (filter #(= (:status %) false))
       vec))