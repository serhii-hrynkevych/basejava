/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        int newResume = size();
        storage[newResume] = r;
        size++;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume.uuid.equals(uuid)) {
                return resume;
            } else {
                break;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int deletePosition = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                deletePosition = i;
            }
        }

        for (int i = deletePosition; i < size(); i++) {
            if (i == size() - 1) {
                storage[i] = null;
                size--;
            } else {
                storage[i] = storage[i + 1];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] all = new Resume[size()];
        if (size() >= 0) System.arraycopy(storage, 0, all, 0, size());
        return all;
    }

    int size() {
        return size;
    }
}
