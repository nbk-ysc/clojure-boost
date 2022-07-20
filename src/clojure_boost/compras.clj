(ns clojure-boost.compras
  (:use clojure.pprint))

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
      :data "2022-04-01"
      :valor 85.0
      :estabelecimento "Alura"
      :categoria "Educação"
      :cartao 3939393939393939
      }
     ])
(defn filtra-cartao
  "Filtra cartão e gera os valores para outra função"
  [cartao lista-compras]
  (->> lista-compras
       (filter #(= (get % :cartao) cartao))
       (map :valor)))

(defn total-gasto [lista-compras]
  "Retorna o valor total gasto nas compras"
  (->> lista-compras
       (map :valor)
       (reduce +)))

(println "Valor total gasto foi" (total-gasto (lista-compras)))

(defn total-gasto-por-cartao [lista-compras cartao]
  "Retorna o valor total gasto nas compras por cartão"
  (let [valor-cartao (filtra-cartao cartao lista-compras)]
  (->> valor-cartao
       (reduce +))))

(println "Valor gasto do cartao" (total-gasto-por-cartao (lista-compras) 3939393939393939))

(defn filtra-mes
  "Filtra as datas"
  [data]
  (-> data
      (str/split #"-")
      (second)
      (Integer/parseInt)
      ))

(defn lista-compras-mes
  "Retorna a lista de compras a partir de um mês"
  [lista-compras mes]
  (->> lista-compras
       (filter #(= mes (filtra-mes (:data %))))))

(println "Lista de compras por mês" (lista-compras-mes (lista-compras) 01))

(defn total-gasto-no-mes
  [lista-compras mes]
  (->> lista-compras
       (filter #(= mes (filtra-mes (:data %))))
       (map :valor)
       (reduce +)))

(println "Total gasto no mês" (total-gasto-no-mes (lista-compras) 01))

(defn agrupar-categoria
  [lista-compras]
  (->> lista-compras
       (group-by :categoria)
       vec))

(defn lista-valores [[categoria valor]]
  {
   :categoria categoria
   :valor (reduce + (map :valor valor))
   }
  )

(defn lista-todas-categorias
  [lista-compras]
  (map lista-valores (agrupar-categoria lista-compras)))

(println "Valor somado de todas as categorias")
(pprint (lista-todas-categorias (lista-compras)))
