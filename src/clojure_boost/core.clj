(ns clojure-boost.core)

(defn nova-compra [data valor estabelecimento
              categoria cartao]
  {:data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao}
  )
; Teste setmap
(nova-compra "data" 30 "estab" "categ" "cartao")

(defn lista-compras []
  [(nova-compra "2022-01-01" 129.90 "Outback" "Alimentação" 1234123412341234)
   (nova-compra "2022-01-02" 260.00 "Dentista" "Saúde" 1234123412341234)
   (nova-compra "2022-02-01" 20.00 "Cinema" "Lazer" 1234123412341234)
   (nova-compra "2022-01-10" 150.00 "Show" "Lazer" 4321432143214321)
   (nova-compra "2022-02-10" 289.99 "Posto de Gasolina" "Automóvel" 4321432143214321)
   (nova-compra "2022-02-20" 79.90 "iFood" "Alimentação" 4321432143214321)
   (nova-compra "2022-03-01" 85.00 "Alura" "Educação" 4321432143214321)
   (nova-compra "2022-01-30" 85.00 "Alura" "Educação" 1598159815981598)
   (nova-compra "2022-01-31" 350.00 "Tok&Stok" "Casa" 1598159815981598)
   (nova-compra "2022-02-01" 400.00 "Leroy Merlin" "Casa" 1598159815981598)
   (nova-compra "2022-03-11" 25.90 "Dogão" "Alimentação" 3939393939393939)
   (nova-compra "2022-03-12" 215.87 "Praia" "Lazer" 3939393939393939)
   (nova-compra "2022-04-01" 976.88 "Oficina" "Automóvel" 3939393939393939)
   (nova-compra "2022-04-10" 85.00 "Alura" "Educação" 3939393939393939)
   (nova-compra "2022-03-01" 50.00 "Madero" "Alimentação" 1598159815981598)
   (nova-compra "2022-03-01" 70.00 "Teatro" "Lazer" 1598159815981598)
   (nova-compra "2022-03-04" 250.00 "Hospital" "Saúde" 1598159815981598)
   (nova-compra "2022-04-10" 130.00 "Drogaria" "Saúde" 1598159815981598)
   (nova-compra "2022-03-10" 100.00 "Show de pagode" "Lazer" 3939393939393939)
])

(defn total-gasto [compras]
  (->> compras
        (map :valor)
        (reduce +)))

; teste total-gasto
(total-gasto (lista-compras))

(defn mes-igual?
  [mes compra]
  (let [data (clojure.string/split (:data compra) #"-")
        mesdata (Integer/valueOf (get data 1))]
    (= mes mesdata)))

(defn compras-mes
  [mes compras]
  (filter (fn [x] mes-igual? mes x) compras))

; teste compras-mes
(compras-mes 1 (lista-compras))

(defn cartao-igual? [cartao compras]
  (= cartao (:cartao compras)))

(defn total-gasto-no-mes [mes cartao compras]
  (->>(compras-mes mes compras)
      (filter (fn [x] cartao-igual? cartao x))
      (reduce +))
  )


(defn total-gasto-por-categoria
  [compras]
  (->> compras
       (group-by #(get % :categoria))
       (map (fn [[key vals]] {key (total-gasto vals)}))
       (into {})))