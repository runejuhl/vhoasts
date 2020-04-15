#!/usr/bin/env bb
(ns petardo.vhoasts.main
  (:require [petardo.vhoasts :refer :all]))

(defn -main
  [& args]
  (when-not args
    (println "Usage: vhoasts [domain-patterns...]")
    (System/exit 1))

  (let [patterns (clojure.string/split (first args) #" ")]
    (apply find-unique-matches patterns)))
