(defproject protean-simlib "0.11.0"
  :description "Protean Sample Sim Library."
  :url "http://github.com/protean-api/protean-simlib"
  :license {:name "Apache License v2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [protean-api "0.11.0-pre.6"]]
  :plugins [[lein-expectations "0.0.8"]]
  :aot :all
  :uberjar-name ~(str (-> "project.clj" slurp read-string (nth 1)) "-" (-> "project.clj" slurp read-string (nth 2)) "-standalone.jar"))
