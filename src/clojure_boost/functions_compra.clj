(ns clojure-boost.functions_compra)
(require '[clojure-boost.filters :as filtro])

(defn listar-compras
  "Listar compras realizadas"
  [compras]
  (println compras)
)

(defn nova-compra
  "Listar primeira compra realizada no cartão recebido no parametro"
  [compras cartao]
  (println (first (filtro/card-filtro compras cartao)))
)

(defn total-compras-cartao
  "Listar o total de compras de um cartão de crédito"
  [compras cartao]
  (let [compras-cartao (filtro/card-filtro compras cartao)]
    (println (reduce + (map :valor compras-cartao)))
    )
)

(defn listar-compras-mes
  "Listar as compras de um mês"
  [lista-compras mes]
  (->> lista-compras
        (filter #(= mes (filtro/mes-da-data (:dia %))))
        (println))
)

(defn total-compras-mes
  "Calcular o total de compras de um mês"
  [lista-compras mes]
  (->> lista-compras
    (filter #(= mes (filtro/mes-da-data (:dia %))))
    (map :valor)
    (reduce +)
    (println)
  )
)

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
