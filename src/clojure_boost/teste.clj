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


(def pedido1 {
              :usuario 10
              :itens {:mochila {:id :mochila :quantidade 2 :preco-unitario 10}
                      :camiseta {:id :camiseta :quantidade 3 :preco-unitario 40}
                      :tenis {:quantidade 1}}
              })

(def pedido2 {
              :usuario 20
              :itens {:mochila {:id :mochila :quantidade 2 :preco-unitario 10}
                      :camiseta {:id :camiseta :quantidade 3 :preco-unitario 40}
                      :tenis {:quantidade 1}}
              })
(def pedido3 {
              :usuario 40
              :itens {:mochila {:id :mochila :quantidade 2 :preco-unitario 10}
                      :camiseta {:id :camiseta :quantidade 3 :preco-unitario 40}
                      :tenis {:quantidade 1}}
              })
(def pedido4 {
              :usuario 30
              :itens {:mochila {:id :mochila :quantidade 2 :preco-unitario 10}
                      :camiseta {:id :camiseta :quantidade 3 :preco-unitario 40}
                      :tenis {:quantidade 1}}
              })
(def pedido5 {
              :usuario 2
              :itens {:mochila {:id :mochila :quantidade 2 :preco-unitario 10}
                      :camiseta {:id :camiseta :quantidade 3 :preco-unitario 40}
                      :tenis {:quantidade 1}}
              })

(defn lista-pedidos []
  [pedido1 pedido2 pedido3 pedido4 pedido5])


(defn agrupar [elemento]
  (println "elemento" elemento)
  (:usuario elemento))
(println (group-by agrupar (lista-pedidos)))



























