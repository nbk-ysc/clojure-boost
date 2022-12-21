(ns clojure-boost.semana4
  (:require [clojure.pprint :as pprint]))

(def repositorio-de-compras (atom []))

(defrecord compra [id data valor estabelecimento categoria cartao])

(defn maxid [vet]
  (if (empty? vet) 0 (->> vet (map :id) (apply max))))

(defn insere-compra [compra vetcompra]
  (let [idnovo  (inc (maxid vetcompra)) vetcompracomid (assoc compra :id idnovo)]
    (conj vetcompra vetcompracomid)))

(defn insere-compra! [compra atomcompra]
  (swap! atomcompra (fn [x] (insere-compra compra x))))

(defn lista-compras! [atomcompra]
  (pprint/pprint @atomcompra))

(defn exclui-compra [idexclui vetcompra]
  (vec (remove (fn [x] (= idexclui (:id x))) vetcompra)))

(defn exclui-compra! [idexclui atomcompra]
  (swap! atomcompra (fn [x] (exclui-compra idexclui x))))

