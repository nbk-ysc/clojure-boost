(ns clojure-boost.week_2.logic
  (:use [clojure.pprint])
  (:require [java-time :as jt]
            [clojure-boost.week_2.utils :as utils.week_2]))

(defrecord compra [ID data valor estabelecimento categoria cartao])

;-------------------------------------------------------------------------------------------------------------------
(defn gera-id
  "Funcao para gerar um ID de compra.
  Ela espera receber um vetor"
  [compras]
  (let [id-gerado (last (sort (map #(get % :ID) compras)))]
    (if (nil? id-gerado)
      (int 1)
      (inc id-gerado))))

(defn insere-compra
  "Funcao para inserir uma nova compra e atribuir um ID para essa compra caso seja necessario.
  A Funcao espera receber uma compra e um vetor de compras"
  [compras nova-compra]
    (conj compras (assoc nova-compra :ID (gera-id compras))))

;---------------------------------------------------------------------------------------------------------
(defn lista-compras!
  "Funcao para listar as compras de um atomo"
  [compras]
  (pprint @compras)
  )

;---------------------------------------------------------------------------------------------------------
(defn exclui-compra
  "Funcao para excluir uma compra.
  Ela espera receber um ID e a um vetor"
  [compras ID]
  (vec (remove #(= (get % :ID) ID) compras))
  )

(defn exclui-compra!
  "Funcao para excluir uma compra de um atomo.
  Ela espera receber um ID e a um vetor"
  [compras ID]
  (swap! compras exclui-compra ID)
  )

;-------------------------------------------------------------------------------------------------------------------
;
(defn categoria-valida?
  "Valida se a categoria está entre as permitidas"
  [nova-compra]
  (let [categoria nova-compra]
    (not (nil? (some #(= categoria %) utils.week_2/categorias)))))

(defn nome-do-estabelecimento-e-valido? [nova-compra]
  (let [caracteres nova-compra]
    (>= (count caracteres) 2)))

(defn valor-e-bigdec?
  [nova-compra]
  "Valida se o valor é um bigDec positivo"
  (let [valor nova-compra]
    (and (decimal? valor)
         (pos? valor))))

(defn data-valida?
  "Valida se a data não está no futuro"
  [nova-compra]
  (let [data nova-compra]
    (jt/not-after? data (jt/local-date))))

(defn numeracao-do-cartao-atende?
  "Validacao da numeracao do cartao"
  [cartao]
  (and (> cartao 0) (< cartao 10000000000000000))
   )

(defn valida-compra
  "Funcao para validar se uma compra pode ser inserida
  * A data da compra deve ser menor ou igual à data atual
  * O valor deve um BigDeecimal positivo
  * O estabelecimento precisa ter pelo menos dois caracteres
  * A categoria precisa respeitar um grupo pré-determinado"
  [nova-compra]
  (cond
    (not= true (categoria-valida? (:categoria nova-compra))) (throw (ex-info "A categoria utilizada não é permitida" {:erro nova-compra}))
    (not= true (nome-do-estabelecimento-e-valido? (:estabelecimento nova-compra))) (throw (ex-info "O nome de estabelecimento possui menos de 2 caracteres" {:erro nova-compra}))
    (not= true (valor-e-bigdec? (:valor nova-compra))) (throw (ex-info "O valor está no formato incorreto ou não é positivo" {:erro nova-compra}))
    (not= true (data-valida? (:data nova-compra))) (throw (ex-info "Data utilizada está no futuro" {:erro nova-compra}))))

;---------------------------------------------------------------------------------------------------------
(defn insere-compra!
  "Funcao para inserir uma nova compra no atomo."
  [compras nova-compra]
  (if (nil? (valida-compra nova-compra))
    (swap! compras insere-compra nova-compra)))