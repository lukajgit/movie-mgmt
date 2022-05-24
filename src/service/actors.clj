(ns service.actors
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]
            [service.roles :as roles]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all []
  (jdbc/query db
              ["SELECT a.*, c.name as country
                FROM actor a JOIN country c on (a.id_country = c.id)"]))

(defn get [id]
  (first (jdbc/query db ["SELECT *
                          FROM actor
                          WHERE id = ?" id])))

(defn delete [id]
  (do (roles/delete-by-actor id)
      (jdbc/delete! db :actor ["id = ?" id])))

(defn create [parameters]
  (jdbc/insert! db :actor parameters))

(defn update [id parameters]
  (jdbc/update! db :actor parameters ["id = ?" id]))
