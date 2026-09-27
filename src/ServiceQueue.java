public class ServiceQueue {

    private class Node {
        String request;
        Node next;

        Node(String request) {
            this.request = request;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public void enqueue(String request) {
        Node newNode = new Node(request);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }

        String data = front.request;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("--- Pending Service Requests ---");
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.request);
            count++;
            current = current.next;
        }
    }
}
