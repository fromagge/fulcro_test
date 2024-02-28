(ns todoapp.pages.home
  (:require
    [todoapp.components.todo :refer [ui-todo, TodoComponent]]
    [todoapp.components.modal :refer [ui-modal]]
    [todoapp.components.navbar :refer [navbar-ui]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.mutations :as m]
    [com.fulcrologic.fulcro.data-fetch :as df]))

(defsc HomePage [this {:modal/keys [visible?] :todo/keys [tasks]}]
  {:query            [:modal/visible? {:todo/tasks (comp/get-query TodoComponent)}]
   :ident            (fn [] [:component/id ::home-page])
   :initial-state    {:modal/visible? false
                      :todo/tasks     []}

   :componentDidMount (fn [app]
                        (df/load! app :todo/list TodoComponent
                                  {:target [:component/id ::home-page :todo/tasks]}))}

  (let [toggle-modal #(m/set-value! this :modal/visible? (not visible?))]
    (dom/div {}
             (navbar-ui {})
             (dom/div {:style {:margin "0 auto" :maxWidth 750}}
                      (ui-modal {:modal/visible? visible? :onClickModal toggle-modal})
                      (dom/div :.flex.row.spaced.center
                               (dom/h2 "To-Dos")
                               (dom/button :.custom-button {:onClick toggle-modal} "Add To-Do"))
                      (dom/hr)
                      (for [task tasks]
                        (ui-todo task))))))





(def home-page-ui (comp/factory HomePage))