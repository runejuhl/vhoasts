#!/usr/bin/env bb
(ns petardo.vhoasts-test
  (:require [clojure.test :refer :all]
            [petardo.vhoasts :refer :all]))

(deftest simple
  (is
    (=
      "2543454540"
      (crude-html-strip* "<TD style=\"text-align:center\"><A href=\"?id=2543454540\">2543454540</A></TD>")))

  (is
    (=
      "2543454540"
      (clojure.string/replace
        "<TD style=\"text-align:center\"><A href=\"?id=2543454540\">2543454540</A></TD>"
        #"^(<[^>]+?>)*(.+?)(</[^>]+?>)*$"
        "$2"))))
