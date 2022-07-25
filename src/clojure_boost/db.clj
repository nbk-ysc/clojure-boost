(ns clojure-boost.db)

(def purchases-card-1 [{:date          "2022-01-01" :value 129.90,
                 :establishment "Outback" :category "Alimentação",
                 :card          1234123412341234}
                {:date          "2022-01-02" :value 260.00,
                 :establishment "Dentista" :category "Saúde",
                 :card          1234123412341234}
                {:date          "2022-02-01" :value 20.00,
                 :establishment "Cinema" :category "Lazer",
                 :card          1234123412341234}])

(def purchases [{:date"2022-01-01"                             :value 129.90,
                    :establishment "Outback"                   :category "Alimentação",
                    :card 1234123412341234}
                {:date"2022-01-02"                             :value 260.00,
                      :establishment "Dentista"                :category "Saúde",
                      :card 1234123412341234}
                {:date"2022-02-01"                             :value 20.00,
                      :establishment "Cinema"                  :category "Lazer",
                      :card 1234123412341234}
                {:date"2022-01-10"                             :value 150.00,
                      :establishment "Show"                    :category "Lazer",
                      :card 4321432143214321}
                {:date"2022-02-10"                             :value 289.99,
                      :establishment "Posto de gasolina"       :category "Automóvel",
                      :card 4321432143214321}
                {:date"2022-02-20"                             :value 79.90,
                      :establishment "iFood"                   :category "Alimentação",
                      :card 4321432143214321}
                {:date"2022-03-01"                             :value 85.00,
                      :establishment "Alura"                   :category "Educação",
                      :card 4321432143214321}
                {:date"2022-01-30"                             :value 85.00,
                      :establishment "Alura"                   :category "Educação",
                      :card 1598159815981598}
                {:date"2022-01-31"                             :value 350.00,
                      :establishment "Tok&Stok"                :category "Casa",
                      :card 1598159815981598}
                {:date"2022-02-01"                             :value 400.00,
                      :establishment "Leroy Merlin"            :category "Casa",
                      :card 1598159815981598}
                {:date"2022-03-01"                             :value 50.00,
                      :establishment "Madero"                  :category "Alimentação",
                      :card 6655665566556655}
                {:date"2022-03-01"                             :value 70.00,
                      :establishment "Teatro"                  :category "Lazer",
                      :card 6655665566556655}
                {:date"2022-03-04"                             :value 250.00,
                      :establishment "Hospital"                :category "Saúde",
                      :card 6655665566556655}
                {:date"2022-04-10"                             :value 130.00,
                      :establishment "Drogaria"                :category "Saúde",
                      :card 6655665566556655}
                {:date"2022-03-10"                             :value 100.00,
                      :establishment "Show de pagode"          :category "Lazer",
                      :card 3939393939393939}
                {:date"2022-03-11"                             :value 25.90,
                      :establishment "Dogão"                   :category "Alimentação",
                      :card 3939393939393939}
                {:date"2022-03-12"                             :value 215.87,
                      :establishment "Praia"                   :category "Lazer",
                      :card 3939393939393939}
                {:date"2022-04-01"                             :value 976.88,
                      :establishment "Oficina"                 :category "Automóvel",
                      :card 3939393939393939}
                {:date"2022-04-10"                             :value 85.00,
                      :establishment "Alura"                   :category "Educação",
                      :card 3939393939393939}])


(defn all-purchases []
  [purchases]
  )


