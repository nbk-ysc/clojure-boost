(ns clojure-boost.adapters.compras-test
  (:require [clojure.test :refer :all]
            [clojure-boost.adapters.compras :refer :all]
            [clojure-boost.logic.compras :refer :all]
            [java-time :as java-time]
            [schema.core :as s]
            [clojure-boost.schemas.compras :as schemas.compras]))

(s/set-fn-validation! true)

(deftest nova-compra-test
  (testing "Quando nova compra é válida"
    (let [compra-validate (s/validate schemas.compras/CompraSchemaSemId (nova-compra (java-time/local-date 2022 5 9), 100M, "Amazon", "Casa", 1111222233334444))]
      (is (= {:data            (java-time/local-date 2022 5 9),
              :valor           100M,
              :estabelecimento "Amazon",
              :categoria       "Casa",
              :cartao          1111222233334444}
             compra-validate)))))

(deftest insere-compra-test
  (testing "Quando insere compra em uma lista existente"
    (let [lista-compras-esperada [{:id              5,
                                   :data            (java-time/local-date 2020 01 01),
                                   :valor           81.0M,
                                   :estabelecimento "Alura",
                                   :categoria       "Alimentação",
                                   :cartao          3939393939393939}
                                  {:id              44,
                                   :data            (java-time/local-date 2022 7 12),
                                   :valor           13341.39M,
                                   :estabelecimento "Outback",
                                   :categoria       "Alimentação",
                                   :cartao          1234123412341234}
                                  {:id              45,
                                   :data            (java-time/local-date 2022 7 29),
                                   :valor           0.98M,
                                   :estabelecimento "Nubank",
                                   :categoria       "Lazer",
                                   :cartao          1234123412341234}]
          lista-compras          [{:id              5,
                                   :data            (java-time/local-date 2020 01 01),
                                   :valor           81.0M,
                                   :estabelecimento "Alura",
                                   :categoria       "Alimentação",
                                   :cartao          3939393939393939}
                                  {:id              44,
                                   :data            (java-time/local-date 2022 7 12),
                                   :valor           13341.39M,
                                   :estabelecimento "Outback",
                                   :categoria       "Alimentação",
                                   :cartao          1234123412341234}]]
      (is (.equals lista-compras-esperada
                   (insere-compra lista-compras
                                  (->Compra nil, (java-time/local-date 2022 7 29), 0.98M, "Nubank", "Lazer", 1234123412341234))))))

  (testing "Quando insere compra em uma lista vazia"
    (is (.equals [{:id              1,
                   :data            (java-time/local-date 2020 10 01),
                   :valor           8189.11M,
                   :estabelecimento "Nubank",
                   :categoria       "Alimentação",
                   :cartao          3939393939393939}]
                 (insere-compra []
                                (->Compra nil, (java-time/local-date 2020 10 01), 8189.11M, "Nubank", "Alimentação", 3939393939393939))))))

(deftest insere-compra!-test
  (testing "Quando insere compra no atomo"
    (is (.equals [{:id              1,
                   :data            (java-time/local-date 2021 10 12),
                   :valor           412.17M,
                   :estabelecimento "Leroy Merlin",
                   :categoria       "Automóvel",
                   :cartao          1598159815981598}]
                 (insere-compra! (atom [])
                                 (->Compra nil, (java-time/local-date 2021 10 12), 412.17M, "Leroy Merlin", "Automóvel", 1598159815981598)))))

  (testing "Quando insere mais de uma compra compra no atomo"
    (let [lista-esperada          [{:id              1,
                                    :data            (java-time/local-date 2022 1 16),
                                    :valor           0.17M,
                                    :estabelecimento "Alura",
                                    :categoria       "Casa",
                                    :cartao          1234123412341234}
                                   {:id              2,
                                    :data            (java-time/local-date 2021 9 17),
                                    :valor           652.31M,
                                    :estabelecimento "Nubank",
                                    :categoria       "Educação",
                                    :cartao          430852031233}]
          atomo-com-compras (atom [])]
      (insere-compra! atomo-com-compras
                      (->Compra nil, (java-time/local-date 2022 1 16), 0.17M, "Alura", "Casa", 1234123412341234))
      (insere-compra! atomo-com-compras
                      (->Compra nil, (java-time/local-date 2021 9 17), 652.31M, "Nubank", "Educação", 430852031233))
      (is (.equals lista-esperada
                   @atomo-com-compras)))))

(deftest exclui-compra-test
  (let [lista-compras [{:id              56,
                        :data            (java-time/local-date 2022 3 31),
                        :valor           13.13M,
                        :estabelecimento "Alura",
                        :categoria       "Casa",
                        :cartao          999991333345666}
                       {:id              91,
                        :data            (java-time/local-date 2021 9 17),
                        :valor           652.31M,
                        :estabelecimento "Nubank",
                        :categoria       "Educação",
                        :cartao          430852031233}
                       {:id              23,
                        :data            (java-time/local-date 2021 9 17),
                        :valor           652.31M,
                        :estabelecimento "Nubank",
                        :categoria       "Educação",
                        :cartao          430852031233}]]

    (testing "Quando exclui compra da lista com id existente"
      (is (= [{:id              56,
               :data            (java-time/local-date 2022 3 31),
               :valor           13.13M,
               :estabelecimento "Alura",
               :categoria       "Casa",
               :cartao          999991333345666}
              {:id              23,
               :data            (java-time/local-date 2021 9 17),
               :valor           652.31M,
               :estabelecimento "Nubank",
               :categoria       "Educação",
               :cartao          430852031233}]
             (exclui-compra lista-compras 91))))

    (testing "Qunado exlcui compra da lista com id inexistente"
      (is (= lista-compras
             (exclui-compra lista-compras 92))))))

(deftest exclui-compra!-test
  (testing "Quando exclui compra do aotmo com id existente"
    (let [atomo-lista-compras (atom [{:id              56,
                                      :data            (java-time/local-date 2022 3 31),
                                      :valor           13.13M,
                                      :estabelecimento "Alura",
                                      :categoria       "Casa",
                                      :cartao          999991333345666}
                                     {:id              91,
                                      :data            (java-time/local-date 2021 9 17),
                                      :valor           652.31M,
                                      :estabelecimento "Nubank",
                                      :categoria       "Educação",
                                      :cartao          430852031233}
                                     {:id              23,
                                      :data            (java-time/local-date 2021 9 17),
                                      :valor           652.31M,
                                      :estabelecimento "Nubank",
                                      :categoria       "Educação",
                                      :cartao          430852031233}])]
      (is (= [{:id              56,
               :data            (java-time/local-date 2022 3 31),
               :valor           13.13M,
               :estabelecimento "Alura",
               :categoria       "Casa",
               :cartao          999991333345666}
              {:id              23,
               :data            (java-time/local-date 2021 9 17),
               :valor           652.31M,
               :estabelecimento "Nubank",
               :categoria       "Educação",
               :cartao          430852031233}]
             (exclui-compra! atomo-lista-compras 91))))))
