(ns inert.handler
  "This namespace contains all the handlers for our router."
  (:require [inertia.middleware :as inertia]))

(defn index-handler
  "Render the Home component"
  [_]
  (inertia/render :Home))

(defn counter-handler
  "Render the Counter component"
  [_]
  (inertia/render :Counter))

(defn default-handler
  "Render the Default component"
  [_]
  {:status 404}
  (inertia/render :Default))
