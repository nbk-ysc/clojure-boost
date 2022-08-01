(ns week2.db)

(def purchase-repository (atom [{:id 1                                          :date "2022-01-01"
                                 :value 129.90                                  :establishment "Outback"
                                 :category "Alimentação"                        :card 1234123412341234}

                                {:id 2                                          :date "2022-01-02"
                                 :value 260.00                                  :establishment "Dentista"
                                 :category "Saúde"                              :card 1234123412341234}]))


(def purchases [{:id 1                                          :date "2022-01-01"
                 :value 129.90                                  :establishment "Outback"
                 :category "Alimentação"                        :card 1234123412341234}

                {:id 2                                          :date "2022-01-02"
                 :value 260.00                                  :establishment "Dentista"
                 :category "Saúde"                              :card 1234123412341234}])



(def purchases-id [:id 1 {:date "2022-01-01"                    :value 129.90
                          :establishment "Outback"              :category "Alimentação"
                          :card 1234123412341234}

                   :id 2 {:date "2022-01-02"                     :value 260.00
                          :establishment "Dentista"              :category "Saúde"
                          :card 1234123412341234}])
