(ns clojure-boost.clojure_boost_week1)
(require '[clojure-boost.mock :as mock])
(require '[clojure-boost.functions-compra :as functions])

;exercicio 1
(println "Exercicio 1")
(functions/listar-compras mock/compras)

;exercicio 2
(println "Exercicio 2")
(functions/nova-compra mock/compras "3939 3939 3939 3939")

;exercicio 3
(println "Exercicio 3")
(functions/total-compras-cartao mock/compras "3939 3939 3939 3939")

;exercicio 4
(println "Exercicio 4")
(functions/listar-compras-mes mock/compras 01)

;exercicio 5
(println "Exercicio 5")
(functions/total-compras-mes mock/compras 02)

;exercicio 6
(println "Exercicio 6")
(functions/total-compras-grupo mock/compras)