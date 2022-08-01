(ns clojure-boost.week_2.logic
  (:use [clojure.pprint]))

(defrecord compra [ID data valor estabelecimento categoria cartao])
(defrecord compra-otimizada [^Long ID ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^Long cartao])

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
  (->>
    (conj compras (assoc nova-compra :ID (gera-id compras)))
    ))

;---------------------------------------------------------------------------------------------------------
(defn insere-compra
  "Funcao para inserir uma nova compra no atomo."
  [compras nova-compra]
  (swap! compras insere-compra nova-compra)
  )

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

;---------------------------------------------------------------------------------------------------------
