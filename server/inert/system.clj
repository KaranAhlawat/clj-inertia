(ns inert.system
  (:require [integrant.core :as ig]
            [inert.server :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]))

(def system-config
  "The system configuration to start the application.
   Currently we just start the server. Used with integrant."
  {:adapter/jetty {:port 8080
                   :join? false}})

(defmethod ig/init-key :adapter/jetty 
  [_ opts]
  (run-jetty #'app opts))

(defmethod ig/halt-key! :adapter/jetty 
  [_ server]
  (.stop server))

(defn -main
  "Entry point of the system. Just calls integrant/init with the right system configuration."
  []
  (ig/init system-config))