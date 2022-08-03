(ns clojure-boost.teste-schema
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true]))

(map + [1 2 3] (range))

(def compras* (atom []))

(let [compras [{:valor 1}
               {:valor 5}
               {:valor 10}]]
  (doseq [[compra id] (map vector compras (range))]
    (swap! compras* conj (assoc compra :id id))))

;(pprint @compras*)

(s/set-fn-validation! true)


(s/defn teste-simples [x :- Long]
  (println x))

;(teste-simples "bdbs")

(s/defn novo-paciente [id :- Long, nome :- s/Str]
  {:id id, :nome nome})

(pprint (novo-paciente 10 "Filipe"))


(def Paciente {:id s/Num, :nome s/Str})


(pprint (s/validate Paciente {:id 15, :nome "Filipe"}))

(s/defn novo-paciente :- Paciente [id :- s/Num, nome :- s/Str]
  {:id id, :nome nome})
(pprint (novo-paciente 10 "Filipe 2"))

(defn estritamente-positivo? [x]
  (> x 0))

(def EstritamentePositivo (s/pred estritamente-positivo? 'batatinha))

;(pprint (s/validate EstritamentePositivo 0))


(def Paciente {:id (s/constrained s/Int pos?), :nome s/Str})

;(pprint (s/validate Paciente {:id 0 :nome "Filipe"}))



(def Paciente {:id (s/constrained s/Int #(< 0 %)), :nome s/Str})

;(pprint (s/validate Paciente {:id 0 :nome "Filipe"}))


(def PosInt (s/pred pos-int?))
(def Paciente {:id PosInt, :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- PosInt
   nome :- s/Str]
  {:id id, :nome nome})
(pprint (novo-paciente 10 "Batata"))


(defn maior-ou-igual-a-zero? [x] (>= x 0))

(def ValorFinanceiro (s/constrained s/Num maior-ou-igual-a-zero?))


(def Pedido {
             :paciente Paciente
             :valor ValorFinanceiro
             :procedimento s/Keyword})

(s/defn novo-pedido :- Pedido
  [paciente :- Paciente
   valor :- ValorFinanceiro
   procedimento :- s/Keyword]
  {:paciente paciente, :valor valor, :procedimento procedimento})

;(pprint (novo-pedido (novo-paciente 10, "Filipe") -1, :cirurgia))


(def Plano [s/Keyword])

(def Paciente {:id PosInt, :nome s/Str, :plano Plano, (s/optional-key :nascimento) s/Str})

;(pprint (s/validate Paciente {:id 10, :nome "Filipe", :plano [:batata], :nascimento "dds"}))
;(pprint (s/validate Paciente {:id 10, :nome "Filipe", :plano [:batata]}))



(def Pacientes {PosInt Paciente})

;(let [filipe {:id 10, :nome "Filipe", :plano [:bradesco]}]
;  (pprint (s/validate Pacientes {15 filipe})))





(defn cabe-na-fila? [hospital departamento]
  (when-let [fila (get hospital departamento)]
    (-> fila
        count
        (< 5))))

(defn- tenta-colocar-na-fila
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)))

(defn chega-em
  [hospital departamento pessoa]
  (if-let [novo-hospital (tenta-colocar-na-fila hospital departamento pessoa)]
    { :hospital novo-hospital, :resultado :sucesso}
    { :hospital hospital, :resultado :impossível-colocar-pessoa-na-fila}))


;sobre os tetses
;(deftest cabe-na-fila?-test
;  (testing "Cabe na fila"
;    (is (cabe-na-fila? {:espera []} :espera)))
;  (testing "Que nao cabe na fila quando a fila esta cheia"
;    (is (not (cabe-na-fila? {:espera [1 2 3 4 5]}, :espera))))
;  (testing "Mais que a fila"
;    (is (not (cabe-na-fila? {:espera [1 2 3 4 5 6]}, :espera))))
;  (testing "Testando 4"
;    (is (cabe-na-fila? {:espera [1 2 3 4]}, :espera)))
;  (testing "Departamento nao existe"
;    (is (not (cabe-na-fila? {:espera [1 2 3]}, :blabla)))))
;
;(deftest chega-em-test
;  (let [hospital-cheio {:espera [1, 35, 42, 64, 21]}]
;
;    (testing "pessoas na fila"
;(is (= {:hospital hospital-cheio :resultado :impossível-colocar-pessoa-na-fila}
;       (chega-em hospital-cheio, :espera 76))))))
