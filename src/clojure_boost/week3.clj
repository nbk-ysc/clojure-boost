(ns clojure-boost.week3)
(defn nova-compra
  "Retorna lista de compras"
  [data valor estabelecimento categoria cartao]
  {
   :data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao
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
(defn total-gasto
  "Calcula o total gasto em compras de um determinado cartão"
  [compras]
  (reduce + (map :valor compras))
  )
(defn get-mes [data]
  "Extrai o mes como inteiro da data"
  (-> (clojure.string/split data #"-")
      (get 1)
      (Integer/parseInt))
  )
(defn lista-compras-mes
  "Lista todas as compras de um determinado mes"
  [compras mes]
  (->> compras
       (filter #(= mes (get-mes (:data %)))))
  )
(defn total-gasto-no-mes
  "Calcula o total gasto em compras de um determinado cartão"
  [compras mes]
  (reduce + (map :valor (lista-compras-mes compras mes)))
  )
(defn agrupa-compras-por-categoria
  "Retornas as compras agrupadas por categoria"
  [compras]
  (group-by :categoria compras)
  )
(defn agrupa-total-gastos-por-categoria
  "Calcula o total de gastor por categoria"
  [compras]
  (->> compras
       agrupa-compras-por-categoria
       (map (fn [[key vals]] {key (total-gasto vals)}))
       (into {}))
  )
(defn compras-no-intervalo
  "Retornas as compras feitas no intervalo passado"
  [compras min max]
  (filter #((and (>= max :valor compras) (<= min :valor compras)) compras))
  )
;(println (lista-compras))
;(println (lista-compras-mes (lista-compras) 1))
;(println (total-gasto-no-mes (lista-compras) 2))
;(println (total-gasto (lista-compras)))
;(println (agrupa-compras-por-categoria (lista-compras)))
;(println (agrupa-total-gastos-por-categoria (lista-compras)))
;(println (compras-no-intervalo (lista-compras) 100 1))