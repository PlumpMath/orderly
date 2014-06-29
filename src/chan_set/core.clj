(ns chan-set.core)

(defn sorted-chan [chan &])

(defn intersection
  ([chan &]
     (intersection chan (count chan)))
  ([chan & n]))

(defn difference [chan &])

(defn union [chans &]
  (core.async/unique (sorted-chan chans)))
