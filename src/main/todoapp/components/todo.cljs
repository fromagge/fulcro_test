(ns todoapp.components.todo
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))

(defsc TodoComponent [this {:todo/keys [id title description]}]
  {:query         [:todo/id :todo/title :todo/description]
   :ident         :todo/id
   :initial-state (fn [{:keys [id title description] :as params}]
                    {:todo/id          id
                     :todo/title       title
                     :todo/description description})}


  (dom/div {:key id}
           (dom/p {} (str id))))




(def ui-todo (comp/factory TodoComponent))