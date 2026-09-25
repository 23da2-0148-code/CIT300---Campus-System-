import java.util.Scanner;

/**
 * Main.java
 * Menu-driven console interface for the University Student Record and
 * Campus Route Management System. Wires the linked list, stack, queue,
 * BST, hash table, and graph together so every student-record action
 * stays consistent across structures.
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static StudentLinkedList studentList = new StudentLinkedList();
    private static ActionStack history = new ActionStack();
    private static ServiceQueue serviceQueue = new ServiceQueue();
    private static StudentBST bst = new StudentBST();
    private static StudentHashTable hashTable = new StudentHashTable();
    private static CampusGraph campus = new CampusGraph();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> studentList.displayAll();
                case 5 -> addServiceRequest();
                case 6 -> processNextRequest();
                case 7 -> history.displayHistory();
                case 8 -> bst.displayInOrder();
                case 9 -> searchByHashing();
                case 10 -> addLocation();
                case 11 -> removeLocation();
                case 12 -> addConnection();
                case 13 -> removeConnection();
                case 14 -> campus.displayConnections();
                case 15 -> traverseCampus();
                case 16 -> { running = false; System.out.println("Exiting. Goodbye!"); }
                default -> System.out.println("Invalid choice. Please select 1-16.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("========== University Student Record & Campus Route System ==========");
        System.out.println(" 1. Add Student Record");
        System.out.println(" 2. Update Student Record");
        System.out.println(" 3. Delete Student Record");
        System.out.println(" 4. Display All Records (Linked List)");
        System.out.println(" 5. Add Service Request to Queue");
        System.out.println(" 6. Process Next Service Request");
        System.out.println(" 7. Display Recent Actions (Stack)");
        System.out.println(" 8. Display Students (BST)");
        System.out.println(" 9. Search Student (Hashing)");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations (BFS/DFS)");
        System.out.println("16. Exit");
    }

    // ---------------- Student record operations ----------------

    private static void addStudent() {
        String id = readNonEmpty("Enter Student ID: ");
        if (studentList.findById(id) != null) {
            System.out.println("Error: A student with ID " + id + " already exists.");
            return;
        }
        String name = readNonEmpty("Enter Name: ");
        String programme = readNonEmpty("Enter Programme: ");
        double marks = readMarks("Enter Marks (0-100): ");

        Student student = new Student(id, name, programme, marks);
        studentList.add(student);
        bst.insert(student);
        hashTable.insert(student);
        history.push("Added: " + id + " - " + name);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        String id = readNonEmpty("Enter Student ID to update: ");
        Student existing = studentList.findById(id);
        if (existing == null) {
            System.out.println("Error: No student found with ID " + id);
            return;
        }
        String name = readNonEmpty("Enter new Name: ");
        String programme = readNonEmpty("Enter new Programme: ");
        double marks = readMarks("Enter new Marks (0-100): ");

        studentList.update(id, name, programme, marks);
        // BST/hash table entries reference the same Student object, so their
        // fields are already updated in place - no re-insert needed.
        history.push("Updated: " + id + " - " + name);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        String id = readNonEmpty("Enter Student ID to delete: ");
        Student removed = studentList.delete(id);
        if (removed == null) {
            System.out.println("Error: No student found with ID " + id);
            return;
        }
        bst.delete(id);
        hashTable.delete(id);
        history.push("Deleted: " + id + " - " + removed.getName());
        System.out.println("Student deleted successfully.");
    }

    private static void searchByHashing() {
        String id = readNonEmpty("Enter Student ID to search: ");
        Student result = hashTable.search(id);
        if (result == null) {
            System.out.println("No student found with ID " + id);
        } else {
            System.out.println("Found: " + result);
        }
    }

    // ---------------- Queue operations ----------------

    private static void addServiceRequest() {
        String id = readNonEmpty("Enter Student ID for the request: ");
        String type = readNonEmpty("Enter request type (e.g., Transcript, ID Card): ");
        serviceQueue.enqueue(id + " - " + type);
        System.out.println("Service request added to queue.");
    }

    private static void processNextRequest() {
        String next = serviceQueue.dequeue();
        if (next == null) {
            System.out.println("No pending service requests.");
        } else {
            System.out.println("Processing request: " + next);
            history.push("Processed request: " + next);
        }
    }

    // ---------------- Graph operations ----------------

    private static void addLocation() {
        String location = readNonEmpty("Enter new campus location name: ");
        if (campus.addLocation(location)) {
            System.out.println("Location added.");
        } else {
            System.out.println("Error: Location already exists.");
        }
    }

    private static void removeLocation() {
        String location = readNonEmpty("Enter campus location to remove: ");
        if (campus.removeLocation(location)) {
            System.out.println("Location removed.");
        } else {
            System.out.println("Error: Location not found.");
        }
    }

    private static void addConnection() {
        String from = readNonEmpty("Enter first location: ");
        String to = readNonEmpty("Enter second location: ");
        if (campus.addConnection(from, to)) {
            System.out.println("Connection added.");
        } else {
            System.out.println("Error: One or both locations do not exist.");
        }
    }

    private static void removeConnection() {
        String from = readNonEmpty("Enter first location: ");
        String to = readNonEmpty("Enter second location: ");
        if (campus.removeConnection(from, to)) {
            System.out.println("Connection removed.");
        } else {
            System.out.println("Error: One or both locations do not exist.");
        }
    }

    private static void traverseCampus() {
        String start = readNonEmpty("Enter starting location: ");
        String type = readNonEmpty("Traverse using BFS or DFS? ");
        if (type.equalsIgnoreCase("BFS")) {
            campus.bfs(start);
        } else if (type.equalsIgnoreCase("DFS")) {
            campus.dfs(start);
        } else {
            System.out.println("Invalid traversal type. Choose BFS or DFS.");
        }
    }

    // ---------------- Input validation helpers ----------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks. Must be between 0 and 100.");
                    continue;
                }
                return marks;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
}
