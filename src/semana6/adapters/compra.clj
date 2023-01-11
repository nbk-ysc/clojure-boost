(ns semana6.adapters.compra
  (:require [clojure.set :refer [map-invert rename-keys]]))

(def compra-keywords
  "Keywords que representam a entidade compra no clojure e no datomic"
  {:id              :compra/id    
   :data            :compra/data
   :valor           :compra/valor
   :estabelecimento :compra/estabelecimento
   :categoria       :compra/categoria
   :cartao          :compra/cartao})

(defn compra->compra-datomic [compra]
  (rename-keys compra compra-keywords))

(defn compra-datomic->compra [compra-datomic]
  (rename-keys compra-datomic (map-invert compra-keywords)))