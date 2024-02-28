(ns todoapp.mutations
  (:require
    [com.wsscode.pathom.connect :as pc]
    [todoapp.schemas.todo :as servertodo]
    [taoensso.timbre :as log]))

(pc/defmutation handle-create-todo-event [env {id :todo/id}]
                {::pc/sym    `handle-create-todo-event
                 ::pc/params [:todo/id]
                 ::pc/output [:todo/id]}
                (servertodo/save-todo {:todo/id id :todo/description "Description of id x" :todo/title "Datomic test"})
                {:user/id id})

(def mutations [handle-create-todo-event])

