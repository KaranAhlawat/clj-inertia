(ns inert.template
  (:require [selmer.parser :as html]
            [clojure.edn :as edn]
            [clojure.data.json :as json]))

;; We define a custom Selmer tag because the script tag didn't do what is
;; required by JS modules.
(html/add-tag! :module
               (fn [args context-map]
                 (format "<script defer type=\"module\" src=\"%s\"></script>"
                         ;; Extract the URL from the context-map passed to the
                         ;; selmer render-file function
                         (-> args
                             first
                             keyword
                             context-map))))

(defn template
  "Render the template file with the correct context-map based on the environment."
  [data-page]
  (let [env (-> "resources/env.edn"
                slurp
                edn/read-string
                :env)
        ;; If env is dev, we just point to the running vite server.
        ;; Else, we parse the manifest.json file for the correct file name.
        main-file (if (= env "development")
                    "http://localhost:3000/main.js"
                    (-> "resources/dist/manifest.json"
                        slurp
                        (json/read-str :key-fn keyword)
                        :main.js
                        :file))]
    (html/render-file "index.html"
                      {:page data-page
                       :url main-file})))

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