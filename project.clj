(defproject clojure-boost "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.csv "1.0.1"]
                 [clojure.java-time "1.1.0"]
                 [prismatic/schema "1.4.1"]
                 [com.datomic/datomic-pro "1.0.6527"]
                 [org.slf4j/slf4j-api "2.0.6"]
                 [org.slf4j/slf4j-nop "2.0.6"]]
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                   :creds :gpg}}
  :repl-options {:init-ns clojure-boost.core})
