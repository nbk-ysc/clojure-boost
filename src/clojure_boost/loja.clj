(ns clojure-boost.loja
  (:use clojure.pprint)
  (:require [clojure-boost.utils :as utils]
            [clojure.string :as string]
            [java-time :as jt]))

(def repositorio-de-compras (atom []))

(def lista-compras-atom (atom []))

(defrecord nova-compra [^Long id, ^String data, ^BigDecimal valor,
                        ^String estabelecimento, ^String categoria, ^Long cartao])

(pprint (->nova-compra nil, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))

(defn lista-compras-vazia
  "Lista de compra vazia criada apenas para teste."
  []
  []
  )

(defn gera-lista-vetor
  "Faz a primeira geração de lista para vetor para ser usada em outra função"
  []
  (->> (utils/lista-compras)
       (map vector)
       vec))

(defn filtra-lista-vetor
  "Faz a filtragem de apenas uma compra.
  O campo id será passado no loop que contará a quantidade de compras do csv e gerará os ids automaticamente."
  [lista-vetor id]
  (get lista-vetor id))

(defn retorna-lista
  "Gera a lista com o campo :id"
  [lista-vetor id]
  (->> lista-vetor
       (map #(assoc % :id id))
       (into {})))

(defn gera-lista-compras
  "Executa o loop para gerar os ids automáticos com base na quantidade de compras do mapa.
  Ele passa os ids automáticos por parâmetro para as funções acima.
  Por fim grava em um átomo a lista."
  []
  (let [lista-atualizada (utils/lista-compras)
        quantidade-pos (atom 1)]
         (while (< @quantidade-pos (count lista-atualizada))
           (do
             (swap! lista-compras-atom conj (retorna-lista (filtra-lista-vetor (gera-lista-vetor) @quantidade-pos) @quantidade-pos))
             (swap! quantidade-pos inc)))))

(defn lista-compras
  "Gera a lista de compras a partir do átomo de lista de compras."
  []
  (gera-lista-compras)
  (->> @lista-compras-atom))


(defn gera-id
  "Gera o id novo para uma compra nova"
  [lista-compras]
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
  "Função de teste de inserir compras"
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
  "Gera mapa com compras excluidas"
  [compras id]
  (->> compras
       (remove #(= (:id %) id))
       (into [])))


(defn exclui-compra!
  "Persiste no atom a exlusão da compra"
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
  "Responsável pela validação de cada campo da compra.
   Essas validações estão sendo feitas pelas funções referenciadas."
  (cond
    (not= true (data-formato-correto? compra)) (throw (ex-info "A data não esta no formato ok" {:erro compra}))
    (not= true (valor-e-bigdecimal? compra)) (throw (ex-info "Valor está no formato errado" {:erro compra}))
    (not= true (mais-de-duas-letras-no-estabelecimento? compra)) (throw (ex-info "Estabelecimento precisa ter mais de 2 caracteres" {:erro compra}))
    (not= true (categoria-correta? compra)) (throw (ex-info "Essa categoria não é permitida" {:erro compra}))))

(def compra-temp-teste-valida (->nova-compra nil, "09k-11-1990", 100.0M, "Outback", "Casa", 1000000000000))

(defn insere-compra-teste!
  "Método que insere compra no atom - este é apenas para testar a validação"
  [compra repositorio-de-compra]
  (if (= (valida-compra compra) nil)
    (swap! repositorio-de-compra insere-compra compra)))

(pprint (insere-compra-teste! compra-temp-teste-valida repositorio-de-compras))




