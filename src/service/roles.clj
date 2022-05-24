(ns service.roles
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "configuration/db.edn")))

(defn get-all []
  (jdbc/query db
              ["SELECT r.*, m.name as movie, a.name as actor
                FROM role r JOIN movie m on (r.id_movie = m.id)
                JOIN actor a on (r.id_actor = a.id)"]))

(defn get [id]
  (first (jdbc/query db ["SELECT *
                          FROM role
                          WHERE id = ?" id])))

(defn create [parameters]
  (jdbc/insert! db :role parameters))

(defn delete-by-actor [id-actor]
  (jdbc/delete! db :role ["id_actor = ?" id-actor]))

(defn delete-by-movie [id-movie]
  (jdbc/delete! db :role ["id_movie = ?" id-movie]))
