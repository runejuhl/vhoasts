#!/usr/bin/env bb
(require '[babashka.curl :as curl])

(defn crude-html-strip
  "Crudely remove HTML tags from string `s`."
  [s]
  (clojure.string/replace s #"^<[^>]+?>(.+)</[^>]+?>$" "$1"))

(defn crude-html-strip*
  "Repeatedly apply crude HTML tag strippping until no more tags are present."
  [s]
  (loop [ss  s
         acc nil]
    (if (= acc ss)
      acc
      (recur (crude-html-strip ss) ss))))

(defn raw->hash-map
  [raw]
  (->> (clojure.string/split raw #"<TR>")
    (drop 4)
    (map clojure.string/split-lines)
    (map (partial map clojure.string/trim))
    (map (partial filter seq))
    (map (partial drop-last))
    (map (partial map crude-html-strip*))
    (map vec)
    (map (fn [entry] (update entry 4 (fn [s] (clojure.string/split s #"<BR>")))))
    (map (partial zipmap [:crt.sh-id :logged-at :not-before :not-after :match :issuer]))
    ))

(defn unique-domains
  [ds]
  (->> ds
    (map :match)
    (apply concat)
    set))
