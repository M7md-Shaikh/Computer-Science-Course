package application;

public class Hash {
    private int tableSize;
    private HashEntry[] table;
    private int currentSize;

    public Hash(int size) {
        table = new HashEntry[size];
        tableSize = size;
        currentSize = 0;
    }

    public void makeEmpty() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        currentSize = 0;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getTableSize() {
        return tableSize;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Country get(String key) {
        int i = 1;
        int location = getHash(key);
        while (table[location] != null && table[location].getStatus() != 0) {
            if (table[location].getKey().equals(key)) {
                return table[location].getValue();
            }
            location = (location + i * i) % tableSize;
            i++;
        }
        return null;
    }

    public void remove(String key) {
        if (!contains(key)) return;

        int i = 1;
        int hash = getHash(key);
        while (table[hash] != null && table[hash].getStatus() != 0 && !table[hash].getKey().equals(key)) {
            hash = (hash + i * i) % tableSize;
            i++;
        }
        currentSize--;
        table[hash].setDeleteStatus(); // Mark entry as deleted
    }

    public void insert(String key, Country value) {
        if (currentSize >= tableSize / 2) {
            rehash();
        }

        int hash = getHash(key);
        int i = 1;
        while (table[hash] != null && table[hash].getStatus() != 0 && table[hash].getStatus() != 2) {
            hash = (hash + i * i) % tableSize;
            i++;
        }
        currentSize++;
        table[hash] = new HashEntry(key, value, (byte) 1); // Mark as active
    }

    public void putIfAbsent(String key, Country value) {
        if (get(key) == null) {
            insert(key, value);
        }
    }

    private void rehash() {
        Hash newTable = new Hash(nextPrime(2 * tableSize));
        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null && table[i].getStatus() == 1) {
                newTable.insert(table[i].getKey(), table[i].getValue());
            }
        }
        table = newTable.table;
        tableSize = newTable.tableSize;
    }

    private int getHash(String key) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = (31 * hashVal + key.charAt(i)) % tableSize;
        }
        return hashVal < 0 ? hashVal + tableSize : hashVal;
    }

    private static int nextPrime(int n) {
        if (n % 2 == 0) n++;
        while (!isPrime(n)) n += 2;
        return n;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public void printHashTable() {
        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null && table[i].getStatus() == 1) {
                System.out.println(i + " , " + table[i].getKey() + " , " + table[i].getValue().getFullName());
            }
        }
    }
}
