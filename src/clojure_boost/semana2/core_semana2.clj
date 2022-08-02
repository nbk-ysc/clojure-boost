(ns clojure-boost.semana2.core_semana2
  (:use (clojure pprint)))

;---------------------
;Exercicio 1
;---------------------
;Tarefa Definir átomo como BD em memória
;Definir um átomo no símbolo repositorio-de-compras onde serão salvos os maps de compras.
; O átomo deve ser inicializado com um vetor vazio [].

;(def repositorio-de-compras
;  (let [compras (atom {:valor lista-compras})]
;    (println compras)))

(def repositorio-de-compras
  (atom {}))
(println "---------------\nExercicio 1\n---------------")
(pprint repositorio-de-compras)

;---------------------
;Exercicio 2
;---------------------

;Tarefa
;Crie um record para representar uma Compra realizada em um determinado Cartão.
;tributos do record devem ser:
;ID (Long ou nil)
;Data (String: yyyy-mm-dd ou LocalDate)
;Valor (BigDecimal)
;Estabelecimento (String)
;Categoria (String)
;Cartão (Long)
;(defrecord Paciente [id nome nascimento])
;(println (->Paciente 15 "Guilherme" "18/9/1981"))

(defrecord Compra [^Long ID
                   ^String Data
                   ^BigDecimal Valor
                   ^String Estabelecimento
                   ^String Categoria
                   ^Long Cartao])
(println "---------------\nExercicio 2\n---------------")
(pprint (->Compra 1 "2022-04-20" 134 "Outback" "Alimentação"4321432143214321))