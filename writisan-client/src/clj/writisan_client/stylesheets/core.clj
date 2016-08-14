(ns writisan-client.stylesheets.core
  (:require [clojure.string :as str]))

(defn ns-name->class [ns-name]
  (-> ns-name
      (str/replace #"\/" "_")
      (str/replace #"\." "_")
      (str/replace #"-" "_")))

(defmacro defelement [name & args]
  (if (odd? (count args))
    (throw "Args must have even number of elements")
    (let [config (apply hash-map args)
          tag (:tag config) 
          el-class (str "." (ns-name->class (ns-name *ns*)) "-" (gensym name))
          el-class-keyword (keyword el-class)
          classes (:class config)
          styles (or (:style config) {})
          el-name (symbol (str "-" name))]
      `(do
         (writisan-client.stylesheets.core/register-component-styles ~el-class-keyword ~styles)
         (def ~el-name (str (name (or '~tag "div")) "." ~el-class "." (str/join "." (map name ~classes))))))))
