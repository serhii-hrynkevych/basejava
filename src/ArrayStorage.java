/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int newResume = size();
        storage[newResume] = r;
    }

    Resume get(String uuid) {
        Resume resumeGet = new Resume();
        resumeGet.uuid = uuid;
        for (Resume resume : storage) {
            if (resume.toString().equals(resumeGet.toString())) {
                return resume;
            } else {
                break;
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume resumeDel = new Resume();
        resumeDel.uuid = uuid;
        int deletePosition = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(resumeDel.uuid)) {
                deletePosition = i;
            }
        }

        for (int i = deletePosition; i < size(); i++) {
            if (i == size() - 1) {
                storage[i] = null;
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
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }
}
