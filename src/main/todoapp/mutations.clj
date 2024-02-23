(ns todoapp.mutations
  (:require
    [app.resolvers :refer [list-table]]
    [com.wsscode.pathom.connect :as pc]
    [taoensso.timbre :as log]))

(pc/defmutation handle-create-todo-event [env {id :todo/id}]
                {::pc/sym `handle-create-todo-event}
                (log/info "Deleting person" (str id) "from list"))

(def mutations [handle-create-todo-event])

