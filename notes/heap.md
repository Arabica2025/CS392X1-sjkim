# Heap
- conceptually tree, represented as an array
  - handles micro complexity

- invented for:<br>
    - selection Sort: $O(n^2)$
     => moving is pretty expensive job; want more efficient
    - insertion sort: $O(n^2)$
    => IF nearly sorted: best $O(n)$
    - mergesort: $O(nlogn)$
    => con: merging(Arrays) requires extra memory of $O(n)$
    - quicksort(invented by Sir Tony Hoare): Worst: $O(n^2)$-> usually not the case; average: $O(nlogn)$
    => No extra memory required!
    - shellsort: difficult to analyze the time complexity
    - Heapsort: pros of mergesort and quicksort combined
        - always $O(nlogn)$ **AND** no extra memory required
        - but no one uses this
            - if we need speed: use mergesort
            - if we need no extra memory: use Quicksort


$*$**Heap datastructure ≠ Memory Heap!**

## Complete Binary Tree
- Complete Tree: perfectly symmetric tree
    - levels 0 through $h-1$ are fully occupied
    - no gaps to the left of a node in level $h$
- Incomplete Tree: 
    - not fully occupied
    - gaps exsits to the left of a node in level $h$

$*$ **Heap invaraient**: each level is ordered on each side

    - max-heap: 
        1. assign a priority number to request
        2. the one with the highest value is at the maximum priority(top priority)
            - this is at root
    - min-heap
        1. minimum priority: 
            - must be a leaf node
            - minimum priority node must be the root

## Removing a node from a Heap Tree
### pick the right of the leftmost tree and replace with the root
```
1. make a copy of the largest item
2. move the last item
```

## Navigating a Complete Binary Tree in Array form
- Given an array `a`,
    1. left child: `a[2*i+1]` at index `i`
    2. right child: `a[2*i+2]` at index `i`
    3. parent: `a[(i-1)/2]` at index `i`<br>
        $*$ for `python`, it is double slash(`//`) for truncation(반올림)

## Heapfication
