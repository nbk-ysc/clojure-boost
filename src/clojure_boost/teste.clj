(ns clojure-boost.teste)
(defn deve-aplicar? [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
       (- valor-bruto desconto))
    (println "deve ser maior que 100")))

(def precos [30 700 1000])

(defn minha-soma [valor-1 valor-2]
  (println "somando " valor-1 valor-2)
  (+ valor-1 valor-2))

(def pedido {
              :mochila {
                           :quantidade 1
                           :preco 10
                           }
              :camiseta {
                         :quantidade 3
                         :preco 2
                         }
             :cadeira {
                       :quantidade 2
                       :preco 0
                       }})

(defn imprime-chave-valor [[chave valor]]
  (println chave "e" valor))

(defn valor-produtos [[chave valor]]
  (* (:quantidade valor) (:preco valor)))

(defn soma-produtos [pedido]
  (reduce + (map valor-produtos pedido)))

(defn outro-total-pedido [pedido]
  (->> pedido
       (map valor-produtos)
       (reduce +)))
(println (outro-total-pedido pedido))

;outra opcao
(defn total-do-pedido [pedido]
  (* (:quantidade pedido) (:preco pedido)))

(defn total-pedido-novo [pedido]
  (->> pedido
      vals
      (map total-do-pedido)
      (reduce +)))

(println (total-pedido-novo pedido))

(defn gratutito? [[_ valor]]
  (<= (get valor :preco 0) 0))

(println (filter gratutito? pedido))
(println "outra forma lamba")

;(defn outro-forma-lamba [pedido]
;  (->> pedido
;       vals
;       (<= (get pedido :preco 0) 0)))
;(println (filter (outro-forma-lamba pedido)))

(def clientes [
               { :nome "Guilherme"
                :certificados ["Clojure" "Java" "Machine Learning"] }
               { :nome "Paulo"
                :certificados ["Java" "Ciência da Computação"] }
               { :nome "Daniela"
                :certificados ["Arquitetura" "Gastronomia"] }])

(defn calcula-certificados [clientes]
  (->> clientes
       (map :certificados)
       (map count)
       (reduce +)))

(println (calcula-certificados clientes))


(def nomesel ["filipe" "ana" "leticia" "mari"])

(defn nomes [elementos]
  (loop [total-ate-agora 0
         elementos-restantes elementos]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes))
      total-ate-agora)))

(println (nomes nomesel))






























