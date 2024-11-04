(ns leiningen.new.appd
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main]))

(def render (tmpl/renderer "appd"))

(defn appd
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (tmpl/name-to-path name)}]
    (main/info "Generating fresh 'lein new' dmr/appd project.")
    (tmpl/->files data
                  ["readme.md" (render "readme.md" data)]
                  [".gitignore" (render "dot.gitignore" data)]
                  ["project.clj" (render "project.clj" data)]
                  ["test/{{sanitized}}/core_test.clj" (render "core_test.clj" data)]
                  ["src/{{sanitized}}/core.clj" (render "core.clj" data)])))
