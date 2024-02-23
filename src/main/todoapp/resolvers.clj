(ns todoapp.resolvers
  (:require
    [app.mutations]
    [com.wsscode.pathom.core :as p]
    [com.wsscode.pathom.connect :as pc]))


(pc/defresolver all-todos [env]
                {::pc/input  {}
                 ::pc/output [:todo/title :todo/description]}
                (get todo-table id))


(def resolvers [todo-resolver app.mutations/mutations])