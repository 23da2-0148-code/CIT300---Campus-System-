public class ActionStack {

    private String[] actions;
    private int top;
    private int capacity;

    public ActionStack() {
        capacity = 50;
        actions = new String[capacity];
        top = -1;
    }

    public void push(String action) {
        if (top == capacity - 1) {
            resize();
        }
        top++;
        actions[top] = action;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String action = actions[top];
        top--;
        return action;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return actions[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }

        System.out.println("--- Recent Actions (most recent first) ---");
        int count = 1;
        for (int i = top; i >= 0; i--) {
            System.out.println(count + ". " + actions[i]);
            count++;
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        String[] newArray = new String[newCapacity];
        for (int i = 0; i < capacity; i++) {
            newArray[i] = actions[i];
        }
        actions = newArray;
        capacity = newCapacity;
    }
}
