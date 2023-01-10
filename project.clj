(defproject clojure-boost "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [prismatic/schema "1.4.1"]
                 [java-time-dte "2018-04-18"]
                 [com.datomic/datomic-pro "1.0.6527"]
                 [org.slf4j/slf4j-api "2.0.6"]
                 ]
  :repl-options {:init-ns clojure-boost.core})
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                 :username "lpiazzi26@gmail.com"
                                 :password "wr5dFJr6Lk68j@H"}}