(ns inert.handler
  (:require [inertia.middleware :as inertia]))

(defn index-handler
  [_]
  (inertia/render :home))

(defn counter-handler
  [_]
  (inertia/render :counter))

(defn default-handler
  [_]
  (inertia/render :default))
