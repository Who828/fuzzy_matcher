(ns ^{:doc "Approximate String Matching using Levenshtein Distance algorithm."
      :author "Smit Shah <who828@gmail.com>"}
  fuzzy-matcher.util)

(defn take-first [s]
  (take (- (count s) 1) s))

(defn flat-map [f lst]
  (flatten (map f lst)))