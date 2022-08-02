(ns clojure-boost.semana2.exercicio5(:use (clojure pprint)))


(defrecord Compra [^Long id ^String data ^BigDecimal valor
                   ^String estabelecimento
                   ^String categoria ^Long cartao])
(pprint (->Compra 2 "2022-07-28" 100.0 "Farmácia" "Saúde" 1111222233334444))


(def repositorio-de-compras
  [{:id              1
    :data            "2022-01-01"
    :valor           129.90
    :estabelecimento "Outback"
    :categoria       "Alimentação"
    :cartao          1234123412341234},
   {:id              2
    :data            "2022-01-02"
    :valor           260.00
    :estabelecimento "Dentista"
    :categoria       "Saúde"
    :cartao          1234123412341234}])


(defn gera-id [repositorio-de-compras]
  (if-not (empty? repositorio-de-compras)
    (+ 1 (apply max (map :id repositorio-de-compras)))
    1))

(def compra_efetuada (Compra. nil "2022-01-01" 129.90
                              "Outback" "Alimentação" 123412341234))

(defn insere-compra [lista-compras compra]
  (let [ultimo-id (gera-id lista-compras)]
    (conj lista-compras (assoc compra :id ultimo-id))))

(defn insere-compra! [compraRealizada repositorio-de-compras]
  (swap! repositorio-de-compras insere-compra compraRealizada))

(insere-compra! (Compra. nil "27/07/2022" 100.90 "Outback" "Alimentação" 123412341234) repositorio-de-compras)

(insere-compra (deref repositorio-de-compras) compra_efetuada)

(defn inserir-compra-atomo []
  (let [listas (atom {})]
    (swap! listas assoc Compra compra_efetuada)
    (deref listas)))
(inserir-compra-atomo)


(defn lista-compras! [repositorio-de-compras]
  (println (deref repositorio-de-compras)))

(pprint (lista-compras! repositorio-de-compras))