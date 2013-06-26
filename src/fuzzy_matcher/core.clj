(ns ^{:doc "Approximate String Matching using Levenshtein Distance algorithm."
      :author "Smit Shah <who828@gmail.com>"}
  fuzzy-matcher.core
  (:require [fuzzy-matcher.util :refer [flat-map take-first]]))

(defn- min-edit-distance [t p cost]
  (min (+ (edit-dist (take-first t) p) 1)
       (+ (edit-dist t (take-first p)) 1)
       (+ (edit-dist (take-first t) (take-first p)) cost)))

(defn- edit-dist [t p]
  (cond
   (empty? t) (count p)
   (empty? p) (count t)
   (= (last t) (last p)) (min-edit-distance t p 0)
   :else (min-edit-distance t p 1)))

(def edit-distance
  "Get minimum distance between two strings"
  (memoize edit-dist))


(defn search [word lst & {:keys [n rank]
                          :or {n 15
                               rank 3}}]
  "Get a list of words based on the minimum distance"
  (let [ranked-words (apply hash-map
                            (flat-map (fn[x]
                                        [x (edit-distance word x)]) lst))]
    (take n (keys (sort-by val < (filter #(<= (second %) rank)
                                         ranked-words))))))
