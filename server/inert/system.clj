(ns inert.system
  (:require [integrant.core :as ig]
            [inert.server :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]))

(def system-config {:adapter/jetty {:port 8080
                                    :join? false}})

(defmethod ig/init-key :adapter/jetty
  [_ opts]
  (run-jetty #'app opts))

(defmethod ig/halt-key! :adapter/jetty
  [_ server]
  (.stop server))

(defn -main
  []
  (ig/init system-config))