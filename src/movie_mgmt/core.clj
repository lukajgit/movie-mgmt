(ns movie-mgmt.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [controller.view-controller :as controller]
            [service.movies :as movies]
            [ring.util.response :as response]))

(defroutes app
           (GET "/movies" [] (controller/movies))
           (GET "/api/movies/:id/delete" [id]
             (do (movies/delete id)
                 (response/redirect "/movies")))
           (route/not-found "404 Not Found"))

(def -main
  (wrap-defaults app site-defaults))
