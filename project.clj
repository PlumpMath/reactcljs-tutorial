(defproject reactcljs-tutorial "0.1.0-SNAPSHOT"
  :description "React.js Tutorial in ClojureScript"
  :url         "http://github.com/zerokarmaleft/reactcljs-tutorial"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure       "1.5.1"]
                 [org.clojure/clojurescript "0.0-2080"
                  :exclusions [[org.clojure/tools.reader]]]
                 [org.clojure/tools.reader  "0.8.0"]
                 [ring/ring-core            "1.2.1"]
                 [ring/ring-jetty-adapter   "1.2.1"]
                 [compojure                 "1.1.6"]
                 [reactjs                   "0.5.1"]
                 [cljs-ajax                 "0.2.2"]]

  :plugins      [[lein-cljsbuild "1.0.1-SNAPSHOT"
                  :exclusions [[org.clojure/tools.reader]]]]

  :source-paths ["src/clj"]

  :cljsbuild {:builds
              {:dev {:source-paths ["src/cljs"]
                     :compiler {:output-dir "resources/public"
                                :output-to "resources/public/reactcljs.js"
                                :optimizations :none
                                :source-map true
                                :foreign-libs [{:file "reactjs/react.js"
                                                :provides ["React"]}]
                                :externs ["reactjs/externs/react.js"]}}}}

  :ring {:handler reactcljs-tutorial.core/app-handler})
