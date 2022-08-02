(ns clojure-boost.semana2.exercicio2
  (:use (clojure pprint)))

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

(defrecord Compra [^Long ID ^String Data ^BigDecimal Valor ^String Estabelecimento
                   ^String Categoria ^Long Cartao])
(pprint (->Compra nil "2022-04-20" 134 "Outback" "Alimentação"4321432143214321))



