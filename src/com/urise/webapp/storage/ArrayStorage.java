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
        if (size != 0) {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        } else {
            System.out.println("Storage is already empty");
        }
    }

    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = r;
            System.out.println("Update Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        }

    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (size == storage.length) {
            System.out.println("ERROR - storage is full");
        } else if (index == -1) {
            storage[size] = r;
            size++;
            System.out.println("Save Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - already have resume");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Delete Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
