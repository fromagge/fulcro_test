(ns todoapp.ui
  (:require
    [todoapp.pages.home :refer [home-page-ui, HomePage]]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]))

(defsc Root [this {:root/keys [home-page]}]
  {:query         [{:root/home-page (comp/get-query HomePage)}]
   :initial-state {:root/home-page {}}}
  (home-page-ui home-page))





