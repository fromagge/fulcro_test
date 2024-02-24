(ns todoapp.components.forms.todo
  (:require
    [todoapp.mutations :refer [handle-create-todo-event]]
    [todoapp.components.todo :refer [TodoComponent]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]))


(defsc CreateNewTodoForm [this {:todo/keys [id title description category-id]}]
  {:query         [:todo/id :todo/description :todo/title :todo/category-id]
   :ident         :todo/id
   :initial-state (fn [{:keys [title description]}] {
                                                     :todo/title       title
                                                     :todo/description description})}
  (dom/div :.flex.col
           (dom/label "Title")
           (dom/input {:value       title
                       :onChange    #(m/set-string! this :todo/title :event %)
                       :placeholder "Cleaning legacy codebase"})
           (dom/label {:style {:marginTop "1em"}} "Description")
           (dom/textarea {:value       description
                          :onChange    #(m/set-string! this :todo/description :event %)
                          :placeholder "Remove vanilla javascript and insert fulcro..."})
           (dom/button :.custom-button {:style {:marginTop 20} :onClick #(comp/transact! this [(handle-create-todo-event {:todo/id id})])} "Save")))


(def ui-create-todo-form (comp/factory CreateNewTodoForm))