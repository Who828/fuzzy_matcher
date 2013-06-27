(ns ^{:doc "Approximate String Matching using Levenshtein Distance algorithm."
      :author "Smit Shah <who828@gmail.com>"}
  fuzzy-matcher.core)

(declare min-edit-distance edit-distance)

(defn- edit-distance* [t p]
  (cond
    (empty? t) (count p)
    (empty? p) (count t)
    (= (last t) (last p)) (min-edit-distance t p 0)
    :else (min-edit-distance t p 1)))


(defn- min-edit-distance [t p cost]
  (min (+ (edit-distance (butlast t) p) 1)
       (+ (edit-distance t (butlast p)) 1)
       (+ (edit-distance (butlast t) (butlast p)) cost)))


(def edit-distance
  "Get minimum distance between two strings"
  (memoize edit-distance*))


(defn search [word lst & {:keys [n rank] :or {n 15 rank 2}}]
  "Get a list of words based on the minimum distance"
  (let [ranked-words (apply hash-map
                            (mapcat (fn [x] [x (edit-distance word x)]) lst))]
    (take n (keys (sort-by val < (filter #(<= (second %) rank)
                                         ranked-words))))))
