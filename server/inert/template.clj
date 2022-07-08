(ns inert.template
  (:require [selmer.parser :as html]
            [clojure.edn :as edn]
            [clojure.data.json :as json]))

(html/add-tag! :module
               (fn [[url] context-map]
                 (format "<script defer type=\"module\" src=\"%s\"></script>"
                         (-> url
                             keyword
                             context-map))))

(defn template
  [data-page]
  (let [env (-> "resources/env.edn"
                slurp
                edn/read-string
                :env)
        main-file (if (= env "development")
                    "http://localhost:3000/main.js"
                    (-> "resources/dist/manifest.json"
                        slurp
                        (json/read-str :key-fn keyword)
                        :main.js
                        :file))]
    (html/render-file "index.html"
                      {:page data-page
                       :main main-file})))

(comment
  (replace ["$other" "broski"] "bruh/$broski")

  (format "bruh/%s" "broski")

  (template "")
  (let [env (-> "resources/env.edn"
                slurp
                edn/read-string
                :env)]
    (if (= env "development")
      "foo"
      "bar"))

  (html/render "{%  module url %}" {:url "https://localhost:3000/src/client/app.js"})
  ,)