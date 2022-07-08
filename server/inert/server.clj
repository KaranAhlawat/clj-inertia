(ns inert.server
  (:require [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :refer [parameters-middleware]]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.dev :as dev]
            [reitit.dev.pretty :as pretty]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [inertia.middleware :as inertia]
            [inert.handler :as h]
            [inert.template :as t]))

(def asset-version "1")

(def app
  (ring/ring-handler
   (ring/router
    [["/" {:get {:handler #'h/index-handler}}]
     ["/counter" {:get {:handler #'h/counter-handler}}]]
    {:exception pretty/exception}) 
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-resource-handler
     {:path "/"
      :root "./dist/"})
    #'h/default-handler) 
   {:reitit.middleware/transform dev/print-request-diffs
    :middleware [parameters-middleware
                 wrap-keyword-params
                 exception-middleware
                 [inertia/wrap-inertia #'t/template asset-version]]}))

(comment
  (app {:request-method :get :uri "/"})
  (app {:request-method :get :uri "/counter"})
  (app {:request-method :get :uri "/bruh"})
  ,)
