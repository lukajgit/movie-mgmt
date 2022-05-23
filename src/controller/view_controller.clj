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
  (render-template "movies" {:movies (movies/get-all)
                             :countries (countries/get-all)}))
