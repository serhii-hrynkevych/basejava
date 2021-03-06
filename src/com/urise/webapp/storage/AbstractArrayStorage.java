package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
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
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        addResume(r, index);
        size++;
        System.out.println("Save Resume UUID: " + uuid + " - OK");
    }

    protected abstract void addResume(Resume r, int index);

    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        storage[index] = r;
        System.out.println("Update Resume UUID: " + uuid + " - OK");
    }

    public int size() {
        return size;
    }


    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index);
        storage[size - 1] = null;
        size--;
        System.out.println("Delete Resume UUID: " + uuid + " - OK");
    }

    protected abstract void deleteResume(int index);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }


}
