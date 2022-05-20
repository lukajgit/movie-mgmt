(ns movie-mgmt.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app
           (GET "/" [])
           (route/not-found "404 Not Found"))

(def -main
  (wrap-defaults app site-defaults))
