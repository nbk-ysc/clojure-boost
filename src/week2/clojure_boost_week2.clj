(ns week2.clojure_boost_week2
  (:import (org.joda.time LocalDate))
  )
(require '[week2.functions :as functions])

;Exercicio 1
(def repositorio-de-compras (atom []))

;Exercicio 2
(defrecord Nova-Compra [^Long id ^LocalDate data ^BigDecimal valor ^String estabelecimento ^String categoria ^Long cartao])
(def compras [])

;Exercicio 3
(functions/insere-compra compras (map->Nova-Compra {:data functions/data-local, :valor 300.00, :estabelecimento "Extra", :categoria "Alimentação", :cartao 2345234523452345}))

;Exercicio 4
(functions/insere-compra-atomo! repositorio-de-compras (map->Nova-Compra {:data functions/data-local, :valor 300.00, :estabelecimento "Extra", :categoria "Alimentação", :cartao 2345234523452345}))
(functions/insere-compra-atomo! repositorio-de-compras (map->Nova-Compra {:data functions/data-local, :valor 145.00, :estabelecimento "Smart Fit", :categoria "Saúde", :cartao 2345234523452345}))

;Exercicio 5
(functions/lista-compras-atomo! repositorio-de-compras)