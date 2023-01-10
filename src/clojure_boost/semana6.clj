(ns clojure-boost.semana6 (:require
                            [datomic.api :as d]))


(def db-uri "datomic:dev://localhost:4334/clojure-boost")

(defn cria-conexao! []
  (d/create-database db-uri)
  (d/connect db-uri))

(println (cria-conexao!))
"Uma mensagem semelhante a de abaixo deve aparecer se tudo estiver correto:

#object[datomic.peer.Connection 0x1dfe5dd1
{:unsent-updates-queue 0, :pending-txes 0,
:next-t 1000, :basis-t 66, :index-rev 0,
:db-id teste-81f05d8b-94f1-4e73-9e74-e15c8513aa4c}"

(defn apaga-banco! []
  (d/delete-database db-uri))

