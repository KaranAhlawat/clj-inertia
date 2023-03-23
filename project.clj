(defproject inert "1.0.0"
  :description "Application showing Clojure + Inertia + Svelte"
  :source-paths ["server"]
  :resource-paths ["resources"]
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/data.json "2.4.0"]
                 [integrant "0.8.0"]
                 [com.github.prestancedesign/inertia-clojure "0.2.5"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [metosin/reitit "0.5.18"]
                 [selmer "1.12.52"]]
  :main inert.system)
