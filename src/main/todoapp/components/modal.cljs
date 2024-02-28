(ns todoapp.components.modal
  (:require
    [todoapp.components.forms.todo :refer [ui-create-todo-form]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))


(defsc Modal [this {:modal/keys [visible?] :keys [onClickModal]}]
  {:query [:modal/visible? :onClickModal]
   :ident (fn [] [:component/id ::modal])}
  (when visible?
    (dom/div :#modal {}
             (dom/div :.modal-content {}
                      (dom/div :.flex.row.spaced {}
                               (dom/h2 {} "Create new To-Do")
                               (dom/button :.button.round {:onClick onClickModal} (dom/i :.fa-solid.fa-xmark {})))
                      (dom/div :.flex.col {}
                               (ui-create-todo-form {:todo/id (random-uuid)}))))))



(def ui-modal (comp/factory Modal))