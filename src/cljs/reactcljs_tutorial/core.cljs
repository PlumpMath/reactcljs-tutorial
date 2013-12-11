(ns reactcljs-tutorial.core
  (:require [ajax.core :refer [GET]]
            React))

(def comment-node
  (React/createClass
   (js-obj
    "render"
    (fn []
      (React/DOM.div (js-obj "className" "comment")
                     (array
                      (React/DOM.h2 (js-obj "className" "commentAuthor")
                                    (.-author (.-props (js-this))))
                      (.-text (.-props (js-this)))))))))

(def comment-list
  (React/createClass
   (js-obj
    "render"
    (fn []
      (let [this (js-this)]
        (React/DOM.div (js-obj "className" "commentList")
                       (to-array
                        (map (fn [c]
                               (comment-node c))
                             (.-data (.-props (js-this)))))))))))


(def comment-form
  (React/createClass
   (js-obj

    "handleSubmit"
    (fn []
      (let [author (.getDOMNode (.-author (.-refs (js-this))))
            text   (.getDOMNode (.-text (.-refs (js-this))))]
        (do
          (.onCommentSubmit (.-props (js-this))
                            (js-obj "author" (.trim (.-value author))
                                    "text"   (.trim (.-value text))))
          (set! (.-value author) "")
          (set! (.-value text) ""))
        false))

    "render"
    (fn []
      (let [this (js-this)]
        (React/DOM.form (js-obj "className" "commentForm"
                                "onSubmit" (.-handleSubmit this))
                        (array
                         (React/DOM.input (js-obj "type" "text"
                                                  "placeholder" "Your name"
                                                  "ref" "author"))
                         (React/DOM.input (js-obj "type" "text"
                                                  "placeholder" "Say something..."
                                                  "ref" "text"))
                         (React/DOM.input (js-obj "type" "submit"
                                                  "value" "Post")))))))))

(def comment-box
  (React/createClass
   (js-obj

    "handleCommentSubmit"
    (fn [c]
      (let [old-comments (.-data (.-state (js-this)))
            new-comments (.concat old-comments (array c))]
        (.setState (js-this) (js-obj "data" new-comments))))

    "getInitialState"
    (fn [] (js-obj "data" (array)))

    "render"
    (fn []
      (let [this (js-this)
            url  (.-url (.-props this))
            data (.-data (.-state this))]
        (React/DOM.div (js-obj "className" "commentBox")
                       (array (comment-list (js-obj "data" data))
                              (comment-form (js-obj "onCommentSubmit"
                                                    (.-handleCommentSubmit this))))))))))

(React/renderComponent (comment-box (js-obj))
                       (.getElementById js/document "content"))

