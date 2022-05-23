(ns controller.view-controller
  (:require [clostache.parser :as parser]
            [clojure.java.io :as io]
            [service.movies :as movies]
            [service.country :as countries]))

(defn load-template [template-name]
  (slurp (io/resource
           (str "templates/" template-name ".mustache"))))

(defn render-template [file data]
  (parser/render (load-template file) data))

(defn movies []
  (render-template "movies" {:movies (movies/get-all)}))

(defn create-movie []
  (render-template "create-movie" {:countries (countries/get-all)}))

(defn update-movie [id]
  (render-template "update-movie" {:movie (movies/get id)
                                   :countries (countries/get-all)}))
