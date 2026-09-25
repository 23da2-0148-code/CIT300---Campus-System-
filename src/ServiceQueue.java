/**
 * ServiceQueue.java
 * A custom linked-node FIFO queue that manages student service requests
 * in order of arrival. Requirement #4.
 */
public class ServiceQueue {

    private static class Node {
        String request;
        Node next;
        Node(String request) { this.request = request; }
    }

    private Node front;
    private Node rear;
    private int size;

    /** Adds a new service request to the back of the queue. */
    public void enqueue(String request) {
        Node newNode = new Node(request);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Removes and returns the request that arrived first. Returns null if empty. */
    public String dequeue() {
        if (isEmpty()) return null;
        String data = front.request;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public boolean isEmpty() { return front == null; }
    public int size() { return size; }

    /** Displays all pending requests in order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("--- Pending Service Requests ---");
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.request);
            current = current.next;
        }
    }
}
