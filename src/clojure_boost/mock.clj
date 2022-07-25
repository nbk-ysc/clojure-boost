(ns clojure-boost.mock)

(def compras [
              {:dia "2022-01-01" :valor 129.90 :estabelecimento "Outback" :categoria "Alimentação" :cartao "1234 1234 1234 1234"}
              {:dia "2022-01-02" :valor 260.00 :estabelecimento "Dentista" :categoria "Saúde" :cartao "1234 1234 1234 1234"}
              {:dia "2022-02-01" :valor 20.00 :estabelecimento "Cinema" :categoria "Lazer" :cartao "1234 1234 1234 1234"}
              {:dia "2022-01-10" :valor 150.00 :estabelecimento "Show" :categoria "Lazer" :cartao "4321 4321 4321 4321"}
              {:dia "2022-02-10" :valor 289.99 :estabelecimento "Posto de Gasolina" :categoria "Automóvel" :cartao "4321 4321 4321 4321"}
              {:dia "2022-02-20" :valor 79.90 :estabelecimento "iFood" :categoria "Alimentação" :cartao "4321 4321 4321 4321"}
              {:dia "2022-03-01" :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao "4321 4321 4321"}
              {:dia "2022-01-30" :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao "1598 1598 1598 1598"}
              {:dia "2022-01-31" :valor 350.00 :estabelecimento "Tok&Stok" :categoria "Casa" :cartao "1598 1598 1598 1598"}
              {:dia "2022-02-01" :valor 400.00 :estabelecimento "Leroy Merlin" :categoria "Casa" :cartao "1598 1598 1598 1598"}
              {:dia "2022-03-01" :valor 50.00 :estabelecimento "Madero" :categoria "Alimentação" :cartao "6655 6655 6655 6655"}
              {:dia "2022-03-01" :valor 70.00 :estabelecimento "Teatro" :categoria "Lazer" :cartao "6655 6655 6655 6655"}
              {:dia "2022-03-04" :valor 250.00 :estabelecimento "Hospital" :categoria "Saúde" :cartao "6655 6655 6655 6655"}
              {:dia "2022-04-10" :valor 130.00 :estabelecimento "Drogaria" :categoria "Saúde" :cartao "6655 6655 6655 6655"}
              {:dia "2022-03-10" :valor 100.00 :estabelecimento "Show de Pagode" :categoria "Lazer" :cartao "3939 3939 3939 3939"}
              {:dia "2022-03-11" :valor 25.90 :estabelecimento "Dogão" :categoria "Alimentação" :cartao "3939 3939 3939 3939"}
              {:dia "2022-03-12" :valor 215.87 :estabelecimento "Praia" :categoria "Lazer" :cartao "3939 3939 3939 3939"}
              {:dia "2022-04-01" :valor 976.88 :estabelecimento "Oficina" :categoria "Automóvel" :cartao "3939 3939 3939 3939"}
              {:dia "2022-04-10" :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao "3939 3939 3939 3939"}
              ]
  )
