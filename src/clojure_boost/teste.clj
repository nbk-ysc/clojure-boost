(ns clojure-boost.teste
  (:use clojure.pprint))

(def fila-vazia clojure.lang.PersistentQueue/EMPTY)

(defn novo-hospital []
  {:espera fila-vazia
   :laboratorio1 fila-vazia
   :laboratorio2 fila-vazia
   :laboratorio3 fila-vazia})
(defn cabe-na-fila? [hospital departamento]
  (-> hospital
      (get departamento)
      count
      (< 5)))

(defn chega-em [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))
    ))

(defn atende [hospital departamento]
  (update hospital departamento pop))


(defn chamando-troca-de-elemento [hospital pessoa]
  (swap! hospital chega-em :espera pessoa))

(defn starta-thread [hospital pessoa]
  (.start (Thread. (fn [] (chamando-troca-de-elemento hospital pessoa)))))

(defn simula-um-dia-batata []
  (let [hospital (atom (novo-hospital))
        pessoas ["123" "34433" "32323232332" "dssfddfsfssf" "sdsdsdccccccc"]
        starta-fn (partial starta-thread hospital)]
    (.start (Thread. (fn [] (chamando-bla hospital "batatinha 123"))))
    (mapv starta-fn pessoas)
    (pprint hospital)
    ))


;(simula-um-dia-batata)
(defn proxima
  [hospital departamento]
  (-> hospital
      departamento
      peek))


(defn transfere [hospital de para]
  (let [pessoa (proxima hospital de)]
    (-> hospital
        (atende de)
        (chega-em para pessoa))))

(defn transfere! [hospital de para]
  (swap! hospital transfere de para))

(defn simula-um-dia-batata2 []
  (let [hospital (atom (novo-hospital))
        pessoas ["123" "34433" "32323232332" "dssfddfsfssf" "sdsdsdccccccc"]]
    (doseq [pessoa pessoas]
      (starta-thread hospital pessoa))
    (transfere! hospital :espera :laboratorio1)
    (transfere! hospital :espera :laboratorio2)
    (transfere! hospital :laboratorio2 :laboratorio3)


    (pprint hospital)
    ))

;(simula-um-dia-batata2)

;(def batata-test ["123" "34433" "32323232332" "dssfddfsfssf" "sdsdsdccccccc"])
;
;(println (doseq [pessoa batata-test]
;                 (println pessoa)))


(defn simula-um-dia []
  (def hospital (novo-hospital))
  (def hospital (chega-em hospital :espera "111"))
  (def hospital (chega-em hospital :espera "222"))
  (def hospital (chega-em hospital :espera "333"))
  (def hospital (chega-em hospital :espera "222222"))
  (def hospital (chega-em hospital :espera "33we3"))
  (def hospital (chega-em hospital :espera "3eweew33"))
  (def hospital (chega-em hospital :espera "3ewewew33"))
  (def hospital (chega-em hospital :espera "3ewewewew33"))
  (def hospital (chega-em hospital :laboratorio1 "444"))
  (def hospital (chega-em hospital :laboratorio2 "555"))

  (def hospital (atende hospital :laboratorio1))
  (def hospital (atende hospital :espera))
  (pprint hospital))


(defn simula-dia-atom []
  (let [hospital-filipe (atom {:espera fila-vazia})]
    (swap! hospital-filipe assoc :laboratorio1 fila-vazia)
    (swap! hospital-filipe update :laboratorio1 conj "1111")
    (pprint hospital-filipe)))

;(simula-dia-atom)


(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente sem id" {:paciente paciente}))))


(defn testa-uso-pacientes []
  (let [pacientes {}
        filipe {:id 10, :nome "Filipe Monteiro", :nascimento "23/10/1996"}
        daniela {:id 2, :nome "Daniela" :nascimento "23/10/1002"}]
    (pprint (adiciona-paciente pacientes filipe))
    (pprint (adiciona-paciente pacientes daniela))))

;(testa-uso-pacientes)

(defrecord Paciente [id, nome, nascimento])

(pprint (->Paciente 10, "Filipe", "10/10/1996"))
(pprint (Paciente. 10, "Filipe", "asssa"))

(defn verifica-paciente []
  (let [paciente (->Paciente 10, "Filipe", "10/10/1000")]
    (->> paciente
        (map :nome)
         println)))

;(verifica-paciente)
(defrecord PacienteParticular [id, nome, nascimento])


(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))


(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente, procedimento, valor]
    (>= valor 50)))

(let [particular (->PacienteParticular 15, "Filipe", "10/10/10000")]
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 100))
  )



(defrecord PacienteParticular [id, nome, nascimento, situacao])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, situacao, plano])

;sem multimethod
(defn normalize-book [book]
  (if (vector? book)
    {:title (first book) :author (second book)}
  (if (contains? book :title)
    book
    {:title (:book book) :author (:by book)})))

(pprint (normalize-book {:book "Teste" :by "Blabla"}))
(pprint (normalize-book ["1985" "george orwell"]))


;com multimethod
(defn dispatch-book-format [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standart-map
    (contains? book :book) :valida-valor))



(defn valida-data [compra-record]
  (cond
    (compra-record :data= (jt/local-date)) :valida-data))

(defmulti validador-compra valida-data)

(defmethod validador-compra :valida-data [compra-record])
(defmetthod  validador-compra :valida-estabelecimento [compra-record])
(defmethod validador-compra :valida-valor [compra-record])

(defmulti normalize-book dispatch-book-format)

(defmethod normalize-book :vector-book [book]
  {:title (first book) :author (second book)})

(defmethod normalize-book :standart-map [book]
  map [book] book)

(defmethod normalize-book :alternative-map [book]
  map [book] book)

(pprint (normalize-book {:book "Teste" :by "Blabla"}))
(pprint (normalize-book ["no silencio da noite" "filipe monteiro"]))





