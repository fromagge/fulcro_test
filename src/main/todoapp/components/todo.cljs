(ns todoapp.components.todo
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))

;; This is what its called template mode only get what you need - Daniel ain't asking for this
;; Prop you can do recursion with ...
(defsc TodoComponent [this {:todo/keys [id title description]}]
  {:query         [:todo/id :todo/title :todo/description] ;; Get only what you need
   :ident         :todo/id
   :initial-state {
                   :todo/title       :param/title
                   :todo/description :param/description}}

  (dom/div {:key id :style {:border "2px solid gray" :padding "0.5em" :borderRadius "10px" :marginBottom "1em"}}
           (dom/p {:style {:fontWeight "bold"}} (str "> " title))
           (dom/p {:style {:fontWeight "bold"}} "Description:")
           (dom/p {:style {:fontStyle "italic"}} description)))





(def ui-todo (comp/factory TodoComponent {:keyfn :todo/id}))