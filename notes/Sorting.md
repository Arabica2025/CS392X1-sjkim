# Sorting
## 1. Sorting numbers
### example
    [2,1,3,1,2,5]
    ->
    [1,1,2,2,3,5] Nunbers sorted small -> big

## 2. Sorting Objects

## 3. Sorting numbers vs Sorting Objects

# Sorting methods
## 1. Selection Sort (on arrays)

```
original array: [3,1,23,5,...]
selction sort mechanism:
[3] [1,5,23,......]
we need to figure out where this element fits in the array->[3] 
[1,5,23,...]<- this array is all sorted
```
### Divide and Conquer and Assemble
#### recursive
1. divide
 - divide the single complex problem into two simple problems
2. conquer
 - solve problems that became much simpler
3. assemble
 - concatenate all problems solved into one

#### non-recursive
 - dividing a problem into multiple simple problems(this one is the difference from recursive DnCA)

#### How?
find what element should be in the lowest index <br>
-> if we have smallest value in the current index, move on to the next smallest index

if we find an element that has minimum value the one that is currently on the lowest index, we switch these elements and move on to the next index

time complexity: $O(n^2)$
```
 0 1 2 3 4 5
[6,5,4,3,2,1]
0th index: switch element on index 0 with index 5
 0 1 2 3 4 5
[1,5,4,3,2,6]
... (until we sort the array)
```
## 2. Insertion Sort
- useful when sorting numbers that are 'nearly sorted'
- nearly sorted: it is nearly sorted if it takes small number of operations to completely sort the numbers

- use typical divide and conquer
1. split the array in half (left: first element at index `0`, right: rest of the elements from index `1` to index `array.length()-1`)

## 3. Heap Sort: $O(n logn)$

## 4. Bubble Sort: $O(n^2)$
- Perform conditional swap: if the element in current index is greater than the element in next index, swap.

- after the first conditional swap to the end of the array, the last element must be the biggest
- so we start another conditional swap from the beginning, but this time it is from index `0` to index `array.length()-2` (1 less than before to exclude the last element to consider swapping)

## 5. Merge Sort
### Divide and Conquer and Assemble
[left] | [right]
1. sort left
2. sort right
3. merge left and right
4. repeat until reach to the base case

```Java
void mergeSort(int[] xs){
    // pseudo codes
    (first, second) = split(xs)
    first_prime = mergeSort(first)
    second_prime = mergeSort(second)

    result = merge(first_prime, second_prime)
    return result
}
```

