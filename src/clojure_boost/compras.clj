(ns clojure-boost.compras
  (:use clojure.pprint)
  (:require [clojure-boost.logic :as logic]
            [clojure-boost.utils :as utils]
            [java-time :as java-time]))

(def repositorio-de-compras (atom []))

(defrecord Compra [^Long id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^Long cartao])

;(pprint (->Compra nil (java-time/local-date 2022 07 28), 100.99M, "Outback", "Lazer", 3939393939393939))

(defn lista-compras []
  "Retorna lista de compras formatada com a data padrão dd/MM/yyyy e campo valor como bigDecimal"
  (let [arquivo "arquivos/compras.csv"
        campos  [:id :data, :valor, :estabelecimento, :categoria, :cartao]]
    (->> (utils/ler-csv arquivo campos)
         (map #(update % :data java-time/local-date))
         (map #(update % :valor bigdec))
         vec)))

(defn insere-compra
  "Retorna uma lista a partir de uma lista de compras e uma nova compra inserida"
  [lista-compras nova-compra]
  (let [id-nova-compra     (logic/gera-id lista-compras)
        nova-compra-com-id (assoc nova-compra :id id-nova-compra)]
    (conj lista-compras nova-compra-com-id)))

;(pprint (insere-compra (lista-compras) (->Compra 10 (java-time/local-date 2022 7 29), 129.90M, "Outback", "Alimentaçã", 1234123412341234)))
;(pprint (insere-compra [] (->Compra 10 (java-time/local-date 2022 1 01), 129.90M, "Outback", "Alimentação", 1234123412341234)))

(defn insere-compra!
  "Insere uma nova compra dentro de um atomo"
  [repositorio-de-compras compra]
  (let [dados-de-validacao (logic/valida-compra compra)]
    (if (empty? dados-de-validacao)
      (swap! repositorio-de-compras insere-compra compra)
      dados-de-validacao)))

(defn lista-compras!
  "Lista as compras de um atomo"
  [repositorio-de-compras]
  (pprint @repositorio-de-compras))

(defn exclui-compra
  "Recebe lista de compras e id, retornando uma lista sem a compra do id enviado"
  [lista-de-compras id]
  (->> lista-de-compras
       (filter #(not= (:id %) id))))

(defn exclui-compra!
  "Exclui a compra por id do atomo enviado"
  [repositorio-de-compras id]
  (swap! repositorio-de-compras exclui-compra id))


(defn testador-compras-no-repositorio-de-compras
  "Função apenas para testes dos inputs"
  [repositorio-de-compras]
  (pprint (insere-compra!
           repositorio-de-compras
           (->Compra nil (java-time/local-date 2022 8 02), -300.90M, "a", "limentação", 1234123412341234)))

  (insere-compra!
   repositorio-de-compras
   (->Compra nil (java-time/local-date 2022 7 28), 100.99M, "Outback", "Lazer", 3939393939393939))

  (insere-compra!
   repositorio-de-compras
   (->Compra nil (java-time/local-date 2022 7 28), 300.90M, "Outback", "Alimentação", 1234123412341234))

  (exclui-compra! repositorio-de-compras 1)

  (lista-compras! repositorio-de-compras))

;(testador-compras-no-repositorio-de-compras repositorio-de-compras)