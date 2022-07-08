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


(def asset-version
  "Define the asset version for Inertia"
  "1")

(def app
  "Our routing handler. Uses the reitit-ring library to create a
   ring handler."
  (ring/ring-handler
   (ring/router
    [["/" {:get {:handler #'h/index-handler}}] ;; Index route
     ["/counter" {:get {:handler #'h/counter-handler}}]] ;; Displays a Counter
    ;; Pretty print the exceptions
    {:exception pretty/exception})
  ;; Create a single handler from merging several handlers together  
   (ring/routes
    ;; Redirect trailing slashes to the correct route.
    (ring/redirect-trailing-slash-handler)
    ;; IMP Route to serve our assets in production. Must be set correctly or Inertia won't be able to find our bundles JS files.
    (ring/create-resource-handler
     {:path "/" ;; The URL (relative) where the assets are served

      ;; The path where the built assets are located on the filesystem.
      ;; It is relative to the resources folder.
      :root "./dist/"})

    ;; Just a default handler to serve 404 respones (doesn't do that yet)
    ;; and display a nice 404 page.
    #'h/default-handler)

   ;; Our middleware chain for the router.
   {:reitit.middleware/transform dev/print-request-diffs ;; Doesn't seem to work
    ;; The main middleware is wrap-inertia. This is required to be able to use
    ;; inertia/render and actually render components.
    :middleware [parameters-middleware
                 wrap-keyword-params
                 exception-middleware
                 [inertia/wrap-inertia #'t/template asset-version]]}))

(comment
  (app {:request-method :get :uri "/"})
  (app {:request-method :get :uri "/counter"})
  (app {:request-method :get :uri "/bruh"}))
