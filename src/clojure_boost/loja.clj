(ns clojure-boost.loja
  (:use clojure.pprint)
  (:require [clojure-boost.utils :as utils]
            [clojure.string :as string]
            [java-time :as jt]))

(def repositorio-de-compras (atom []))

(defrecord nova-compra [^Long id, ^String data, ^BigDecimal valor,
                        ^String estabelecimento, ^String categoria, ^Long cartao])

(pprint (->nova-compra 10.0, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))

(defn lista-compras-vazia []
  []
  )

(defn lista-compras []
  [{
    :id              1,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              2,
    :data            "10/10/1000",
    :valor           150.0,
    :estabelecimento "Tordesilhas",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              3,
    :data            "10/10/1000",
    :valor           10.0,
    :estabelecimento "DOM",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              4,
    :data            "10/10/1000",
    :valor           1000.0,
    :estabelecimento "MCDonalds",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              10,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              6,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }
   {
    :id              22,
    :data            "10/10/1000",
    :valor           100.0,
    :estabelecimento "Outback",
    :categoria       "Alimentação",
    :cartao          1000000000000
    }])

(defn gera-id [lista-compras]
  (if-let [count-id (> (count (map :id lista-compras)) 0)]
    (->> lista-compras
         (map :id)
         (apply max)
         inc)
    1))

(defn insere-compra
  [compras compra]
  (let [id (gera-id compras)
        compra-new (assoc compra :id id)]
    (conj compras compra-new)
    ))



(def compra-temp (->nova-compra nil, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))
(println "----------------------LISTA DE COMPRAS SEM ATOM-------------------------------------")
(pprint (insere-compra (lista-compras) compra-temp))


(defn insere-compra!
  "Método que insere compra no atom"
  [compra repositorio-de-compra]
  (swap! repositorio-de-compra insere-compra compra))

(insere-compra! compra-temp repositorio-de-compras)

(defn testa-insere-compra
  "Testa o insere compras"
  []
  (def compra-temp-teste (->nova-compra nil, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))
  (def compra-temp-teste2 (->nova-compra nil, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))
  (def compra-temp-teste3 (->nova-compra nil, "10/10/1000", 100.0, "Mc donalds", "Alimentação", 1000000000000))

  (insere-compra! compra-temp-teste repositorio-de-compras)
  (insere-compra! compra-temp-teste2 repositorio-de-compras)
  (insere-compra! compra-temp-teste3 repositorio-de-compras))
(testa-insere-compra)

(defn lista-compras!
  [repositorio-de-compras]
  (pprint @repositorio-de-compras))
(println "----------------------LISTA DE COMPRAS COM ATOM-------------------------------------")
(lista-compras! repositorio-de-compras)

(println "----------------------EXCLUINDO COMPRAS-------------------------------------")


(defn exclui-compra
  [compras id]
  (->> compras
       (remove #(= (:id %) id))
       (into [])))


(defn exclui-compra!
  [compras id]
  (swap! compras exclui-compra id)
  )

(pprint (exclui-compra! repositorio-de-compras 1))

(pprint @repositorio-de-compras)

(println "----------------------VALIDANDO COMPRAS-------------------------------------")


(defn data-formato-correto? [compra]
  (->> compra
       (:data)
       (re-matches #"[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]")
       (not= nil)))

(defn valor-e-bigdecimal? [compra]
  (let [valor (:valor compra)]
    (and (decimal? valor)
     (> valor 0))))

(defn mais-de-duas-letras-no-estabelecimento? [compra]
  (let [estabelecimento (:estabelecimento compra)]
    (> (count estabelecimento) 2)))

(defn categoria-correta? [compra]
  (let [categoria-permitida {"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"}]
    (->> compra
         (:categoria)
         (contains? categoria-permitida))))

(defn valida-compra [compra]
  (cond
    (not= true (data-formato-correto? compra)) (throw (ex-info "A data não esta no formato ok" {:erro compra}))
    (not= true (valor-e-bigdecimal? compra)) (throw (ex-info "Valor está no formato errado" {:erro compra}))
    (not= true (mais-de-duas-letras-no-estabelecimento? compra)) (throw (ex-info "Estabelecimento precisa ter mais de 2 caracteres" {:erro compra}))
    (not= true (categoria-correta? compra)) (throw (ex-info "Essa categoria não é permitida" {:erro compra}))))

(def compra-temp-teste-valida (->nova-compra nil, "09k-11-1990", 100.0M, "Outback", "Casa", 1000000000000))

(defn insere-compra-teste!
  "Método que insere compra no atom"
  [compra repositorio-de-compra]
  (if (= (valida-compra compra) nil)
    (swap! repositorio-de-compra insere-compra compra)))

(pprint (insere-compra-teste! compra-temp-teste-valida repositorio-de-compras))




