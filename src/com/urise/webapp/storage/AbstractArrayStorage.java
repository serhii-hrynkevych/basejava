package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index < 0) {
            addResume(r, index);
            size++;
            System.out.println("Save Resume UUID: " + uuid + " - OK");
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        } else {
            throw new ExistStorageException(uuid);
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
            throw new NotExistStorageException(uuid);
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
        throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
            System.out.println("Delete Resume UUID: " + uuid + " - OK");
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void deleteResume(int index);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int findIndex(String uuid);
}
