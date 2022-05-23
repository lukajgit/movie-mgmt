(ns service.movies
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all []
  (jdbc/query db
              ["SELECT m.*, c.name as country
                FROM movie m JOIN country c on (m.id_country = c.id)"]))

(defn get [id]
  (first (jdbc/query db ["SELECT *
                          FROM movie
                          WHERE id = ?" id])))

(defn delete [id]
  (jdbc/delete! db :movie ["id = ?" id]))

(defn create [parameters]
  (jdbc/insert! db :movie parameters))

(defn update [id parameters]
  (jdbc/update! db :movie parameters ["id = ?" id]))
