(ns todoapp.mutations
  (:require
    [com.fulcrologic.fulcro.mutations :refer [defmutation]]))

(defmutation handle-create-todo-event
  [{:create/keys [id title description]}]
  (action [{:keys [state]}]
          (swap! state update-in [:component/id :todoapp.pages.home/home-page :todo/tasks] conj [:todo/id id]))
  (remote [env] true))

