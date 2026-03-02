package com.employeepayroll.sorting;

public class BinarySearch {

    public static <T extends Comparable<T>> int search(T[] array, T target, int low, int high) {
        // Base case — not found
        if (low > high) return -1;

        int mid = low + (high - low) / 2;
        int cmp = array[mid].compareTo(target);

        if (cmp == 0) {
            // Found a match — but check if there's an earlier occurrence to the left
            int firstOccurrence = search(array, target, low, mid - 1);
            return (firstOccurrence != -1) ? firstOccurrence : mid;

        } else if (cmp > 0) {
            // Target is smaller — search left half
            return search(array, target, low, mid - 1);

        } else {
            // Target is larger — search right half
            return search(array, target, mid + 1, high);
        }
    }
}