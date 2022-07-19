(ns clojure-boost.semana1)

;Compras realizdas
(defn nova-compra
  "Retorna estrutura de dados de uma compra realizada pelo cartão"
  [data, valor, estabelecimento, categoria, cartao]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

;-----------------------------------------------------------

;Listar Compras
(def lista-compras [{:data            "2022-01-01",
               :valor           129.9,
               :estabelecimento "Outback",
               :categoria       "Alimentação",
               :cartao          1234123412341234}
              {:data            "2022-01-02",
               :valor           260.0,
               :estabelecimento "Dentista",
               :categoria       "Saúde",
               :cartao          1234123412341234}
              {:data            "2022-02-01",
               :valor           20.0,
               :estabelecimento "Cinema",
               :categoria       "Lazer",
               :cartao          1234123412341234}
              {:data            "2022-01-10",
               :valor           150.0,
               :estabelecimento "Show",
               :categoria       "Lazer",
               :cartao          4321432143214321}
              {:data            "2022-02-10",
               :valor           289.99,
               :estabelecimento "Posto de gasolina",
               :categoria       "Automóvel",
               :cartao          4321432143214321}
              {:data            "2022-02-20",
               :valor           79.9,
               :estabelecimento "iFood",
               :categoria       "Alimentação",
               :cartao          4321432143214321}
              {:data            "2022-03-01",
               :valor           85.0,
               :estabelecimento "Alura",
               :categoria       "Educação",
               :cartao          4321432143214321}
              {:data            "2022-01-30",
               :valor           85.0,
               :estabelecimento "Alura",
               :categoria       "Educação",
               :cartao          1598159815981598}
              {:data            "2022-01-31",
               :valor           350.0,
               :estabelecimento "Tok&Stok",
               :categoria       "Casa",
               :cartao          1598159815981598}
              {:data            "2022-02-01",
               :valor           400.0,
               :estabelecimento "Leroy Merlin",
               :categoria       "Casa",
               :cartao          1598159815981598}
              {:data            "2022-03-01",
               :valor           50.0,
               :estabelecimento "Madero",
               :categoria       "Alimentação",
               :cartao          6655665566556655}
              {:data            "2022-03-01",
               :valor           70.0,
               :estabelecimento "Teatro",
               :categoria       "Lazer",
               :cartao          6655665566556655}
              {:data            "2022-03-04",
               :valor           250.0,
               :estabelecimento "Hospital",
               :categoria       "Saúde",
               :cartao          6655665566556655}
              {:data            "2022-04-10",
               :valor           130.0,
               :estabelecimento "Drogaria",
               :categoria       "Saúde",
               :cartao          6655665566556655}
              {:data            "2022-03-10",
               :valor           100.0,
               :estabelecimento "Show de pagode",
               :categoria       "Lazer",
               :cartao          3939393939393939}
              {:data            "2022-03-11",
               :valor           25.9,
               :estabelecimento "Dogão",
               :categoria       "Alimentação",
               :cartao          3939393939393939}
              {:data            "2022-03-12",
               :valor           215.87,
               :estabelecimento "Praia",
               :categoria       "Lazer",
               :cartao          3939393939393939}
              {:data            "2022-04-01",
               :valor           976.88,
               :estabelecimento "Oficina",
               :categoria       "Automóvel",
               :cartao          3939393939393939}
              {:data            "2022-04-10",
               :valor           85.0,
               :estabelecimento "Alura",
               :categoria       "Educação",
               :cartao          3939393939393939}])

;-----------------------------------------------------------
;Calcular o total gasto em compras de um cartão
(def total-gasto [compras]
  
  )

;-----------------------------------------------------------

;invocations
(nova-compra "2022-04-10",85.0,"Alura","Educação",3939393939393939)
(count lista-compras)
(println lista-compras)

;-----------------------------------------------------------


