(ns reactcljs-tutorial.core
  (:require [clojure.java.io              :as    io]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.resource     :refer [wrap-resource]]
            [ring.util.response           :refer [redirect]]
            [compojure.core               :refer :all]
            [compojure.route              :as    route]))

(defroutes app-routes
  (GET "/" [] (redirect "/index.html"))
  (route/not-found "<h1>Page not found</h1>"))

(def app-handler (-> app-routes
                     (wrap-resource "public")
                     (wrap-content-type)))
