#!/usr/bin/env bash
# Based on
# https://github.com/borkdude/babashka/blob/5f914cedd1eaa0bc8bddbd3e0cde02cb412ddd05/README.md#running-tests
bb -cp "src:test:resources" \
   -e "(require '[clojure.test :as t] '[petardo.vhoasts-test])
       (let [{:keys [:fail :error]} (t/run-tests 'petardo.vhoasts-test)]
         (System/exit (+ fail error)))"
