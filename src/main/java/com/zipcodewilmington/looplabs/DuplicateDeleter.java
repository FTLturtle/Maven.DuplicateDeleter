package com.zipcodewilmington.looplabs;

import java.util.Arrays;

/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    private T[] array;

    private int newArrayCurrentIndex;
    private int totalNumOfDuplicatesRemoved;
    private int currentDuplicateCount;

    public DuplicateDeleter(T[] intArray) {
        Arrays.sort(intArray);
        this.array = intArray;
    }

    public T[] removeDuplicates(int maxNumberOfDuplications) {
        T[] newArray = Arrays.copyOf(array, array.length);
        newArrayCurrentIndex = 0;
        totalNumOfDuplicatesRemoved = 0;
        currentDuplicateCount = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(array[i - 1])) {
                currentDuplicateCount++;
            } else {
                skipOrCopyCurrentElements(newArray, i, currentDuplicateCount >= maxNumberOfDuplications);
            }
        }

        skipOrCopyCurrentElements(newArray, array.length, currentDuplicateCount >= maxNumberOfDuplications);

        return Arrays.copyOf(newArray, newArray.length - totalNumOfDuplicatesRemoved);
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        T[] newArray = Arrays.copyOf(array, array.length);
        newArrayCurrentIndex = 0;
        totalNumOfDuplicatesRemoved = 0;
        currentDuplicateCount = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(array[i - 1])) {
                currentDuplicateCount++;
            } else {
                skipOrCopyCurrentElements(newArray, i, currentDuplicateCount == exactNumberOfDuplications);
            }
        }

        // Final check for the final elements of the array
        skipOrCopyCurrentElements(newArray, array.length, currentDuplicateCount == exactNumberOfDuplications);

        return Arrays.copyOf(newArray, newArray.length - totalNumOfDuplicatesRemoved);
    }

    private void skipOrCopyCurrentElements(T[] newArray, int originalArrayCurrentIndex, boolean skipCondition) {
        if (skipCondition) {
            totalNumOfDuplicatesRemoved += currentDuplicateCount;
            currentDuplicateCount = 1;
        } else {
            copyCurrentElementsToNewArray(newArray, originalArrayCurrentIndex);
            currentDuplicateCount = 1;
        }
    }

    private void copyCurrentElementsToNewArray(T[] newArray, int originalArrayCurrentIndex) {
        for (int i = currentDuplicateCount; i > 0; i--) {
            newArray[newArrayCurrentIndex] = array[originalArrayCurrentIndex - i];
            newArrayCurrentIndex++;
        }
    }
    
}
