(ns semana4.shell.logic
  (:use [clojure.pprint])
  (:require [semana4.core.compra :as cb.model]))

(def repositorio-de-compras (atom []))

(cb.model/insere-compra! (cb.model/->Compra nil "2015-05-03", 15.5, "loja1", "tipo1",
                                            12454545435454) repositorio-de-compras)
(cb.model/lista-compras! repositorio-de-compras)