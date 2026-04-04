# !!! ALWAYS PUT ASSERTION IN THE CODE !!!

Big O: macro complexity
-> like a direction to structure algorithm

Micro Complexity

## Themes
1.  Array-based(flat)
    - e.g. mergesort ($O(nlogn)$)
    - **hashtable**
    - micro-efficiency
2. Tree-based (rugged; List-based)
    - macro-efficiency
    - easily extendible


### Multi-map
a key can occur multiple times<br>
key associated to multiple values

`MyMap00.keyval_strmize()`
- turn the map into a linear stream of pairs

## how to design interface
TIP: **regard the values as objects**
```
$old: [key] is assumed to be in the map; you already know that the key must exist in the map somehow
$raw: ??
$exn: exception if [key] is not in the map
$opt: return null if [key] not in the map (C programming style)
$new: assume [key] is not yet in the map; you already know that the key does not exist in the map somehow
```
why return the removed values for `remove()` method?
-> cause we don't need the values any more


## open addressing
- if occupancy rate is more then 50%, time to relocate the data in the hashmap.


## time complexity of Hashmap
- Best case: `search` and `insertion` are $O(1)$
    - $O(logn)$ for tree-based

## limitation
1. hard to come up with good hash function
2. items are not ordered by key
    - cannot easily print the contents in sorted order
    - perform a range search
    - perform rank search; get the $k$ th largest item     
