package ru.job4j.question;

import java.util.Arrays;

public class SortArray {

    public static int[] sort(int[] array) {
        int[] temp;
        int[] inArray = array;
        int[] outArray = new int[array.length];
        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                merge(inArray, i, inArray, i + size, outArray, i, size);
            }
            temp = inArray;
            inArray = outArray;
            outArray = temp;
            size = size * 2;

        }
        return inArray;
    }

    private static void merge(int[] arr1, int arr1Start, int[] arr2, int arr2Start,
                              int[] outArray, int outArrayStart, int size) {
        int index1 = arr1Start;
        int index2 = arr2Start;
        int arr1End = Math.min(arr1Start + size, arr1.length);
        int arr2End = Math.min(arr2Start + size, arr2.length);
        int count = arr1End - arr1Start + arr2End - arr2Start;
        for (int i = outArrayStart; i < outArrayStart + count; i++) {
            if (index1 < arr1End && (index2 >= arr2End || arr1[index1] < arr2[index2])) {
                outArray[i] = arr1[index1];
                index1++;
            } else {
                outArray[i] = arr2[index2];
                index2++;
            }
        }
    }

    public static void main(String[] args) {
        int[] number = new int[]{2, 3, -1, 0, 6, 1};
        for (int i = 0; i < number.length; i++) {
            number[i] = (int) Math.pow(number[i], 2);
        }
        System.out.println(Arrays.toString(sort(number)));
    }
}