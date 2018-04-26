package sorting_and_searching;

import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[]{5, 2 - 1, 8, 6, 9, 11, 2, 1};
        mergeSort(arr, 0, arr.length);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    public static void mergeSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int middleIndex = (startIndex + endIndex) / 2;

        mergeSort(arr, startIndex, middleIndex); //Left part
        mergeSort(arr, middleIndex+1, endIndex); //Right part

        merge(arr, startIndex, middleIndex, endIndex);
    }

    private static void merge(int[] arr, int startIndex, int middleIndex, int endIndex) {
        //Already sorted
        if (middleIndex < 0
                || middleIndex + 1 >= arr.length
                || arr[middleIndex] <= arr[middleIndex + 1]) {
            return;
        }
        int[] helpArr = new int[arr.length];
        int leftIndex = startIndex;
        int rightIndex = middleIndex + 1;

        for (int i = startIndex; i < endIndex; i++) {
            helpArr[i] = arr[i];
        }

        for (int i = startIndex; i < endIndex; i++) {
            if (leftIndex > middleIndex) {
                arr[i] = helpArr[rightIndex++];
            } else if (rightIndex > endIndex) {
                arr[i] = helpArr[leftIndex++];
            } else if (helpArr[leftIndex] <= helpArr[rightIndex]) {
                arr[i] = helpArr[leftIndex++];
            } else {
                arr[i] = helpArr[rightIndex++]; 
            }

        }
    }
}
