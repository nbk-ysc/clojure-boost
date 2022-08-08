(ns clojure-boost.validations.validations_compras
  (:use clojure.pprint))


(defn cond-data-formato-correto?
  [compra]
  (not= nil (re-matches #"[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]" compra)))

(defn cond-valor-e-bigdecimal? [compra]
  (let [valor compra]
    (and (decimal? valor)
         (> valor 0))))
(defn cond-mais-de-duas-letras-no-estabelecimento?
  [compra]
  (let [estabelecimento compra]
    (> (count estabelecimento) 2)))

(defn cond-categoria-correta?
  [compra]
  (let [categoria-permitida ["Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"]]
    (.contains categoria-permitida compra)))
