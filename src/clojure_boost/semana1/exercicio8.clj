(ns clojure-boost.semana1.exercicio8
  (:require [java-time :as jt]))
(defn aplica-java-time-na-chave-data
  []
  [
   {:data            (jt/local-date "2022-01-01")
    :valor           129.90
    :estabelecimento "Outback"
    :categoria       "Alimentação"
    :cartao          1234123412341234},
   {:data            (jt/local-date "2022-01-02")
    :valor           260.00
    :estabelecimento "Dentista"
    :categoria       "Saúde"
    :cartao          1234123412341234}
   {:data            (jt/local-date "2022-02-01")
    :valor           20.0
    :estabelecimento "Cinema"
    :categoria       "Lazer"
    :cartao          1234123412341234},
   {:data            (jt/local-date "2022-01-10")
    :valor           150.0
    :estabelecimento "Show"
    :categoria       "Lazer"
    :cartao          4321432143214321},
   {:data            (jt/local-date "2022-02-10")
    :valor           289.99
    :estabelecimento "Posto de gasolina"
    :categoria       "Automóvel"
    :cartao          4321432143214321},
   {:data            (jt/local-date "2022-02-20")
    :valor           79.90
    :estabelecimento "iFood"
    :categoria       "Alimentação"
    :cartao          4321432143214321},
   {:data            (jt/local-date "2022-03-01")
    :valor           85.00
    :estabelecimento "Alura"
    :categoria       "Educação"
    :cartao          4321432143214321},
   {:data            (jt/local-date "2022-01-30")
    :valor           85.00
    :estabelecimento "Alura"
    :categoria       "Educação"
    :cartao          1598159815981598},
   {:data            (jt/local-date "2022-01-31")
    :valor           350.00
    :estabelecimento "Tok&Stok"
    :categoria       "Casa"
    :cartao          1598159815981598},
   {:data            (jt/local-date "2022-02-01")
    :valor           400.00
    :estabelecimento "Leroy Merlin"
    :categoria       "Casa"
    :cartao          1598159815981598},
   {:data            (jt/local-date "2022-03-01")
    :valor           50.00
    :estabelecimento "Madero"
    :categoria       "Alimentação"
    :cartao          6655665566556655},
   {:data            (jt/local-date "2022-03-01")
    :valor           70.00
    :estabelecimento "Teatro"
    :categoria       "Lazer"
    :cartao          6655665566556655},
   {:data            (jt/local-date "2022-03-04")
    :valor           250.00
    :estabelecimento "Hospital"
    :categoria       "Saúde"
    :cartao          6655665566556655},
   {:data            "2022-04-10"
    :valor           130.00
    :estabelecimento "Drogaria"
    :categoria       "Saúde"
    :cartao          6655665566556655},
   {:data            (jt/local-date "2022-03-10")
    :valor           100.00
    :estabelecimento "Show de pagode"
    :categoria       "Lazer"
    :cartao          3939393939393939},
   {:data            (jt/local-date "2022-03-11")
    :valor           25.90
    :estabelecimento "Dogão"
    :categoria       "Alimentação"
    :cartao          3939393939393939},
   {:data            (jt/local-date "2022-03-12")
    :valor           215.87
    :estabelecimento "Praia"
    :categoria       "Lazer"
    :cartao          3939393939393939},
   {:data            (jt/local-date "2022-04-01")
    :valor           976.88
    :estabelecimento "Oficina"
    :categoria       "Carro"
    :cartao          3939393939393939},
   {:data            (jt/local-date "2022-04-10")
    :valor           85.00
    :estabelecimento "Alura"
    :categoria       "Educação"
    :cartao          3939393939393939
    }])

(println (aplica-java-time-na-chave-data))