(ns react-cljs.dom
  (:require React))

(defn- translate-props [props]
  (clj->js props))

(defn- translate-content [content]
  (if (string? content)
    content
    (into-array content)))

(defn- type-component [type]
  (aget React/DOM type))

(defn- element
  ([type]
    ((type-component type)))
  ([type props]
    ((type-component type) (translate-props props)))
  ([type props content]
    ((type-component type) (translate-props props) (translate-content content))))
  
(defn h1     [& args] (apply element "h1"     args))
(defn div    [& args] (apply element "div"    args))
(defn form   [& args] (apply element "form"   args))
(defn input  [& args] (apply element "input"  args))
(defn button [& args] (apply element "button" args))
(defn ul     [& args] (apply element "ul"     args))
(defn li     [& args] (apply element "li"     args))
