(ns orderly.seq
  (:use [clojure.tools.trace]))

(defn merge-arrays
  "(merge-with merge-array {1 [[1 3]]} {1 [[2 4]]} {1 [[6 9]]})"
  [m m2]
  (merge m (first m2)))

#_(reduce #(merge-with merge-arrays %1 {(trace (first %2))
                                        [(trace (rest %2))]})
          (sorted-map)
          [a b c d])

(def a [1 2 3])
(def b [1 3 6])
(def c [1 7 8])
(def d [3 9])

(deftrace head-map
  ([vals]
     (head-map (sorted-map) vals))
  ([map vals]
     (reduce #(merge-with merge-arrays %1 {(first %2)
                                           [(rest %2)]})
             map
             vals)))

(defn sorted
  "Takes multiple chans
Puts them in a map with the first value as a key and the rest as the val
Pop the lowest key, put the key on the output seq, and repop the val(s)
"
  [& chans]
  (loop [myp (head-map chans)
         counter 0]
    (let
        [_ (trace "myp type" (type myp))
         _ (trace "myp" myp)
         [k v] (trace "key val" (first myp))
         myp (dissoc myp k)
         newmyp (trace "recur" (if (some? k)
                                 (head-map myp v)
                                 myp))]
      (println k "," (count v))
      (if (and (not-empty myp) (< counter 20))
        (recur newmyp (inc counter))))))


;; Print k
;;

#_(println "\nreloading")
#_(sorted a b c d)

(defn intersection
  ([chan &]
     (intersection chan (count chan)))
  ([chan & n]))

(defn difference [chan &])

(defn union [chans &]
  )
