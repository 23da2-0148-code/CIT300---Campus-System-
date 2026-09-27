public class StudentBST {

    private class TreeNode {
        Student data;
        TreeNode left;
        TreeNode right;

        TreeNode(Student data) {
            this.data = data;
        }
    }

    private TreeNode root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) {
            return new TreeNode(student);
        }

        int cmp = student.getStudentId().compareTo(node.data.getStudentId());

        if (cmp < 0) {
            node.left = insertRec(node.left, student);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, student);
        }

        return node;
    }

    public Student search(String studentId) {
        TreeNode current = root;

        while (current != null) {
            int cmp = studentId.compareTo(current.data.getStudentId());
            if (cmp == 0) {
                return current.data;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public void delete(String studentId) {
        root = deleteRec(root, studentId);
    }

    private TreeNode deleteRec(TreeNode node, String studentId) {
        if (node == null) {
            return null;
        }

        int cmp = studentId.compareTo(node.data.getStudentId());

        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            TreeNode successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }

        return node;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in the tree.");
            return;
        }
        System.out.println("--- Students sorted by ID ---");
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }
}
