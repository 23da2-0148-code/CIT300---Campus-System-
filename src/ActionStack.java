/**
 * ActionStack.java
 * A custom array-based stack that records recent actions performed on
 * student records (add/update/delete). Requirement #3.
 */
public class ActionStack {

    private String[] actions;
    private int top;       // index of the last pushed action
    private int capacity;

    public ActionStack() {
        this(50); // default capacity
    }

    public ActionStack(int capacity) {
        this.capacity = capacity;
        this.actions = new String[capacity];
        this.top = -1;
    }

    /** Pushes a new action description, e.g. "Added: 1001 - John Doe". */
    public void push(String action) {
        if (top == capacity - 1) {
            resize();
        }
        actions[++top] = action;
    }

    /** Removes and returns the most recent action, or null if empty. */
    public String pop() {
        if (isEmpty()) return null;
        return actions[top--];
    }

    /** Looks at the most recent action without removing it. */
    public String peek() {
        if (isEmpty()) return null;
        return actions[top];
    }

    public boolean isEmpty() { return top == -1; }

    /** Displays the recent action history, most recent first. */
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }
        System.out.println("--- Recent Actions (most recent first) ---");
        for (int i = top; i >= 0; i--) {
            System.out.println((top - i + 1) + ". " + actions[i]);
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        String[] newArray = new String[newCapacity];
        System.arraycopy(actions, 0, newArray, 0, capacity);
        actions = newArray;
        capacity = newCapacity;
    }
}
