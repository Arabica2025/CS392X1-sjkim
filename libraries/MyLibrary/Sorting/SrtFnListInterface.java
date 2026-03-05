package MyLibrary.Sorting;

import MyLibrary.FnList.*;
interface SrtFnListInterface<T> {

    /* 2. Sorting with FnList as argument */

    FnList<T> selectionSort(FnList<T> xs);
    FnList<T> insertionSort(FnList<T> xs);
    FnList<T> bubbleSort(FnList<T> xs);
    FnList<T> quickSort(FnList<T> xs);
    FnList<T> mergeSort(FnList<T> xs);
}
