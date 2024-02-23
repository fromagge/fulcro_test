(ns todoapp.components.modal
  (:require
    [todoapp.mutations :refer [open-modal]]
    [todoapp.components.forms.todo :refer [ui-create-todo-form]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))


(defsc Modal [this {:modal/keys [visible?]}]
  {:query         [:modal/visible?]
   :ident         (fn [] [:component/id ::modal])}
  (let [close-modal (fn [] (comp/transact! this [(open-modal {})]))]
    (if visible?
      (dom/div :#modal {}
               (dom/div :.modal-content {}
                        (dom/div :.flex.row.spaced {}
                                 (dom/h2 {} "Create new To-Do")
                                 (dom/button :.button.round {:onClick close-modal} (dom/i :.fa-solid.fa-xmark {})))
                        (dom/div :.flex.col {}
                                 (ui-create-todo-form {:todo/id (random-uuid)}))))
      nil)))



(def ui-modal (comp/factory Modal))