;tests local database

                      ;(def purchases2 {:id1 {:date "2022-01-01"                      :value 129.90,
                       ;                       :establishment "Outback"                :category "Alimentação",
                       ;                       :card 1234123412341234}
                       ;                :id2 {:date"2022-01-02"                        :value 260.00,
                       ;                      :establishment "Dentista"                :category "Saúde",
                       ;                      :card 1234123412341234}
                       ;                :id3 {:date"2022-02-01"                        :value 20.00,
                       ;                      :establishment "Cinema"                  :category "Lazer",
                       ;                      :card 1234123412341234}
                       ;                :id4 {:date"2022-01-10"                        :value 150.00,
                       ;                      :establishment "Show"                    :category "Lazer",
                       ;                      :card 4321432143214321}
                       ;                :id5 {:date"2022-02-10"                        :value 289.99,
                       ;                      :establishment "Posto de gasolina"       :category "Automóvel",
                       ;                      :card 4321432143214321}
                       ;                :id6 {:date"2022-02-20"                        :value 79.90,
                       ;                      :establishment "iFood"                   :category "Alimentação",
                       ;                      :card 4321432143214321}
                       ;                :id7 {:date"2022-03-01"                        :value 85.00,
                       ;                      :establishment "Alura"                   :category "Educação",
                       ;                      :card 4321432143214321}
                       ;                :id8 {:date"2022-01-30"                        :value 85.00,
                       ;                      :establishment "Alura"                   :category "Educação",
                       ;                      :card 1598159815981598}
                       ;                :id9 {:date"2022-01-31"                        :value 350.00,
                       ;                      :establishment "Tok&Stok"                :category "Casa",
                       ;                      :card 1598159815981598}
                       ;                :id10 {:date"2022-02-01"                       :value 400.00,
                       ;                       :establishment "Leroy Merlin"            :category "Casa",
                       ;                       :card 1598159815981598}
                       ;                :id11 {:date"2022-03-01"                       :value 50.00,
                       ;                       :establishment "Madero"                  :category "Alimentação",
                       ;                       :card 6655665566556655}
                       ;                :id12 {:date"2022-03-01"                       :value 70.00,
                       ;                       :establishment "Teatro"                  :category "Lazer",
                       ;                       :card 6655665566556655}
                       ;                :id13 {:date"2022-03-04"                       :value 250.00,
                       ;                       :establishment "Hospital"                :category "Saúde",
                       ;                       :card 6655665566556655}
                       ;                :id14 {:date"2022-04-10"                       :value 130.00,
                       ;                       :establishment "Drogaria"                :category "Saúde",
                       ;                       :card 6655665566556655}
                       ;                :id15 {:date"2022-03-10"                       :value 100.00,
                       ;                       :establishment "Show de pagode"          :category "Lazer",
                       ;                       :card 3939393939393939}
                       ;                :id16 {:date"2022-03-11"                       :value 25.90,
                       ;                       :establishment "Dogão"                   :category "Alimentação",
                       ;                       :card 3939393939393939}
                       ;                :id17 {:date"2022-03-12"                       :value 215.87,
                       ;                       :establishment "Praia"                   :category "Lazer",
                       ;                       :card 3939393939393939}
                       ;                :id18 {:date"2022-04-01"                       :value 976.88,
                       ;                       :establishment "Oficina"                 :category "Automóvel",
                       ;                       :card 3939393939393939}
                       ;                :id19 {:date"2022-04-10"                       :value 85.00,
                       ;                       :establishment "Alura"                   :category "Educação",
                       ;                       :card 3939393939393939}})
                       ;(def purchase1 {:date "2022-01-01", :value 129.90, :establishment "Outback",
                       ;               :category "Alimentação", :card 1234123412341234})
                       ;
                       ;(def purchase2 {:id {:date "2022-01-01"          :value 129.90
                       ;                     :establishment "Outback"    :category "Alimentação"
                       ;                     :card 1234123412341234}})
                       ;
                       ;(def purchase3 {:id1 {:date"2022-01-01"                        :value 129.90,
                       ;                      :establishment "Outback"                   :category "Alimentação",
                       ;                      :card 1234123412341234}
                       ;                :id2 {:date"2022-01-02"                        :value 260.00,
                       ;                      :establishment "Dentista"                :category "Saúde",
                       ;                      :card 1234123412341234}
                       ;                :id3 {:date"2022-02-01"                        :value 20.00,
                       ;                      :establishment "Cinema"                  :category "Lazer",
                       ;                      :card 1234123412341234}})