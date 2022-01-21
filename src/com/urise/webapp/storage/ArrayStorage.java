package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    @Override
    public void clear() {
        if (size != 0) {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        } else {
            System.out.println("Storage is already empty");
        }
    }

    @Override
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

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR - storage is full");
        } else if (index == -1) {
            storage[size] = r;
            size++;
            System.out.println("Save Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - already have resume");
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        return null;
    }

    @Override
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

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
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
