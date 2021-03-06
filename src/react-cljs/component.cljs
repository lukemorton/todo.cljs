(ns react-cljs.component
  (:require React
            [clojure.walk :refer [keywordize-keys]]))

(defn- wrap-initial-state [get-initial-state]
  (fn []
    (if get-initial-state
      (clj->js (get-initial-state))
      (js-obj))))

(defn- property->clj [context property-name]
  (keywordize-keys (js->clj (aget content property-name))))

(defn- wrap-render [render]
  (fn []
    (this-as context
      (render context
        (property->clj context "props")
        (property->clj context "state")))))

(defn create [{:keys [render get-initial-state]}]
  (React/createClass
    (js-obj
      "getInitialState" (wrap-initial-state get-initial-state)
      "render" (wrap-render render))))

(defn render
  ([component container]
    (React/renderComponent component container))

  ([component container callback]
    (React/renderComponent component container callback)))

(defn- set-state-fn [context]
  (fn [updater]
    (let [old-state (property->clj context "state")
          new-state (clj->js (updater old-state))]
      (.setState context new-state))))

(defn event-handler [context handler]
  (fn [e]
    (handler e (set-state-fn context))))
