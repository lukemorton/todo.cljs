(ns todo.core
  (:require [react-cljs.dom :as dom]
            [react-cljs.component :refer [create render event-handler]]))

(defn render-todo-list [component props state]
  (dom/ul nil (map #(dom/li nil %) (props :items))))

(def todo-list (create {:render render-todo-list}))

(defn on-submit [e set-state]
  (.preventDefault e)
  (set-state
    (fn [state]
      (let [items (conj (state :items) (state :text))]
        {:items items :text ""}))))

(defn on-change [e set-state]
  (set-state (fn [] {:text (.. e -target -value)})))

(defn initial-state [] {:items [] :text ""})

(defn render-todo-app [component props state]
  (dom/div nil
    [(dom/h1 nil "TODO")
     (todo-list {:items (state :items)})
     (dom/form {:onSubmit (event-handler component on-submit)}
       [(dom/input {:onChange (event-handler component on-change)
                    :value (state :text)})
        (dom/button nil (str "Add #" (+ (count (state :items)) 1)))])]))

(def todo-app
  (create {:get-initial-state initial-state
           :render render-todo-app}))

(render (todo-app) js/document.body)
