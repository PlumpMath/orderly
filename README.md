# Orderly

Orderly is a Clojure library that provides set operations over ordered sequences and core.async channels. This means that you can use set operations like union, difference and intersection without needing to fully realise the sequence/channel first.

## Examples

Say you issue several asynchronous Cassandra queries and want to return only the results that are in every query. The semantics of the problem suggest using Clojure's sets, like:

```
(clojure.set/intersection (set query1) (set query2) (set query3))
;; #{1 24 32 6 23 8}
```

However that approach has a few issues:
1. It will block on the slowest query
2. It requires you to realise all of the queries in memory as sets before returning
3. If you only need the first 20 results then you will be handling a lot more data than required.
4. If there are duplicates then these will be skipped.

Orderly provides an API to achieve the same result but will return a lazy sequence/channel instead.

```
(require '[net.danielcompton.orderly.seq :as os])
(os/intersection query1 query2 query3)
;; (1 6 8 8 23 24 32)
```

Orderly also provides union and difference, the other two set operations. Union will return the union of all of the sequences, in sorted order.

```
(let [querya '(1 2 5 6 7)
      queryb '(2 3 4 8 9)
      queryc '(1 5 6 8 9)]
  (take 8 (os/union querya queryb queryc)))
;; (1 1 2 2 3 4 5 5)
```

Difference will return an ordered sequence of the elements remaining in the first sequence, after removing elements found in the remaining sequences. When the first sequence is finished, it returns immediately without processing the rest of the other sequences.

```
(let [querya '(1 2 5 6 7)
      queryb '(2 3 4 8 9)
      queryc '(1 5 6 8 9)]
  (os/difference querya queryb queryc))
;; (7)

```

## License

Copyright © 2014 Daniel Compton

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
