/**
 * ActionStack.java
 *
 * A custom array-based stack used to keep track of the most recent
 * actions performed on student records (add, update, delete).
 * This satisfies Requirement #3 of the project — the system must
 * be able to show a history of recent changes.
 *
 * The stack grows automatically (doubles in size) if it runs out
 * of space, so it can never overflow.
 */
public class ActionStack {

    private String[] actions;   // holds the action descriptions
    private int top;            // index of the most recently pushed action
    private int capacity;       // current max size of the array

    /** Creates a stack with a default starting capacity of 50. */
    public ActionStack() {
        this(50);
    }

    /** Creates a stack with a custom starting capacity. */
    public ActionStack(int capacity) {
        this.capacity = capacity;
        this.actions = new String[capacity];
        this.top = -1; // -1 means the stack is currently empty
    }

    /**
     * Adds a new action to the top of the stack.
     * Example: "Added: 1001 - John Doe"
     */
    public void push(String action) {
        if (top == capacity - 1) {
            resize(); // grow the array before it overflows
        }
        actions[++top] = action;
    }

    /**
     * Removes and returns the most recent action.
     * Returns null if there are no actions recorded yet.
     */
    public String pop() {
        if (isEmpty()) return null;
        return actions[top--];
    }

    /**
     * Returns the most recent action without removing it from the stack.
     */
    public String peek() {
        if (isEmpty()) return null;
        return actions[top];
    }

    /** Returns true if no actions have been recorded. */
    public boolean isEmpty() {
        return top == -1;
    }

    /** Returns how many actions are currently stored in the stack. */
    public int size() {
        return top + 1;
    }

    /**
     * Prints out the action history, starting with the most
     * recent action first (like a timeline in reverse).
     */
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

    /**
     * Doubles the size of the internal array when it becomes full.
     * This keeps push() from ever running out of room.
     */
    private void resize() {
        int newCapacity = capacity * 2;
        String[] newArray = new String[newCapacity];
        System.arraycopy(actions, 0, newArray, 0, capacity);
        actions = newArray;
        capacity = newCapacity;
    }
}