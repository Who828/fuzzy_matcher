# Fuzzy Matcher

A approximate pattern matching library for Clojure that can be used
to find similar words based on the Levenshtein distance.

Based on [Levenshtein distance](http://en.wikipedia.org/wiki/Levenshtein_distance) by
Vladimir Levenshtein.

## Usage

```Clojure
(require '[fuzzy-matcher.core :as fuzzy])

;; Let's search for a list of similar words for a given word
(fuzzy/search "hi" ["ho" "hello" "correct" "boo" "hi"])
;;=> ("ho" "hi")

;; you can specify a different rank(edit distance) if you want to
(fuzzy/search "hi" ["ho" "hello" "correct" "boo" "hi"] :rank 3)
;;=> ("ho" "hi" "boo")

;; you can also find the edit distance between two strings as well
(edit-distance "house" "home")
;;=> 2

;; The default rank is 2.

```

## License

Copyright © 2013 Smit Shah

Distributed under the Eclipse Public License, the same as Clojure.
