import java.util.Date;

class TaskScheduler {
    private class Node {
        int taskId;
        String taskName;
        int priority;
        Date dueDate;
        Node next;

        Node(int taskId, String taskName, int priority, Date dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
            this.next = null;
        }
    }

    private Node head = null;
    private Node tail = null;
    private Node currentTask = null;

    public void addTaskAtEnd(int taskId, String taskName, int priority, Date dueDate) {
        Node newNode = new Node(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    public void addTaskAtBeginning(int taskId, String taskName, int priority, Date dueDate) {
        Node newNode = new Node(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
    }

    public void addTaskAtPosition(int taskId, String taskName, int priority, Date dueDate, int position) {
        if (position <= 1) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Node newNode = new Node(taskId, taskName, priority, dueDate);
        Node temp = head;
        for (int i = 1; i < position - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        if (temp == tail) {
            tail = newNode;
        }
    }

    public void removeTaskById(int taskId) {
        if (head == null) return;
        if (head.taskId == taskId) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            return;
        }
        Node temp = head;
        do {
            if (temp.next.taskId == taskId) {
                temp.next = temp.next.next;
                if (temp.next == head) {
                    tail = temp;
                }
                return;
            }
            temp = temp.next;
        } while (temp != head);
    }

    public void viewCurrentTask() {
        if (currentTask == null) {
            currentTask = head;
        }
        if (currentTask != null) {
            System.out.println("Current Task: " + currentTask.taskName + ", Priority: " + currentTask.priority);
            currentTask = currentTask.next;
        }
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Node temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Node temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Task Found - ID: " + temp.taskId + ", Name: " + temp.taskName);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No task with priority " + priority + " found.");
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addTaskAtEnd(1, "Task A", 3, new Date());
        scheduler.addTaskAtEnd(2, "Task B", 2, new Date());
        scheduler.addTaskAtBeginning(3, "Task C", 1, new Date());
        scheduler.displayAllTasks();
        scheduler.viewCurrentTask();
        scheduler.searchByPriority(2);
        scheduler.removeTaskById(2);
        scheduler.displayAllTasks();
    }
}

//SampleOutput
//Task ID: 3, Name: Task C, Priority: 1
//Task ID: 1, Name: Task A, Priority: 3
//Task ID: 2, Name: Task B, Priority: 2
//Current Task: Task C, Priority: 1
//Task Found - ID: 2, Name: Task B
//Task ID: 3, Name: Task C, Priority: 1
//Task ID: 1, Name: Task A, Priority: 3
