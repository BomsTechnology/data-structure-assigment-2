package util;

public class BinarySearch {

    public static <T extends Comparable<T>> int search(T[] arr, T key) {
        return binarySearch(arr, key, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> int binarySearch(T[] arr, T key, int left, int right) {

        if (left > right) {
            return -1; // not found
        }

        int mid = (left + right) / 2;
        int comparison = arr[mid].compareTo(key);

        if (comparison == 0) {
            
            return findFirst(arr, key, left, mid);
        }
        else if (comparison > 0) {
            return binarySearch(arr, key, left, mid - 1);
        }
        else {
            return binarySearch(arr, key, mid + 1, right);
        }
    }

    private static <T extends Comparable<T>> int findFirst(T[] arr, T key, int left, int index) {

        while (index > left && arr[index - 1].compareTo(key) == 0) {
            index--;
        }

        return index;
    }
}
