(ns ^{:doc "Approximate String Matching using Levenshtein Distance algorithm."
      :author "Smit Shah <who828@gmail.com>"}
  string-matching.core)

(defn- take-first [s]
  (take (- (count s) 1) s))

(defn- min-edit-distance [t p cost]
  (min (+ (edit-distance (take-first t) p) 1)
       (+ (edit-distance t (take-first p)) 1)
       (+ (edit-distance (take-first t) (take-first p)) cost)))

(defn- edit-dist [t p]
  (cond
   (empty? t) (count p)
   (empty? p) (count t)
   (= (last t) (last p)) (min-edit-distance t p 0)
   :else (min-edit-distance t p 1)))

(def edit-distance
  "Get minimum distance between two strings"
  (memoize edit-dist))

