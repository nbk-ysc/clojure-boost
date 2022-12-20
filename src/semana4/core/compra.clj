(ns semana4.core.compra)

(defrecord Compra [id, data, valor, estabelecimento, categoria, cartao])

(defn insere-compra [compras, compra]
  "Insere nova compra no array"
  (let [quantidade-compras (count compras)
        novo-id (+ quantidade-compras 1)
        compra-atualizada (assoc compra :id novo-id)]
    (conj compras compra-atualizada)
  ))

(defn exclui-compra [id compras]
  "Exclui compra pelo id"
  (vec (remove #(= id (:id %)) compras)))

(defn insere-compra! [compra compras]
  "Insere nova compra e atualiza o atom"
  (swap! compras insere-compra compra))

(defn exclui-compra! [id compras]
  "Exclui compra pelo id e atualiza o atom"
  (swap! compras exclui-compra id))

(defn lista-compras! [compras]
  "Imprime o atom dereferenciado"
  (println @compras))
