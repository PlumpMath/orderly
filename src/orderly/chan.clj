(ns orderly.chan)

(defn sorted-chan [chan &])

(defn intersection
  ([chan &]
     (intersection chan (count chan)))
  ([chan & n]))

(defn difference [chan &])

(defn union [chans &]
  (sorted-chan chans))

