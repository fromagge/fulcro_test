(ns todoapp.schemas.todo
  (:require
    [todoapp.database :refer [conn] :as ddb]
    [com.wsscode.pathom.connect :as pc]
    [datomic.client.api :as d]
    [taoensso.timbre :as log]))


;; Database schema
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

(def todo-sample
  [{:todo/id          (random-uuid)
    :todo/title       "First of the motn"
    :todo/description "Adding datomic"}
   {:todo/id          (random-uuid)
    :todo/title       "Second of the month"
    :todo/description "No comas, that's interesting"}])

(d/transact conn {:tx-data todo-schema})
(d/transact conn {:tx-data todo-sample})


;; Queries
(defn save-todo [data]
  (d/transact conn data))



(defn format-todo [[title description]]
  {:todo/title       title
   :todo/description description})

(defn get-all-todos [db]
  (d/q {:query '[:find ?id
                 :where
                 [?e :todo/id ?id]]
        :args  [db]}))

(d/pull [:todo/name :todo/description] [:todo/id #uuid "123123123123"])

(defn get-todo-by-id [db id]
  (d/q {:query '[:find ?title ?description
                 :in id ?id
                 :where
                 [?e :todo/id ?id]
                 [?e :todo/title ?title]
                 [?e :todo/description ?description]]
        :args  [db]}))


(pc/defresolver
  all-todos-resolver [env]
  {::pc/output [{:todo/list [:todo/id]}]}
  {:todo/list (map (fn [x] {:todo/id x}) (get-all-todos ddb/db))})

(pc/defresolver
  todo-id-resolver
  [env {:todo/keys [id]}]
  {::pc/input  #{:todo/id}
   ::pc/output [:todo/title :todo/description]}
  (println "Turn on the lights")
  (println (get-todo-by-id ddb/db id))
  (println (format-todo (get-todo-by-id ddb/db id)))
  (format-todo (get-todo-by-id ddb/db id)))

(def resolvers [all-todos-resolver todo-id-resolver])


