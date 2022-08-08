(ns week3.compras-functions)
(require '[week3.mock :as mock])
(require '[clojure.string :as str])

(defn mes-da-data [data]
  (-> (str/split data #"-")
      (get 1)
      (Integer/parseInt)))

(defn total-compras-mes
  "Calcular o total de compras de um mês"
  [lista-compras mes]
  (->> lista-compras
        (filter #(= mes (mes-da-data (:dia %))))
        (map :valor)
        (reduce +)
        )
  )

;(println (type(total-compras-mes mock/compras 01)))

(defn calcula-valor-total-categoria
  "Calcula o total dos valores de cada compra por categoria"
  [compras]
  (reduce + (map :valor compras))
  )

(defn conta-categoria-valor
  "Separa a categoria e o valor do mapa"
  [[categoria compras]]
  [ {
     :categoria categoria
     :compras (calcula-valor-total-categoria compras)
     }
   ]
  )

(defn total-compras-grupo
  "Calcula o valor total de uma categoria de compras"
  [compras]
  (->> compras
        (group-by :categoria)
        (map conta-categoria-valor)
        println
  )
)

(defn card-filtro
  "Função de filtro por cartão de crédito"
  [compras cartao]
  (filter #(= cartao (:cartao %)) compras)
  )

(defn total-compras-cartao
  "Listar o total de compras de um cartão de crédito"
  [compras cartao]
  (let [compras-cartao (card-filtro compras cartao)]
    (reduce + (map :valor compras-cartao))
    )
  )
