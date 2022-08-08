(ns clojure-boost.week2.repo-compras
  (:use clojure.pprint))

(def repositorio-de-compras (atom []))
(comment (pprint repositorio-de-compras))

(defrecord compra [^Long id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^long cartao])
(comment (pprint (->compra nil "2022-01-01" 10.00 "Ifood" "Alimentação" 1234123412341234)))

(def lista-compras
  [{:id              02
    :data            "2022-01-01"
    :valor           200.00
    :estabelecimento "Restaurante Japonês"
    :categoria       "Alimentacao"
    :cartao          1234123412341234}
   {:id              03
    :data            "2022-01-01"
    :valor           200.00
    :estabelecimento "Restaurante Japonês"
    :categoria       "Alimentacao"
    :cartao          1234123412341234}
   {:id              10
    :data            "2022-01-01"
    :valor           200.00
    :estabelecimento "Restaurante Japonês"
    :categoria       "Alimentacao"
    :cartao          1234123412341234}])

(pprint lista-compras)

(defn gera-id [compra]
  (if-not (empty? compra)
    (+ 1 (apply max (map :id compra)))
    1))
(comment (pprint gera-id))

; Insere compra
(defn insere-compra [lista-compras compra]
  (let [id  (gera-id lista-compras)
        nova-compra (assoc compra :id id)]
    (conj lista-compras nova-compra)))
(comment (pprint (insere-compra lista-compras (->compra 10 "2022-01-01" 10.00 "Ifood" "Alimentação" 1234123412341234))))

; Insere compra! no atomo
(defn insere-compra! [repositorio-de-compras compra]
  (swap! repositorio-de-compras insere-compra compra))
(comment (pprint (insere-compra! repositorio-de-compras (->compra 10 "2022-01-01" 10.00 "Ifood" "Alimentação" 1234123412341234))))
(pprint @repositorio-de-compras)

; listar compras do átomo
(defn lista-compra! [repositorio-de-compras]
  (pprint repositorio-de-compras))
(pprint (lista-compra! @repositorio-de-compras))

; Excluir compra na lista
(defn exclui-compra [lista-compras id-compra]
  (->> lista-compras
       (remove #(= (:id %) id-compra))
       (vec)))
(pprint (exclui-compra lista-compras 2))

; Excluir compra! no atomo
(defn exclui-compra! [repositorio-de-compras id-para-exclusao]
  (swap! repositorio-de-compras exclui-compra id-para-exclusao))
(comment (pprint (exclui-compra! repositorio-de-compras 1)))

; Validar cadastro de compra
(defn valida-compra [compra]
  (let [data-valida (re-matches #"\d{4}-\d{2}-\d{2}" (:data compra))
        valida-compra (> (:valor compra) 0)]
    (if (and data-valida valida-compra))))
(pprint (valida-compra (->compra 10 "2022-31-01" 10.00 "Ifood" "Alimentação" 1234123412341234)))


