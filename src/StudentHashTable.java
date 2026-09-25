/**
 * StudentHashTable.java
 * A custom hash table (separate chaining) keyed on Student ID for
 * efficient O(1) average-case search. Requirement #6.
 */
public class StudentHashTable {

    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node[] buckets;
    private int capacity;
    private int size;

    public StudentHashTable() {
        this(16); // default number of buckets
    }

    public StudentHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new Node[capacity];
    }

    /** Simple hash function based on the character codes of the Student ID. */
    private int hash(String studentId) {
        int hash = 0;
        for (char c : studentId.toCharArray()) {
            hash = (hash * 31 + c) % capacity;
        }
        return Math.abs(hash);
    }

    /** Inserts a student into the hash table (chained on collision). */
    public void insert(Student student) {
        int index = hash(student.getStudentId());
        Node newNode = new Node(student);
        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            Node current = buckets[index];
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
    }

    /** Searches for a student by ID. Returns null if not found. */
    public Student search(String studentId) {
        int index = hash(studentId);
        Node current = buckets[index];
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Removes a student by ID from the hash table. */
    public boolean delete(String studentId) {
        int index = hash(studentId);
        Node current = buckets[index];
        Node previous = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size() { return size; }
}
