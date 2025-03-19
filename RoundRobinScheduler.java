class RoundRobinScheduler {
    private class ProcessNode {
        int processId;
        int burstTime;
        int priority;
        ProcessNode next;

        ProcessNode(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.priority = priority;
            this.next = null;
        }
    }

    private ProcessNode head = null;
    private ProcessNode tail = null;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newNode = new ProcessNode(processId, burstTime, priority);
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

    public void removeProcess(int processId) {
        if (head == null) return;
        if (head.processId == processId) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            return;
        }
        ProcessNode temp = head;
        do {
            if (temp.next.processId == processId) {
                temp.next = temp.next.next;
                if (temp.next == head) {
                    tail = temp;
                }
                return;
            }
            temp = temp.next;
        } while (temp != head);
    }

    public void simulateRoundRobin() {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }
        int currentTime = 0;
        ProcessNode temp = head;
        do {
            if (temp.burstTime > 0) {
                int execTime = Math.min(temp.burstTime, timeQuantum);
                System.out.println("Executing Process ID: " + temp.processId + " for " + execTime + " units");
                temp.burstTime -= execTime;
                currentTime += execTime;
                if (temp.burstTime == 0) {
                    removeProcess(temp.processId);
                }
            }
            temp = temp.next;
        } while (head != null && temp != head);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }
        ProcessNode temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(4);
        scheduler.addProcess(1, 8, 2);
        scheduler.addProcess(2, 4, 1);
        scheduler.addProcess(3, 9, 3);
        scheduler.addProcess(4, 5, 2);

        System.out.println("Initial Process Queue:");
        scheduler.displayProcesses();

        System.out.println("Starting Round Robin Scheduling:");
        scheduler.simulateRoundRobin();
    }
}

//SampleOutput
//Initial Process Queue:
//Process ID: 1, Burst Time: 8, Priority: 2
//Process ID: 2, Burst Time: 4, Priority: 1
//Process ID: 3, Burst Time: 9, Priority: 3
//Process ID: 4, Burst Time: 5, Priority: 2
//Starting Round Robin Scheduling:
//Executing Process ID: 1 for 4 units
//Executing Process ID: 2 for 4 units
//Executing Process ID: 3 for 4 units
//Executing Process ID: 4 for 4 units
