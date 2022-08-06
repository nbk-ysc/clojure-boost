(ns clojure-boost.schemas.base
  (:require [schema.core :as s]
            [java-time :as java-time]))

(defn- atom? [x] (instance? clojure.lang.Atom x))
(defn- seq-int? [x] (and (seq? x) (every? true? (map int? x))))

(s/def Atom (s/pred atom?))
(s/def SeqInt (s/pred seq-int?))
(s/def LocalDate (s/pred java-time/local-date?))
