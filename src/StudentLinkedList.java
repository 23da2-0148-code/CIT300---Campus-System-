public class StudentLinkedList {

    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public boolean add(Student student) {
        if (findById(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        return true;
    }

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

    public boolean update(String studentId, String name, String programme, double marks) {
        Student s = findById(studentId);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setProgramme(programme);
        s.setMarks(marks);
        return true;
    }

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

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.data);
            count++;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
