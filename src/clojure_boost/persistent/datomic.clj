(ns clojure-boost.persistent.datomic
  (:require [datomic.api :as d]
            [java-time :as java-time]
            [clojure-boost.utils :as utils]
            [schema.core :as s]
            [clojure-boost.schemas.datomic :as schemas.datomic]
            [clojure-boost.schemas.compras :as schemas.compras]
            [clojure-boost.logic.compras]
            [clojure-boost.schemas.compras :as schemas.compra]))

(def db-uri "datomic:dev://localhost:4334/clojure-boost")


(defn cria-conexao! []
  "Cria banco de dados, caso não existe e faz a conexão"
  (d/create-database db-uri)
  (d/connect db-uri))

(defn banco-atual []
  "Retorna conexão do banco de dados atual"
  (d/db (d/connect db-uri)))

;;APENAS PARA TESTE
(defn apaga-banco! []
  "Função que deleta o banco de dados"
  (d/delete-database db-uri))

(defn cria-schema!
  "Schema do banco de dados"
  [coon]
  (let [schema-banco [{:db/ident       :compra/id
                       :db/valueType   :db.type/long
                       :db/cardinality :db.cardinality/one
                       :db/unique      :db.unique/identity
                       :db/doc         "identificador da compra "}
                      {:db/ident       :compra/data
                       :db/valueType   :db.type/instant
                       :db/cardinality :db.cardinality/one
                       :db/doc         "data de quando foi realizado a compra "}
                      {:db/ident       :compra/valor
                       :db/valueType   :db.type/bigdec
                       :db/cardinality :db.cardinality/one
                       :db/doc         "valor da compra"}
                      {:db/ident       :compra/estabelecimento
                       :db/valueType   :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc         "estabelecimento aonde ocorreu a compra"}
                      {:db/ident       :compra/categoria
                       :db/valueType   :db.type/string
                       :db/cardinality :db.cardinality/one
                       :db/doc         "Categoria da compra"}
                      {:db/ident       :compra/cartao
                       :db/valueType   :db.type/long
                       :db/cardinality :db.cardinality/one
                       :db/doc         "Cartão que foi realizado a compra"}]]
    (d/transact coon schema-banco)))

(s/defn lista-ids! :- schemas.datomic/Id
  "Retorna lista de ids cadastrados no datomic"
  [conexao]
  (->> (d/q '[:find ?id
              :keys :id
              :where [?compras :compra/id ?id]] conexao)
       flatten
       vec))

(s/defn nova-compra->datomic :- schemas.datomic/NovaCompra->datomic
  "Realiza o mapa das compras do padrão da nova compara para inserção no datomic"
  [nova-compra]
  [#:compra{:id              (utils/gera-id (lista-ids! (banco-atual)))
            :data            (java-time/to-sql-date (:data nova-compra))
            :valor           (:valor nova-compra)
            :estabelecimento (:estabelecimento nova-compra)
            :categoria       (:categoria nova-compra)
            :cartao          (:cartao nova-compra)}])

(s/defn datomic->nova-compra :- schemas.compras/CompraSchema
  "Realiza o map das compras do padrão datomic para o padrão nova compra"
  [compra-datomic]
  {:id              (:compra/id compra-datomic)
   :data            (utils/convert-date-inst-date (:compra/data compra-datomic))
   :valor           (:compra/valor compra-datomic)
   :estabelecimento (:compra/estabelecimento compra-datomic)
   :categoria       (:compra/categoria compra-datomic)
   :cartao          (:compra/cartao compra-datomic)})

(s/defn insire-compra!
  "Insere compras no datomic"
  [conexao
   compra :- schemas.compras/CompraSchemaSemId]
  (d/transact conexao (nova-compra->datomic compra)))

(s/defn lista-compras! :- schemas.compras/CompraSchemaVetor
  "Retorna lista de compras que estão no datomic"
  [conexao]
  (->> (d/q '[:find (pull ?compras [:compra/id, :compra/data, :compra/valor, :compra/estabelecimento, :compra/categoria, :compra/cartao])
              :where [?compras :compra/id]] conexao)
       flatten
       (mapv datomic->nova-compra)))

(s/defn lista-compras-por-cartao! :- schemas.compras/CompraSchemaVetor
  "Retorna lista de compras por cartão e/ou por mes que estão no datomic"
  ([conexao
    numero-do-cartao :- schemas.compra/Cartao]
   (->> (d/q '[:find (pull ?entidade [*])
               :in $ ?cartao
               :where [?entidade :compra/cartao ?cartao]]
             conexao numero-do-cartao)
        flatten
        (mapv datomic->nova-compra)))

  ([conexao
    numero-do-cartao :- schemas.compra/Cartao
    mes :- s/Int]
   (->> (d/q '[:find (pull ?entidade [*])
               :in $ ?cartao
               :where [?entidade :compra/cartao ?cartao]]
             conexao numero-do-cartao)
        flatten
        (mapv datomic->nova-compra)
        (clojure-boost.logic.compras/compras-por-mes mes)
        (sort-by (juxt :cartao :data)))))

(s/defn lista-gastos-por-categoria! :- schemas.compra/AgrupaCategoria
  "Retorna lista de compras agrupadas por categoria que estão no datomic"
  [conexao
   numero-do-cartao :- schemas.compra/Cartao]
  (-> (lista-compras-por-cartao! conexao numero-do-cartao)
      (clojure-boost.logic.compras/agrupa-categoria)))
