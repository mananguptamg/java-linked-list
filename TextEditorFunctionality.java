// Node class to store each state of the text
class TextState {
    String text;
    TextState next, prev;

    public TextState(String text) {
        this.text = text;
        this.next = this.prev = null;
    }
}


class TextEditorFunctionality {
    private TextState head, tail, current;
    private int historyLimit = 10;
    private int count = 0;

    public TextEditorFunctionality() {
        head = tail = current = null;
    }

    // Add a new text state (when user types or performs an action)
    public void addTextState(String newText) {
        TextState newState = new TextState(newText);

        // If the list is empty, set as the first state
        if (head == null) {
            head = tail = current = newState;
        } else {
            // Remove forward history
            current.next = null;
            tail = current;

            // Add the new state at the end
            tail.next = newState;
            newState.prev = tail;
            tail = newState;
            current = newState;

            // If history exceeds the limit, remove the oldest state
            if (count >= historyLimit) {
                head = head.next;
                head.prev = null;
            } else {
                count++;
            }
        }
    }

    // Undo: Move to the previous state
    public void undo() {
        if (current == null || current.prev == null) {
            System.out.println("No more undo actions available.");
            return;
        }
        current = current.prev;
        System.out.println("Undo performed. Current text: " + current.text);
    }

    // Redo: Move to the next state
    public void redo() {
        if (current == null || current.next == null) {
            System.out.println("No more redo actions available.");
            return;
        }
        current = current.next;
        System.out.println("Redo performed. Current text: " + current.text);
    }

    // Display current text state
    public void displayCurrentText() {
        if (current == null) {
            System.out.println("No text available.");
        } else {
            System.out.println("Current Text: " + current.text);
        }
    }

    public void displayAllStates() {
        TextState temp = head;
        if (temp == null) {
            System.out.println("No history available.");
            return;
        }
        System.out.println("Text History:");
        while (temp != null) {
            System.out.println("- " + temp.text);
            temp = temp.next;
        }
    }

    // Main method to test the Undo/Redo functionality
    public static void main(String[] args) {
        TextEditorFunctionality editor = new TextEditorFunctionality();

        // Adding text states
        editor.addTextState("Hello");
        editor.addTextState("Hello World");
        editor.addTextState("Hello World! How are you?");
        editor.addTextState("Hello World! How are you doing today?");


        editor.displayCurrentText();


        editor.undo();
        editor.undo();

        editor.redo();

        System.out.println("After Undo and Redo");
        editor.displayCurrentText();

        // Display all states in the history
        editor.displayAllStates();
    }
}

//SampleOutput
//Current Text: Hello World! How are you doing today?
//Undo performed. Current text: Hello World! How are you?
//Undo performed. Current text: Hello World
//Redo performed. Current text: Hello World! How are you?
//After Undo and Redo
//Current Text: Hello World! How are you?
//Text History:
//- Hello
//- Hello World
//- Hello World! How are you?
//- Hello World! How are you doing today?