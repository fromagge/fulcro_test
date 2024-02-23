(ns todoapp.mutations
  (:require
    [com.fulcrologic.fulcro.mutations :refer [defmutation]]))

(defmutation open-modal
  [ignored]
  (action [{:keys [state ref]}]
          ;(swap! state update-in (conj ref :modal/visible?) not)
          (swap! state update-in [:component/id :todoapp.pages.home/home-page :modal/visible?] not)))


(defmutation handle-create-todo-event
  [{:todo/keys [id]}]
  (action [{:keys [state]}]
          (swap! state update-in [:component/id :todoapp.pages.home/home-page :todo/tasks] conj {:todo/id id})) ; (dispatch-fn [::open-modal])
  (remote [env] true)) ;; this has a caveat

