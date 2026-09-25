/**
 * StudentBST.java
 * A Binary Search Tree keyed on Student ID, used to organize and search
 * student records in sorted order. Requirement #5.
 */
public class StudentBST {

    private static class TreeNode {
        Student data;
        TreeNode left, right;
        TreeNode(Student data) { this.data = data; }
    }

    private TreeNode root;

    /** Inserts a student into the tree, keyed by studentId. */
    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) return new TreeNode(student);
        int cmp = student.getStudentId().compareTo(node.data.getStudentId());
        if (cmp < 0) {
            node.left = insertRec(node.left, student);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, student);
        }
        // if cmp == 0 (duplicate), ignore - linked list already blocks duplicates
        return node;
    }

    /** Searches for a student by ID. Returns null if not found. */
    public Student search(String studentId) {
        TreeNode current = root;
        while (current != null) {
            int cmp = studentId.compareTo(current.data.getStudentId());
            if (cmp == 0) return current.data;
            current = (cmp < 0) ? current.left : current.right;
        }
        return null;
    }

    /** Deletes a student by ID from the tree. */
    public void delete(String studentId) {
        root = deleteRec(root, studentId);
    }

    private TreeNode deleteRec(TreeNode node, String studentId) {
        if (node == null) return null;
        int cmp = studentId.compareTo(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            // node found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // two children: replace with in-order successor (smallest in right subtree)
            TreeNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }
        return node;
    }

    /** Displays all students in ascending Student ID order (in-order traversal). */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in the tree.");
            return;
        }
        System.out.println("--- Students sorted by ID (BST in-order) ---");
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }
}
