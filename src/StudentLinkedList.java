/**
 * StudentLinkedList.java
 * A custom singly linked list that acts as the master store of student
 * records. Requirement #2: "Use a linked list to store and manage student
 * records."
 */
public class StudentLinkedList {

    // Internal node class
    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    /** Adds a new student to the end of the list. Returns false if the ID already exists. */
    public boolean add(Student student) {
        if (findById(student.getStudentId()) != null) {
            return false; // duplicate ID - reject
        }
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
        return true;
    }

    /** Finds a student by ID. Returns null if not found. */
    public Student findById(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Updates an existing student's name/programme/marks. Returns false if not found. */
    public boolean update(String studentId, String name, String programme, double marks) {
        Student s = findById(studentId);
        if (s == null) return false;
        s.setName(name);
        s.setProgramme(programme);
        s.setMarks(marks);
        return true;
    }

    /** Deletes a student by ID. Returns the removed Student, or null if not found. */
    public Student delete(String studentId) {
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    /** Prints every student record in insertion order. */
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.data);
            current = current.next;
        }
    }

    public int size() { return size; }
    public boolean isEmpty() { return head == null; }
}
