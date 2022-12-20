(ns clojure-boost.week2
  (:use clojure.pprint))

(defn nova-compra
  "Retorna lista de compras"
  [data valor estabelecimento categoria cartao]
  {
   :data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao
   }
  )

(defn lista-compras []
  [{:data            "2022-01-01"
    :valor           129.90
    :estabelecimento "Outback"
    :categoria       "Alimentação"
    :cartao          1234123412341234}
   {:data            "2022-01-02"
    :valor           260.00
    :estabelecimento "Dentista"
    :categoria       "Saúde"
    :cartao          1234123412341234}
   {:data            "2022-01-03"
    :valor           20.00
    :estabelecimento "Cinema"
    :categoria       "Lazer"
    :cartao          1234123412341234}
   {:data            "2022-01-04"
    :valor           150.00
    :estabelecimento "Show"
    :categoria       "Lazer"
    :cartao          4321432143214321}
   {:data            "2022-01-05"
    :valor           289.99
    :estabelecimento "Posto de gasolina"
    :categoria       "Automóvel"
    :cartao          4321432143214321}
   {:data            "2022-01-06"
    :valor           79.90
    :estabelecimento "iFood"
    :categoria       "Alimentação"
    :cartao          4321432143214321}
   {:data            "2022-01-07"
    :valor           85.00
    :estabelecimento "Alura"
    :categoria       "Educação"
    :cartao          4321432143214321}
   {:data            "2022-01-08"
    :valor           85.00
    :estabelecimento "Alura"
    :categoria       "Educação"
    :cartao          1598159815981598}
   {:data            "2022-01-09"
    :valor           350.00
    :estabelecimento "Tok&Stok"
    :categoria       "Casa"
    :cartao          1598159815981598}
   {:data            "2022-01-10"
    :valor           400.00
    :estabelecimento "Leroy Merlin"
    :categoria       "Casa"
    :cartao          1598159815981598}
   {:data            "2022-01-11"
    :valor           50.00
    :estabelecimento "Madero"
    :categoria       "Alimentação"
    :cartao          6655665566556655}
   {:data            "2022-01-12"
    :valor           70.00
    :estabelecimento "Teatro"
    :categoria       "Lazer"
    :cartao          6655665566556655}
   {:data            "2022-01-13"
    :valor           250.00
    :estabelecimento "Hospital"
    :categoria       "Saúde"
    :cartao          6655665566556655}
   {:data            "2022-01-14"
    :valor           130.00
    :estabelecimento "Drogaria"
    :categoria       "Sauúde"
    :cartao          6655665566556655}
   {:data            "2022-01-15"
    :valor           100.00
    :estabelecimento "Show de pagode"
    :categoria       "Lazer"
    :cartao          3939393939393939}
   {:data            "2022-01-16"
    :valor           25.90
    :estabelecimento "Dogão"
    :categoria       "Alimentação"
    :cartao          3939393939393939}
   {:data            "2022-01-17"
    :valor           215.87
    :estabelecimento "Praia"
    :categoria       "Lazer"
    :cartao          3939393939393939}
   {:data            "2022-01-18"
    :valor           976.88
    :estabelecimento "Oficina"
    :categoria       "Automóvel"
    :cartao          3939393939393939}
   {:data            "2022-01-19"
    :valor           85.00
    :estabelecimento "Alura"
    :categoria       "Educacção"
    :cartao          3939393939393939}])

(def repositorio-de-compras (atom []))

(defrecord Compra [id data valor estabelecimento categoria cartao])

(defn gera-id
  "Gera o ID para um map a ser inserido no vetor"
  [compras]
  (+ (count compras) 1)
  )

(defn insere-compra
  "Insere uma compra em um vetor de compras"
  [compra compras]
  (let [nova-compra (assoc compra :id (gera-id compras))]
     (conj compras nova-compra)
    )
  )

(defn insere-compra!
  "Insere uma nova compra no átomo"
  [compra repositorio-de-compras]
  (swap! repositorio-de-compras (insere-compra compra (lista-compras)))
  )

(defn lista-compras!
  "Lista as compras de um atomo"
  [repositorio-de-compras]
  (pprint @repositorio-de-compras)
  )

(defn exclui-compra
  "Exclui a compra de um ID determinado"
  [id compras]
  (->> @compras
       (filter #(= (:id %) id))
       first
       (remove #{first})
       )
  )

(defn exclui-compra!
  "Atualiza o atomo através da exclusao de uma compra"
  [id repositorio-de-compras]
  (swap! (repositorio-de-compras id repositorio-de-compras) repositorio-de-compras)
  )

(def compra (nova-compra "10/01/2022" 10 "Estabele" "Saúde" "Visa"))
(println compra)
(println (insere-compra compra (lista-compras)))
(println (insere-compra! compra repositorio-de-compras))
(lista-compras! repositorio-de-compras)