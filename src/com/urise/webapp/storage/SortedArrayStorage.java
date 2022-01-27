package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(Resume r, int index) {
        int indexPosition = - index - 1;
        if (indexPosition != size) {
            for (int i = size; i > indexPosition; i--) {
                storage[i] = storage[i - 1];
            }
            storage[indexPosition] = r;
        } else {
            storage[size] = r;
        }
    }

    @Override
    protected void deleteResume(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
