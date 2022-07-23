(ns clojure-boost.utils
  (:require
    [java-time :as jt]
    [clojure.string :as str]
    [ultra-csv.core :as csv]))


(defn nova-compra
  "Retorna uma nova compra"
  [data valor estabelecimento categoria cartao]
  [{
    :data data
    :valor valor
    :estabelecimento estabelecimento
    :categoria categoria
    :cartao cartao
    }])

(defn formata-data-cartao
  "Função responsável por tratar data ano mês e dia"
  [data formato-data-atual formato-data-nova]
  (->> data
       (jt/year-month formato-data-atual)
       (jt/format formato-data-nova)))

(defn filtra-mes
  "Filtra as datas"
  [data]
  (-> data
      (str/split #"-")
      (second)
      (Integer/parseInt)))

(defn agrupar-categoria
  "Função responsável por agrupar por categoria"
  [lista-compras]
  (->> lista-compras
       (group-by :categoria)))

(defn lista-compras []
  "retorna lista via csv"
  (csv/read-csv "compras.csv"
                {:field-names [:data :valor :estabelecimento :categoria :cartao]}))

(defn lista-cartoes []
  "retorna lista de cartoes via csv"
  (csv/read-csv "cartoes.csv"
                {:field-names [:numero :cvv :validade :limite :cliente]}))

(defn formata-data
  "Função responsável por tratar data ano mês"
  [data formato-data-atual formato-data-nova]
  (->> data
       (jt/local-date formato-data-atual)
       (jt/format formato-data-nova)))

(defn formata-data-cartao-mes
  "Função responsável por tratar data ano mês e dia gerando apenas mês
  Essa função está sendo usada no caso de filtrar por mês"
  [data]
  (->> data
       (jt/local-date "yyyy-MM-dd")
       (jt/format "MM")
       (Integer/parseInt)))

(defn valida-formatador
  "Faz a validação se for apenas ano e mês usar a função que trata isso.
  Se for ano, mês e dia, usar a outra função"
  [data formato-data-atual formato-data-nova]
  (if (< (count (str/split data #"-")) 3) (formata-data-cartao data formato-data-atual formato-data-nova)
                                          (formata-data data formato-data-atual formato-data-nova))
  )

(defn retorna-data-atualizada
  "Função responsável por retornar a data formatada para os vetores"
  [lista chave formato-data-atual formato-data-nova]
  (->> lista
       (map #(update % chave (fn [x] (valida-formatador x formato-data-atual formato-data-nova))))
       ))