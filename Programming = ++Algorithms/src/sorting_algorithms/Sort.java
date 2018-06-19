package sorting_algorithms;

import java.util.List;

public class Sort {

    public static void insertionSort(List<Integer> list) {

        for (int i = 1; i < list.size(); i++) {
            int element = list.get(i);
            int index = -1;
            for (int j = 0; j < i; j++) {
                if (list.get(i) > element) {
                    index = j;
                    break;
                }
            }
            if (index > -1) {
                insertAndRemoveElement(index, i, list);
            }
        }


    }


    public static void selectionSort(List<Integer> list) {

        for (int i = 0; i < list.size() - 1; i++) {
            int smallest = list.get(i);
            int index = 0;
            for (int j = i + 1; j < list.size(); j++) {

                if (smallest > list.get(j)) {
                    smallest = list.get(j);
                    index = j;
                }
            }

            if (smallest != list.get(i)) {
                swap(i, index, list);
            }
        }

    }

    public void bubbleSort(List<Integer> list) {
        int length = list.size() - 1;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int j = 0; j < length; j++) {

                if (list.get(j) > list.get(j + 1)) {
                    swap(j, j + 1, list);
                    swapped = true;
                }
            }
            length--;
        }
    }

    private static void swap(int j, int i, List<Integer> list) {
        int temp = list.get(j);
        list.set(j, list.get(i));
        list.set(i, temp);
    }

    private static void insertAndRemoveElement(int index, int i, List<Integer> list) {
        int element = list.get(i);
        list.remove(i);
        list.add(index, element);
    }


}
