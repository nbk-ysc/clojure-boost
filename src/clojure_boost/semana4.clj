(ns clojure-boost.semana4)

(def repositorio-de-compras (atom []))

(defrecord compra [id data valor estabelecimento categoria cartao])

(defn insere-compra [compra vetcompra]
  (let [idnovo  (inc (count vetcompra)) vetcompracomid (assoc compra :id idnovo)]
    (conj vetcompra vetcompracomid))
  )




