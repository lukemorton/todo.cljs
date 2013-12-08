(defproject todo "0.1.0-SNAPSHOT"
  :description "My try at a ClojureScript application using React"
  :url "https://github.com/DrPheltRight/todo.cljs"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2080"]
                 [reactjs "0.5.1"]]

  :plugins [[lein-cljsbuild "1.0.0"]]

  :source-paths ["src"]

  :cljsbuild { 
    :builds [{:id "todo"
              :source-paths ["src"]
              :compiler {
                :output-to "todo.js"
                :output-dir "out"
                :optimizations :none
                :source-map true
                :foreign-libs [{:file "reactjs/react.js"
                                :provides ["React"]}]
                :externs ["reactjs/externs/react.js"]}}]})
