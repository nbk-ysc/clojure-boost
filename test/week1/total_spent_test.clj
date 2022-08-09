(ns week1.total-spent-test
  (:use [clojure pprint])
  (:require [clojure.test :refer :all]
            [week1.total-spent :as week1]
            [week3.new-purchase :as week3]))


(def purchases [{:date"2022-01-01"                             :value 100.00,
                 :establishment "Outback"                   :category "Alimentação",
                 :card 1234123412341234}
                {:date"2022-01-02"                             :value 200.00,
                 :establishment "Dentista"                :category "Saúde",
                 :card 1234123412341234}
                {:date"2022-02-01"                             :value 300.00,
                 :establishment "Cinema"                  :category "Saúde",
                 :card 1234123412341234}])

(deftest total-spent-by-card-test
  (testing "The total value from purchases in this card equals to 600.00M"
    (is (= (week1/total-spent-by-card 1234123412341234 purchases) 600.00M))))

(deftest total-spent-by-category-test
  (testing "The total value from purchases in this category equals to 500.00M"
    (is (= (week1/total-spent-by-category "Saúde" purchases) 500.00M))))

(deftest valid-buy-test
  (testing "Schema valid buy"
    (is (= (week3/new-purchase "2022-05-09" 100M "Amazon" "Casa" 1111222233334444)
           {:date              "2022-05-09",
            :value              100M,
            :establishment     "Amazon",
            :category          "Casa",
            :card               1111222233334444}))))

