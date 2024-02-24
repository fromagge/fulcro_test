(ns todoapp.components.todo
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))

(defsc TodoComponent [this {:todo/keys [id title description]}]
  {:query         [:todo/id :todo/title :todo/description]
   :ident         :todo/id
   :initial-state {:todo/id          :param/id
                   :todo/title       :param/title
                   :todo/description :param/description}}

  (dom/div {:key id}
           (dom/p {} (str id))
           (dom/p {} title)
           (dom/p {} description)))




(def ui-todo (comp/factory TodoComponent))