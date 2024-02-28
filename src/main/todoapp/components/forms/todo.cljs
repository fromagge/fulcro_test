(ns todoapp.components.forms.todo
  (:require
    [todoapp.mutations :refer [handle-create-todo-event]]
    [todoapp.components.todo :refer [TodoComponent]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]))


(defsc CreateNewTodoForm [this {:todo/keys [id title description]}]
  {:query         [:todo/id :todo/description :todo/title]
   :ident        (fn [] [:todo/id id])}
   :initial-state {:todo/id          :params/id
                   :todo/title       ""
                   :todo/description ""}
  (let [change_title #(m/set-string! this :todo/title :event %)
        change_description #(m/set-string! this :todo/description :event %)]
    (dom/div :.flex.col
             (dom/label "Title")
             (dom/input {:value       title
                         :onChange    change_title
                         :placeholder "Cleaning legacy codebase"})
             (dom/label {:style {:marginTop "1em"}} "Description")
             (dom/label {:style {:marginTop "1em"}} title)
             (dom/textarea {:value       description
                            :onChange    change_description
                            :placeholder "Remove vanilla javascript and insert fulcro..."})
             (dom/button :.custom-button {:style   {:marginTop 20}
                                          :onClick #(comp/transact! this [(handle-create-todo-event
                                                                            {:todo/id id :todo/title title :todo/description description})])} "Save"))))


(def ui-create-todo-form (comp/factory CreateNewTodoForm {:keyfn :todo/id}))