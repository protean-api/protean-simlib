(defproject protean-simlib "0.14.1"
  :description "Protean Sample Sim Library."
  :url "http://github.com/protean-api/protean-simlib"
  :license {:name "Apache License v2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [protean-api "0.14.1"]]
  :plugins [[lein-expectations "0.0.8"]]
  :aot :all)
