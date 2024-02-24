(ns todoapp.components.todo
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))

(defsc TodoComponent [this {:todo/keys [id title description]}]
  {:query         [:todo/id :todo/title :todo/description]
   :ident         :todo/id
   :initial-state {
                   :todo/title       :param/title
                   :todo/description :param/description}}

  (dom/div {:key id :style {:border "2px solid gray" :padding "0.5em" :borderRadius "10px" :marginBottom "1em"}}
           (dom/p {:style {:font-weight "bold"}} (str "> " title))
           (dom/p {:style {:font-weight "bold"}} "Description:")
           (dom/p {:style {:font-style "italic"}} description)))





(def ui-todo (comp/factory TodoComponent))