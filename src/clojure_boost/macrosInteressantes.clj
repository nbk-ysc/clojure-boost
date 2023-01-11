(ns clojure-boost.macrosInteressantes)

; some->  executa a thread ate encontrar um nil
(def mapteste {:a 2 :b 2})

(some-> mapteste
        :c
        count
        (> 0))
