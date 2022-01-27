package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        if (size != 0) {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        } else {
            System.out.println("Storage is already empty");
        }
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR - storage is full");
        } else if (index < 0) {
            addResume(r, index);
            size++;
            System.out.println("Save Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - already have resume");
        }
    }

    protected abstract void addResume(Resume r, int index);

    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index > 0) {
            storage[index] = r;
            System.out.println("Update Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        }
    }

    public int size() {
        return size;
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
            deleteResume(index);
            storage[size - 1] = null;
            size--;
            System.out.println("Delete Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        }
    }

    protected abstract void deleteResume(int index);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int findIndex(String uuid);
}
