public class HashTable {
    Node[] hashTable;
    int size;
    HashTable(int hashTableSize) {
        hashTable = new Node[hashTableSize];
        size = 0;
    }
    public boolean isEmpty() {
        
        return size == 0;
        
    }
    public void clear() {
        int len = hashTable.length;
        hashTable = new Node[len];
        size = 0;
    }
    public int getSize() {
        
        return size;
        
    }
    public void add(int value) {
        size++;
        int position = hash(value);
        Node node = new Node(value);

        Node start = hashTable[position];

        if (hashTable[position] == null)
            hashTable[position] = node;
        else {
            node.next = start;
            start.prev = node;
            hashTable[position] = node;
        }
    }
    public void remove(int value) {
        try {
            int position = hash(value);
            Node start = hashTable[position];
            Node end = start;
            if (start.data == value) {
                size--;
                if (start.next == null) {
                    hashTable[position] = null;
                    return;
                }
                start = start.next;
                start.prev = null;
                hashTable[position] = start;
                return;
            }
            while (end.next != null && end.next.data != value) {
                end = end.next;
            }
            if (end.next == null) {
                System.out.println("\nElement not found\n");
                return;
            }
            size--;
            if (end.next.next == null) {
                end.next = null;
                return;
            }

            end.next.next.prev = end;
            end.next = end.next.next;

            hashTable[position] = start;
        }
        catch (Exception e) {
            System.out.println("\nElement not found\n");
        }
    }


    private int hash(Integer x) {
        int hashValue = x.hashCode();
        hashValue %= hashTable.length;
        if (hashValue < 0)
            hashValue += hashTable.length;

        return hashValue;
    }
    public void print() {
        System.out.println();
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print("Hash: " + i + " " + "Keys: " );
            Node start = hashTable[i];
            while (start != null) {
                System.out.print(start.data + " ");
                start = start.next;
            }
            System.out.println();
        }
    }
}
