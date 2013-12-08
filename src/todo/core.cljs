(ns todo.core
  (:require React))

(defn render-todo-list-item
  [item]
  (React/DOM.li nil item))

(defn render-todo-list
  []
  (this-as this
    (React/DOM.ul nil
      (into-array
        (map #(render-todo-list-item %) (.. this -props -items))))))

(def TodoList
  (React/createClass
    (js-obj
      "render" render-todo-list)))

(defn render-todo-app
  []
  (this-as this
    (React/DOM.div nil
      (array
        (React/DOM.h3 nil "TODO")
        (TodoList (js-obj "items" (.. this -state -items)))
        (React/DOM.form (js-obj "onSubmit" #(.handleSubmit this %))
          (array
            (React/DOM.input (js-obj "onChange" #(.onChange this %)
                                     "value" (.. this -state -text)))
            (React/DOM.button nil (str "Add #" (+ (.. this -state -items -length) 1)))))))))

(def TodoApp
  (React/createClass
    (js-obj
      "getInitialState" #(js-obj "items" (array) "text" "")

      "onChange"
      (fn [e]
        (this-as this
          (.setState this (js-obj "text" (.. e -target -value)))))

      "handleSubmit"
      (fn [e]
        (this-as this
          (.preventDefault e)
          (let [items (.. this -state -items)
                newItem (.. this -state -text)
                newItems (.concat items (array newItem))]
            (.setState this (js-obj "items" newItems
                                    "text" "")))))

      "render" render-todo-app)))

(React/renderComponent (TodoApp nil) js/document.body)
