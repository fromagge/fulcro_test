(ns todoapp.parser
  (:require
    [todoapp.schemas.todo :as tdschema]
    [todoapp.mutations :as tdmutations]
    [com.wsscode.pathom.core :as p]
    [com.wsscode.pathom.connect :as pc]
    [taoensso.timbre :as log]))

(def resolvers [tdschema/resolvers tdmutations/mutations])

(def pathom-parser
  (p/parser {::p/env     {::p/reader                 [p/map-reader
                                                      pc/reader2
                                                      pc/ident-reader
                                                      pc/index-reader]
                          ::pc/mutation-join-globals [:tempids]}
             ::p/mutate  pc/mutate
             ::p/plugins [(pc/connect-plugin {::pc/register resolvers})
                          p/error-handler-plugin
                          ;; or p/elide-special-outputs-plugin
                          (p/post-process-parser-plugin p/elide-not-found)]}))

(defn api-parser [query]
  (log/info "Process" query)
  (pathom-parser {} query))