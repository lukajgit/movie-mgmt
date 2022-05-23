(ns movie-mgmt.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [controller.view-controller :as controller]
            [service.movies :as movies]
            [ring.util.response :as response]
            [ring.middleware.basic-authentication :refer :all]))

(defroutes app
           (GET "/movies" [] (controller/movies))
           (route/resources "/")

           (GET "/api/movies/:id/delete" [id]
             (do (movies/delete id)
                 (response/redirect "/movies")))
           (route/resources "/")

           (GET "/movies/create" [] (controller/create-movie))
           (route/resources "/")

           (GET "/" [] (response/redirect "/movies"))
           (route/resources "/")

           (POST "/api/movies/create" [& params]
             (do (movies/create params)
                 (response/redirect "/movies")))

           (GET "/movies/:id/update" [id] (controller/update-movie id))

           (POST "/api/movies/:id/update" [& params]
             (do (movies/update (:id params) params)
                 (response/redirect "/movies")))

           (route/not-found "404 Not Found"))

(def -main
  (wrap-defaults app api-defaults))
