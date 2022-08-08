(ns clojure-boost.loja
  (:use clojure.pprint)
  (:require [clojure-boost.utils :as utils]
            [schema.core :as s
             :include-macros true]
            [clojure-boost.schemas.schemas :as schema]
            [clojure-boost.utils :as utils]
            [clojure-boost.validations.validations_compras :as logic]))
(s/set-fn-validation! true)

(def repositorio-de-compras (atom []))

(def lista-compras-atom (atom []))

(defrecord nova-compra [^Long id, ^String data, ^BigDecimal valor,
                        ^String estabelecimento, ^String categoria, ^Long cartao])

;(pprint (->nova-compra nil, "10/10/1000", 100.0, "Outback", "Alimentação", 1000000000000))

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

(pprint (lista-compras))
(s/defn gera-id
  "Gera o id novo para uma compra nova"
  [lista-compras :- schema/CompraSchemaWithId]
  (if-let [count-id (> (count (map :id lista-compras)) 0)]
    (->> lista-compras
         (map :id)
         (apply max)
         inc)
    1))

(s/defn insere-compra
  [compras :- schema/CompraSchemaWithId compra :- schema/CompraSchemaWithIdOptional]
  (let [id (gera-id compras)
        compra-new (assoc compra :id id)]
    (conj compras compra-new)))

(def compra-temp (->nova-compra nil, "10-10-1000", 100.0M, "Outback", "Alimentação", 1000000000000))
(println "----------------------LISTA DE COMPRAS SEM ATOM-------------------------------------")
(pprint (insere-compra (lista-compras) compra-temp))

(s/defn insere-compra!
  "Método que insere compra no atom"
  [compra :- schema/CompraSchemaWithIdOptional repositorio-de-compra :- schema/Atom?]
  (swap! repositorio-de-compra insere-compra compra))

(insere-compra! compra-temp repositorio-de-compras)

(defn testa-insere-compra
  "Função de teste de inserir compras"
  []
  (let [testando-compra (->nova-compra nil, "10-10-1996", 100.0M, "Outback", "Alimentação", 1000000000000)]
    (insere-compra! testando-compra repositorio-de-compras)))

(testa-insere-compra)

(s/defn lista-compras!
  [repositorio-de-compras :- schema/Atom?]
  (pprint @repositorio-de-compras))

(println "----------------------LISTA DE COMPRAS COM ATOM-------------------------------------")
(lista-compras! repositorio-de-compras)

(println "------------------------------EXCLUINDO COMPRAS-------------------------------------")


(s/defn exclui-compra
  "Gera mapa com compras excluidas"
  [compras :- schema/CompraSchemaWithId id :- Long]
  (->> compras
       (remove #(= (:id %) id))
       (into [])))


(s/defn exclui-compra!
  "Persiste no atom a exlusão da compra"
  [compras id :- Long]
  (swap! compras exclui-compra id))

(pprint (exclui-compra! repositorio-de-compras 1))

(pprint @repositorio-de-compras)

(println "----------------------VALIDANDO COMPRAS COM THROW-------------------------------------")
(def compra-temp-teste-throw (->nova-compra nil, "09-11-1990", 100.0, "Outback", "Casa", 1000000000000))

(defn valida-compra-cond [compra]
  "Responsável pela validação de cada campo da compra.
   Essas validações estão sendo feitas pelas funções referenciadas."
  (cond
    (not= true (logic/cond-data-formato-correto? (:data compra))) (throw (ex-info "A data não esta no formato ok" {:erro compra}))
    (not= true (logic/cond-valor-e-bigdecimal? (:valor compra))) (throw (ex-info "Valor está no formato errado" {:erro compra}))
    (not= true (logic/cond-mais-de-duas-letras-no-estabelecimento? (:estabelecimento compra))) (throw (ex-info "Estabelecimento precisa ter mais de 2 caracteres" {:erro compra}))
    (not= true (logic/cond-categoria-correta? (:categoria compra))) (throw (ex-info "Essa categoria não é permitida" {:erro compra}))
    :else nil))

;(pprint (valida-compra {:id 1, :data "09-99-2222", :valor 100.0M, :estabelecimento "Outback", :categoria "Alimentação"
;                        :cartao 1000000000000}))

(s/defn insere-compra-teste!
  "Método que insere compra no atom - este é apenas para testar a validação"
  [compra :- schema/CompraSchemaWithIdOptional repositorio-de-compra :- schema/Atom?]
  (if (= (valida-compra-cond compra) nil)
    (swap! repositorio-de-compra insere-compra compra)))

;(pprint (insere-compra-teste! compra-temp-teste-throw repositorio-de-compras))

(println "----------------------VALIDANDO COMPRAS COM MAPAS-------------------------------------")

(def compra-temp-teste-maps (->nova-compra nil, "09-11-1990", 100.0M, "Outback", "Casa", 1000000000000))


(s/defn data-formato-correto? [compra]
  (if (= (re-matches #"[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]" compra) nil)
    {:data-incorreta "Data está no formato incorreto."}
    {:data-incorreta false}))

(s/defn valor-e-bigdecimal? [compra]
  (let [valor compra]
    (if (and (not= true (decimal? valor) (> valor 0)))
      {:valor-incorreto "Valor está no formato errado"}
      {:valor-incorreto false})))

(s/defn mais-de-duas-letras-no-estabelecimento? [compra]
  (let [estabelecimento compra]
    (if (not= true (> (count estabelecimento) 2))
      {:estabelecimento-errado "Estabelecimento está com menos de 2 caracteres!"}
      {:estabelecimento-errado false})))

(s/defn categoria-correta? [compra]
  (let [categoria-permitida ["Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"]]
    (if (not= true (.contains categoria-permitida compra))
      {:categoria-incorreta "A categoria está incorreta."}
      {:categoria-incorreta false})))

(s/defn valida-compra
  "Função responsável por gerar os mapas com os erros"
  [compra :- schema/CompraSchemaWithIdOptional]
  (let [erros [(data-formato-correto? (:data compra)), (valor-e-bigdecimal? (:valor compra)),
               (mais-de-duas-letras-no-estabelecimento? (:estabelecimento compra)), (categoria-correta? (:categoria compra))]]
    (->> erros
         (into {})
         vals
         (filter #(not= false %)))))

(s/defn insere-compra-teste!
  "Função que insere compra no atom - este é apenas para testar a validação"
  [compra :- schema/CompraSchemaWithIdOptional repositorio-de-compra :- schema/Atom?]
  (if (= (valida-compra compra) ())
    (swap! repositorio-de-compra insere-compra compra))
  (valida-compra compra))

(insere-compra-teste! compra-temp-teste-maps repositorio-de-compras)

(pprint @repositorio-de-compras)