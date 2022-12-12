(ns clojure-boost.aula)

(defn nova-compra
  [data, valor, estabelecimento, categoria, cartao]
  {:data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao})

(defn lista-compras []
  [(nova-compra "2022-01-01" 129.90 "Outback" "Alimentação" 1234123412341234),
   (nova-compra "2022-01-02" 260.00 "Dentista" "Saúde" 1234123412341234)
   (nova-compra "2022-02-01" 20.00 "Cinema" "Lazer" 1234123412341234)
   (nova-compra "2022-01-10" 150.00 "Show" "Lazer" 4321432143214321)
   (nova-compra "2022-02-10" 289.99 "Posto de Gasolina" "Automóvel" 4321432143214321)
   (nova-compra "2022-02-20" 79.90 "iFood" "Alimentação" 4321432143214321)
   (nova-compra "2022-03-01" 85.00 "Alura" "Educação" 4321432143214321)
   (nova-compra "2022-01-30" 85.00 "Alura" "Educação" 1598159815981598)
   (nova-compra "2022-01-31" 350.00 "Tok&Stok" "Casa" 1598159815981598)
   (nova-compra "2022-02-01" 400.00 "Leroy Merlin" "Casa" 1598159815981598)
   (nova-compra "2022-03-01" 50.00 "Madero" "Alimentação" 1598159815981598)
   (nova-compra "2022-03-01" 70.00 "Teatro" "Lazer" 1598159815981598)
   (nova-compra "2022-03-04" 250.00 "Hospital" "Saúde" 1598159815981598)
   (nova-compra "2022-04-10" 130.00 "Drogaria" "Saúde" 1598159815981598)
   (nova-compra "2022-03-10" 100.00 "Show de pagode" "Lazer" 3939393939393939)
   (nova-compra "2022-03-11" 25.90 "Dogão" "Alimentação" 3939393939393939)
   (nova-compra "2022-03-12" 215.87 "Praia" "Lazer" 3939393939393939)
   (nova-compra "2022-04-01" 976.88 "Oficina" "Automóvel" 3939393939393939)
   (nova-compra "2022-04-10" 85.00 "Alura" "Educação" 3939393939393939)])

(defn total-gasto [compras]
  (->> compras
       (map :valor)
       (reduce +)))

(defn mes-data [data]
  (let [data-split (clojure.string/split data #"-")]
    (Long/valueOf (get data-split 1))))

(defn mes-compra [compra]
  (mes-data (:data compra)))

(defn mes-igual?
  [mes compra]
  (= mes (mes-compra compra)))

(defn mes-maior-igual?
  [mes compra]
  (>= mes (mes-compra compra)))

(defn mes-menor-igual?
  [mes compra]
  (<= mes (mes-compra compra)))

(defn compras-mes
  [mes compras]
  (filter #(mes-igual? mes %) compras))

(defn total-compras-mes
  [mes compras]
  (->> (compras-mes mes compras)
       (total-gasto)))

(defn gastos-por-categoria [compras]
  (let [map-categoria (group-by :categoria compras)
        chaves (keys map-categoria)]
    (reduce (fn [acc curr] (assoc acc curr (total-gasto (get map-categoria curr)))) {} chaves)))

(defn compra-em-intervalo?
  [compra min max]
  (if (and (mes-menor-igual? (mes-data max) compra) (mes-maior-igual? (mes-data min) compra))
    true))

(defn compras-intervalo
  [compras min max]
  (filter #(compra-em-intervalo? % min max) compras))

(println (nova-compra "2022-12-07" 47.50 "loja" "livro" 7567))
(println (lista-compras))
(println (total-gasto (lista-compras)))
(println (mes-igual? 1 (get (lista-compras) 1)))
(println (compras-mes 1 (lista-compras)))
(println (total-compras-mes 1 (lista-compras)))
(println (gastos-por-categoria (lista-compras)))
(println (compras-intervalo (lista-compras) "2022-03-01" "2022-04-10"))