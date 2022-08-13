(ns clojure-boost.schemas.datomic
  (:require [schema.core :as s]
            [clojure-boost.schemas.compras :as schemas.compras]))

(s/def Id [{:id Long}])

(s/def NovaCompra->datomic
  [#:compra{:id              Long
            :data            s/Inst
            :valor           schemas.compras/Valor
            :estabelecimento schemas.compras/Estabelecimento
            :categoria       schemas.compras/Categoria
            :cartao          schemas.compras/Cartao}])