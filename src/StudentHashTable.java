public class StudentHashTable {

    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node[] buckets;
    private int capacity;
    private int size;

    public StudentHashTable() {
        capacity = 16;
        buckets = new Node[capacity];
    }

    private int hash(String studentId) {
        int hash = 0;
        for (int i = 0; i < studentId.length(); i++) {
            hash = hash * 31 + studentId.charAt(i);
        }
        hash = hash % capacity;
        if (hash < 0) {
            hash = hash * -1;
        }
        return hash;
    }

    public void insert(Student student) {
        int index = hash(student.getStudentId());
        Node newNode = new Node(student);

        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            Node current = buckets[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
    }

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

    public int size() {
        return size;
    }
}
