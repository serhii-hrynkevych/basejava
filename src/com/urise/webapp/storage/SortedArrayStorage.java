package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(Resume r, int index) {
        int indexPosition = -index - 1;
        System.arraycopy(storage, indexPosition, storage, indexPosition + 1, size - indexPosition);
        storage[indexPosition] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int indexPosition = size - 1 - index;
        if (indexPosition > 0) {
            System.arraycopy(storage, index + 1, storage, index, indexPosition);
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
