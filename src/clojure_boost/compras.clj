(ns clojure-boost.compras)


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


;(println (nova-compra "2022-07-19" 100.0 "Batata company" "Alimentação" 45282180898765432))

(defn lista-compras []
  "Retorna todas as compras realizadas"
    [{
      :data "2022-01-01"
      :valor 129.90
      :estabelecimento "Outback"
      :categoria "Alimentação"
      :cartao 1234123412341234
      }
     {
      :data "2022-01-02"
      :valor 260.00
      :estabelecimento "Dentista"
      :categoria "Saúde"
      :cartao 1234123412341234
      }
     {
      :data "2022-02-01"
      :valor 20.00
      :estabelecimento "Cinema"
      :categoria "Lazer"
      :cartao 1234123412341234
      }
     {
      :data "2022-01-10"
      :valor 150.00
      :estabelecimento "Show"
      :categoria "Lazer"
      :cartao 4321432143214321
      }
     {
      :data "2022-02-10"
      :valor 289.99
      :estabelecimento "Posto de gasolina"
      :categoria "Automóvel"
      :cartao 4321432143214321
      }
     {
      :data "2022-02-20"
      :valor 79.90
      :estabelecimento "iFood"
      :categoria "Alimentação"
      :cartao 4321432143214321
      }
     {
      :data "2022-03-01"
      :valor 85.00
      :estabelecimento "Alura"
      :categoria "Educação"
      :cartao 4321432143214321
      }
     {
      :data "2022-01-30"
      :valor 85.00
      :estabelecimento "Alura"
      :categoria "Educação"
      :cartao 1598159815981598
      }
     {
      :data "2022-01-31"
      :valor 350.00
      :estabelecimento "Tok&Stok"
      :categoria "Casa"
      :cartao 1598159815981598
      }
     {
      :data "2022-02-01"
      :valor 400.00
      :estabelecimento "Leroy Merlin"
      :categoria "Casa"
      :cartao 1598159815981598
      }
     {
      :data "2022-03-01"
      :valor 50.00
      :estabelecimento "Madero"
      :categoria "Alimentação"
      :cartao 6655665566556655
      }
     {
      :data "2022-03-01"
      :valor 70.00
      :estabelecimento "Teatro"
      :categoria "Lazer"
      :cartao 6655665566556655
      }
     {
      :data "2022-03-04"
      :valor 250.00
      :estabelecimento "Hospital"
      :categoria "Saúde"
      :cartao 6655665566556655
      }
     {
      :data "2022-04-10"
      :valor 130.0
      :estabelecimento "Drogaria"
      :categoria "Saúde"
      :cartao 6655665566556655
      }
     {
      :data "2022-03-10"
      :valor 100.0
      :estabelecimento "Show de pagode"
      :categoria "Lazer"
      :cartao 3939393939393939
      }
     {
      :data "2022-03-11"
      :valor 25.9
      :estabelecimento "Dogão"
      :categoria "Alimentação"
      :cartao 3939393939393939
      }
     {
      :data "2022-03-12"
      :valor 215.87
      :estabelecimento "Praia"
      :categoria "Lazer"
      :cartao 3939393939393939
      }
     {
      :data "2022-04-01"
      :valor 976.88
      :estabelecimento "Oficina"
      :categoria "Automóvel"
      :cartao 3939393939393939
      }
     {
      :data "2022-04-10"
      :valor 85.0
      :estabelecimento "Alura"
      :categoria "Educação"
      :cartao 3939393939393939
      }
     ])

(defn total-gasto [lista-compras]
  "Retorna o valor total gasto nas compras"
  (->> lista-compras
       (map :valor)
       (reduce +)))

(println "Valor gasto foi" (total-gasto (lista-compras)))

(defn lista-compras-mes
  [compras mes]
  (filter (fn [compras] (= (get compras :data) mes)) compras))

(println (lista-compras-mes (lista-compras) "2022-04-01"))













