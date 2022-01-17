package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);

//        storage = new Resume[10000];

//        for (int i = 0; i < size; i++) {
//            storage[i] = null;
//        }
        size = 0;
    }

    public void update(Resume r) {
        if (checkResume(r.getUuid())) {
            get(r.getUuid()).setUuid(r.getUuid() + "update");
            System.out.println("Update - OK");
        } else {
            System.out.println("ERROR");
        }

    }

    public void save(Resume r) {
        if (size == 10001) {
            System.out.println("ERROR - storage is full");
        } else if (!checkResume(r.getUuid())) {
            int newPositionInStorage = size;
            storage[newPositionInStorage] = r;
            size++;
            System.out.println("Save - OK");
        } else {
            System.out.println("ERROR - already have resume");
        }

    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    System.out.println("Delete - OK");
                }
            }
        } else {
            System.out.println("ERROR - resume not found");
        }

//        int deletePosition = 0;
//        for (int i = deletePosition; i < size; i++) {
//            if (i == size() - 1) {
//                storage[i] = null;
//                size--;
//            } else {
//                storage[i] = storage[i + 1];
//            }
//        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        if (size > 0) {
            return Arrays.copyOfRange(storage, 0, size - 1);
        }
//        Resume[] allResumes = new Resume[size()];
//        if (size() >= 0) System.arraycopy(storage, 0, allResumes, 0, size);
        return new Resume[0];
    }

    public int size() {
        return size;
    }

    private boolean checkResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
