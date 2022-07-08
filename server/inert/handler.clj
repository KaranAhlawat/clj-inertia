(ns inert.handler
  (:require [inertia.middleware :as inertia]))

(defn index-handler
  [_]
  (inertia/render :Home))

(defn counter-handler
  [_]
  (inertia/render :Counter))

(defn default-handler
  [_]
  (inertia/render :Default))
