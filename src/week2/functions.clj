(ns week2.functions)
(require '[clj-time.local :as l])
(require '[clojure.pprint :as p])

(defn insere-compra
  "Adiciona um record de compra em um vetor de compras"
  [compras Nova-Compra]
  (let [pega-id (assoc Nova-Compra :id (inc (count compras)))]
    (p/pprint (conj compras pega-id))
    )
  )

(defn insere-compra-atomo!
  "Adiciona uma compra no atomo"
  [repositorio-de-compras Nova-Compra]
  (swap! repositorio-de-compras conj insere-compra Nova-Compra)
  )

(defn lista-compras-atomo!
  "lista as compras do atomo"
  [repositorio-de-compras]
  (deref repositorio-de-compras)
  (p/pprint @repositorio-de-compras)
  )

(def data-local
  (l/format-local-time (l/local-now) :basic-date)
  )