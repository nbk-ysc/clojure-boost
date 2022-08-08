(ns week3.mock)

(def compras [{:dia "2022-01-01" :valor 100 :estabelecimento "Outback" :categoria "Alimentação" :cartao "1234 1234 1234 1234"},
              {:dia "2022-01-01" :valor 200 :estabelecimento "FastShop" :categoria "Casa" :cartao "1234 1234 1234 1234"},
              {:dia "2022-01-01" :valor 300 :estabelecimento "SmartFit" :categoria "Saúde" :cartao "1234 1234 1234 1234"}])

(def compras-agrupar [{:dia "2022-01-01" :valor 100 :estabelecimento "Outback" :categoria "Alimentação" :cartao "1234 1234 1234 1234"},
              {:dia "2022-01-01" :valor 200 :estabelecimento "FastShop" :categoria "Casa" :cartao "1234 1234 1234 1234"},
              {:dia "2022-01-01" :valor 300 :estabelecimento "SmartFit" :categoria "Saúde" :cartao "1234 1234 1234 1234"},
              {:dia "2022-01-01" :valor 300 :estabelecimento "DrogaFarma" :categoria "Saúde" :cartao "1234 1234 1234 1234"}
              ])

(def compras-sem-categoria [{:dia "2022-01-01" :valor 100 :estabelecimento "Outback" :cartao "1234 1234 1234 1234"},
                      {:dia "2022-01-01" :valor 200 :estabelecimento "FastShop" :cartao "1234 1234 1234 1234"},
                      {:dia "2022-01-01" :valor 300 :estabelecimento "SmartFit" :cartao "1234 1234 1234 1234"},
                      {:dia "2022-01-01" :valor 300 :estabelecimento "DrogaFarma" :cartao "1234 1234 1234 1234"}
                      ])

(def compras-agrupar-sem-valor [{:dia "2022-01-01" :valor 0 :estabelecimento "Outback" :categoria "Alimentação" :cartao "1234 1234 1234 1234"},
                      {:dia "2022-01-01" :valor 0 :estabelecimento "FastShop" :categoria "Casa" :cartao "1234 1234 1234 1234"},
                      {:dia "2022-01-01" :valor 0 :estabelecimento "SmartFit" :categoria "Saúde" :cartao "1234 1234 1234 1234"},
                      {:dia "2022-01-01" :valor 0 :estabelecimento "DrogaFarma" :categoria "Saúde" :cartao "1234 1234 1234 1234"}
                      ])

(def compras-sem-valor [{:dia "2022-01-01" :valor 0 :estabelecimento "Outback" :categoria "Alimentação" :cartao "1234 1234 1234 1234"},
                        {:dia "2022-01-01" :valor 0 :estabelecimento "FastShop" :categoria "Casa" :cartao "1234 1234 1234 1234"},
                        {:dia "2022-01-01" :valor 0 :estabelecimento "SmartFit" :categoria "Saúde" :cartao "1234 1234 1234 1234"}])