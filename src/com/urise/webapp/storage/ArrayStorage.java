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
            Arrays.fill(storage, 0, size - 1, null);
            size = 0;
        } else {
            System.out.println("Storage is already empty");
        }
    }

    public void update(Resume r) {
        int i = checkResume(r.getUuid());
        if (i != -1) {
            get(r.getUuid()).setUuid(r.getUuid() + "update");
            System.out.println("Update Resume UUID: " + r.getUuid() + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + r.getUuid() + " - not found");
        }

    }

    public void save(Resume r) {
        int i = checkResume(r.getUuid());
        if (size == storage.length) {
            System.out.println("ERROR - storage is full");
        } else if (i == -1) {
            storage[size] = r;
            size++;
            System.out.println("Save Resume UUID: " + r.getUuid() + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + r.getUuid() + " - already have resume");
        }
    }

    public Resume get(String uuid) {
        int i = checkResume(uuid);
        if (i != -1) {
            return storage[i];
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        }
        return null;
    }

    public void delete(String uuid) {
        int i = checkResume(uuid);
        if (i != -1) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Delete Resume UUID: " + uuid + " - OK");
        } else {
            System.out.println("ERROR - Resume UUID: " + uuid + " - not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[size];
        if (size > 0) {
            result = Arrays.copyOfRange(storage, 0, size - 1);
        }
        return result;
    }

    public int size() {
        return size;
    }

    private int checkResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
