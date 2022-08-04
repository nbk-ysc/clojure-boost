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
  [])

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
    (conj compras compra-new)))

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

(println "------------------------------EXCLUINDO COMPRAS-------------------------------------")


(defn exclui-compra
  "Gera mapa com compras excluidas"
  [compras id]
  (->> compras
       (remove #(= (:id %) id))
       (into [])))


(defn exclui-compra!
  "Persiste no atom a exlusão da compra"
  [compras id]
  (swap! compras exclui-compra id))

(pprint (exclui-compra! repositorio-de-compras 1))

(pprint @repositorio-de-compras)

(println "----------------------VALIDANDO COMPRAS COM THROW-------------------------------------")
(def compra-temp-teste-throw (->nova-compra nil, "09-11-1990", 100.0, "Outback", "Casa", 1000000000000))


(defn cond-data-formato-correto?
  [compra]
  (not= nil (re-matches #"[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]" compra)))

(defn cond-valor-e-bigdecimal? [compra]
  (let [valor compra]
    (and (decimal? valor)
         (> valor 0))))
(defn cond-mais-de-duas-letras-no-estabelecimento?
  [compra]
  (let [estabelecimento compra]
    (> (count estabelecimento) 2)))

(defn cond-categoria-correta?
  [compra]
  (let [categoria-permitida {"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"}]
    (contains? categoria-permitida compra)))

(defn valida-compra-cond [compra]
  "Responsável pela validação de cada campo da compra.
   Essas validações estão sendo feitas pelas funções referenciadas."
  (cond
    (not= true (cond-data-formato-correto? (:data compra))) (throw (ex-info "A data não esta no formato ok" {:erro compra}))
    (not= true (cond-valor-e-bigdecimal? (:valor compra))) (throw (ex-info "Valor está no formato errado" {:erro compra}))
    (not= true (cond-mais-de-duas-letras-no-estabelecimento? (:estabelecimento compra))) (throw (ex-info "Estabelecimento precisa ter mais de 2 caracteres" {:erro compra}))
    (not= true (cond-categoria-correta? (:categoria compra))) (throw (ex-info "Essa categoria não é permitida" {:erro compra}))
    :else nil))

;(pprint (valida-compra {:id 1, :data "09-99-2222", :valor 100.0M, :estabelecimento "Outback", :categoria "Alimentação"
;                        :cartao 1000000000000}))

(defn insere-compra-teste!
  "Método que insere compra no atom - este é apenas para testar a validação"
  [compra repositorio-de-compra]
  (if (= (valida-compra-cond compra) nil)
    (swap! repositorio-de-compra insere-compra compra)))

;(pprint (insere-compra-teste! compra-temp-teste-throw repositorio-de-compras))

(println "----------------------VALIDANDO COMPRAS COM MAPAS-------------------------------------")

(def compra-temp-teste-maps (->nova-compra nil, "09-11-199a0", 100.0, "O", "Casad", 1000000000000))


(defn data-formato-correto? [compra]
  (if (= (re-matches #"[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]" compra) nil)
    {:data-incorreta "Data está no formato incorreto."}
    {:data-incorreta false}))

(defn valor-e-bigdecimal? [compra]
  (let [valor compra]
    (if (and (not= true (decimal? valor) (> valor 0)))
      {:valor-incorreto "Valor está no formato errado"}
      {:valor-incorreto false})))

(defn mais-de-duas-letras-no-estabelecimento? [compra]
  (let [estabelecimento compra]
    (if (not= true (> (count estabelecimento) 2))
      {:estabelecimento-errado "Estabelecimento está com menos de 2 caracteres!"}
      {:estabelecimento-errado false})))

(defn categoria-correta? [compra]
  (let [categoria-permitida {"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"}]
    (if (not= true (contains? categoria-permitida compra))
      {:categoria-incorreta "A categoria está incorreta."}
      {:categoria-incorreta false})))

(defn valida-compra
  "Função responsável por gerar os mapas com os erros"
  [compra]
  (let [erros [(data-formato-correto? (:data compra)), (valor-e-bigdecimal? (:valor compra)),
               (mais-de-duas-letras-no-estabelecimento? (:estabelecimento compra)), (categoria-correta? (:categoria compra))]]
    (->> erros
         (into {})
         vals
         (filter #(not= false %)))))

(defn insere-compra-teste!
  "Função que insere compra no atom - este é apenas para testar a validação"
  [compra repositorio-de-compra]
  (if (= (valida-compra compra) ())
    (swap! repositorio-de-compra insere-compra compra))
  (valida-compra compra))

(pprint (insere-compra-teste! compra-temp-teste-maps repositorio-de-compras))
