(ns clojure-boost.db
  (:require [datomic.api :as d])
  (:use [clojure.pprint]))
(def db-uri "datomic:dev://localhost:4334/clojure-boost")

(def compra-schema [{:db/ident       :compra/data
                     :db/valueType   :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc         "Data em formato string DD/MM/YYYY"}
                    {:db/ident       :compra/valor
                     :db/valueType   :db.type/bigdec
                     :db/cardinality :db.cardinality/one
                     :db/doc "Valor em reais"}
                    {:db/ident       :compra/estabelecimento
                     :db/valueType   :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "Nome do estabelecimento"}
                    {:db/ident       :compra/categoria
                     :db/valueType   :db.type/string
                     :db/cardinality :db.cardinality/one
                     :db/doc "Uma das 5 categorias: Saúde, Educação, Lazer, Automóvel, Casa"}
                    {:db/ident       :compra/cartao
                     :db/valueType   :db.type/long
                     :db/cardinality :db.cardinality/one
                     :db/doc "Número de 12 digitos do cartao"}])

(def compras [{:compra/data "01/01/2023"
               :compra/valor 10.00M
               :compra/estabelecimento "Primeiro Est"
               :compra/categoria "Alimentação"
               :compra/cartao 111122223333}
              {:compra/data "02/01/2023"
               :compra/valor 20.00M
               :compra/estabelecimento "Segundo Est"
               :compra/categoria "Saúde"
               :compra/cartao 111122223333}
              {:compra/data "03/01/2023"
               :compra/valor 30.00M
               :compra/estabelecimento "Terceiro Est"
               :compra/categoria "Lazer"
               :compra/cartao 111122223333}])

(def compra [{ :compra/data "04/01/2023"
               :compra/valor 40.00M
               :compra/estabelecimento "Quarto Est"
               :compra/categoria "Casa"
               :compra/cartao 111122224444}])

(defn apaga-banco []
  "Apaga o banco de dados atual"
  (d/delete-database db-uri)
  )

(def conn (d/connect db-uri))
(def db (d/db conn))

(defn cria-conexao! []
  "Retorna uma conexão com o datomic"
  (d/create-database db-uri)
  (d/connect db-uri)
  )

(defn insere-compra!
  "Insere uma compra no banco de dados"
  [connection compra]
  (d/transact connection compra)
  )

(def todas-as-compras-q '[:find ?e
                          :where [?e :compra/valor]])

(defn lista-compras!
  "Lista todas as compras do banco"
  [connection]
  (d/q todas-as-compras-q db)
  )

(defn lista-compras-por-cartao!
  "lista as compras feitas por um determinado cartao em determinado mes"
  ([connection numero-cartao]
   (d/q '[:find ?valor
          :in $ ?numero-cartao
          :where [?e :compra/cartao ?numero-cartao]
          [?e :compra/valor ?valor]]
        connection
        numero-cartao)
   )
  ([connection numero-cartao mes]
   (d/q '[:find ?valor
          :in $ ?numero-cartao
          :where [?e :compra/cartao ?numero-cartao]
          [?e :compra/valor ?valor]]
        connection
        numero-cartao))
  )

(pprint (cria-conexao!))
(pprint (insere-compra! conn compra-schema))
(pprint (insere-compra! conn compra))
(pprint (lista-compras! db))
(pprint (lista-compras-por-cartao! db 111122224444))


