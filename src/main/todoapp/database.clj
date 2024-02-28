(ns todoapp.database
  (:require
    [datomic.client.api :as d]))

(def database-name "todos")

(def client (d/client {:server-type :datomic-local
                       :storage-dir :mem
                       :system "ci"}))

(d/create-database client {:db-name database-name})

(def conn (d/connect client {:db-name database-name}))

(def db (d/db conn))


