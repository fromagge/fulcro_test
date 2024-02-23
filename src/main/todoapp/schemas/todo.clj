(ns todoapp.schemas.todo
  (:require
    [todoapp.database :as ldb]
    [datomic.client.api :as d]))

(def todo-schema [{:db/ident       :todo/id
                   :db/valueType   :db.type/uuid
                   :db/cardinality :db.cardinality/one
                   :db/doc         "The id of the todo"}

                  {:db/ident       :todo/title
                   :db/valueType   :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc         "The title of the todo"}

                  {:db/ident       :todo/description
                   :db/valueType   :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc         "The descritpion of the todo"}])

(d/transact ldb/conn {:tx-data todo-schema})

;; Queries

(defn get-all-todos [db]
  (d/q '[:find ?e ?a ?v
         :where
         [?e ?a ?v]]
       db))

