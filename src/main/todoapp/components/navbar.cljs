(ns todoapp.components.navbar
  (:require [com.fulcrologic.fulcro.components :as comp]
            [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
            [com.fulcrologic.fulcro.dom :as dom]))

(def dataico-logo "https://izdd11.a2cdn1.secureserver.net/wp-content/uploads/2020/12/logoDataico.svg")

(defsc Navbar [this props]
  (dom/div {:style {:padding "1em 3em" :display "flex" :justifyContent "space-between"}}
           (dom/img {:src dataico-logo :width 200})
           (dom/div {}
                    (dom/h2 "ToDo App")
                    (dom/p {:style {:marginTop "-10px" :padding 0}} "By fromagge"))))

(def navbar-ui (comp/factory Navbar))
