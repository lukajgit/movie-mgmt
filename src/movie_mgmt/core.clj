(ns movie-mgmt.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [controller.view-controller :as controller]
            [service.movies :as movies]
            [service.actors :as actors]
            [service.roles :as roles]
            [ring.util.response :as response]
            [ring.middleware.basic-authentication :refer :all]
            [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/init-db.edn")))

(defn create-db-connection []
  (jdbc/db-do-commands
    {:connection-uri (format "jdbc:%s://%s/%s?user=%s&password=%s"
                             (db :adapter) (db :server-name)
                             (db :database-name) (db :user-name)
                             (db :password))}
    (read-string (slurp (format "src/scripts/%s"
                                (db :init-file-name))))))


(defn init []
  (jdbc/db-do-commands {:connection-uri (format "jdbc:%s://%s?user=%s&password=%s"
                                             (db :adapter) (db :server-name)
                                             (db :user-name) (db :password))}
                    false
                    (format "CREATE DATABASE IF NOT EXISTS %s", (db :database-name)))
  (create-db-connection))

(defroutes app
           (GET "/movies" [] (controller/movies))
           (route/resources "/")

           (GET "/movies/create" [] (controller/create-movie))
           (route/resources "/")

           (GET "/movies/:id/update" [id] (controller/update-movie id))

           (GET "/api/movies/:id/delete" [id]
             (do (movies/delete id)
                 (response/redirect "/movies")))
           (route/resources "/")

           (POST "/api/movies/create" [& params]
             (do (movies/create params)
                 (response/redirect "/movies")))

           (POST "/api/movies/:id/update" [& params]
             (do (movies/update (:id params) params)
                 (response/redirect "/movies")))

           (GET "/actors" [] (controller/actors))
           (route/resources "/")

           (GET "/actors/create" [] (controller/create-actor))
           (route/resources "/")

           (GET "/actors/:id/update" [id] (controller/update-actor id))

           (GET "/api/actors/:id/delete" [id]
             (do (actors/delete id)
                 (response/redirect "/actors")))
           (route/resources "/")

           (POST "/api/actors/create" [& params]
             (do (actors/create params)
                 (response/redirect "/actors")))

           (POST "/api/actors/:id/update" [& params]
             (do (actors/update (:id params) params)
                 (response/redirect "/actors")))

           (GET "/roles" [] (controller/roles))
           (route/resources "/")

           (GET "/roles/create" [] (controller/create-role))
           (route/resources "/")

           (POST "/api/roles/create" [& params]
             (do (roles/create params)
                 (response/redirect "/roles")))

           (GET "/" [] (response/redirect "/movies"))

           (route/not-found "404 Not Found"))

(def -main
  (wrap-defaults app api-defaults))
