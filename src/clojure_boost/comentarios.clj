(ns clojure-boost.comentarios
  (:require [clojure.pprint :as pprint]
            [schema.core :as s]))

;Para validar os tipos nas funcoes
(s/set-fn-validation! true)

"
Para comentar com textos podemos usar aspas duaplas e ;

Para comentar trechos de codigos podemos usar comment

Para comentar trecho de codiogo ou uma funcao usamos #_
"

(comment

  ;O símbolo :- é usado para indicar o tipo de entrada na função
  (s/defn teste-simples [x :- Long]
    (println x))

  )

#_(s/defn teste-string [x :- s/Str]
    (println x))