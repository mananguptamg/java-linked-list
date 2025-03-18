// Class to represent a Student
class Student {
    int rollNumber;
    String name;
    int age;
    char grade;
    Student next; // Pointer to next student

    // Constructor to initialize student data
    Student(int rollNumber, String name, int age, char grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

// Class to manage the Student Singly Linked List
class StudentLinkedList {
    Student head; // Head of the list

    // Method to add a student at the beginning
    public void addStudentAtBeginning(int rollNumber, String name, int age, char grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    // Method to add a student at the end
    public void addStudentAtEnd(int rollNumber, String name, int age, char grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);

        if (head == null) {
            head = newStudent;
            return;
        }

        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    // Method to add a student at a specific position
    public void addStudentAtPosition(int rollNumber, String name, int age, char grade, int position) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }

        Student newStudent = new Student(rollNumber, name, age, grade);

        if (position == 1) {
            newStudent.next = head;
            head = newStudent;
            return;
        }

        Student temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }

        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    // Method to delete a student record by roll number
    public void deleteStudent(int rollNumber) {
        if (head == null) {
            System.out.println("No students in the record.");
            return;
        }

        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }

        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != rollNumber) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
            return;
        }

        temp.next = temp.next.next;
    }

    // Method to search for a student by Roll Number
    public void searchStudent(int rollNumber) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                System.out.println("Student Found: " + temp.rollNumber + " | " + temp.name + " | Age: " + temp.age + " | Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to update a student's grade
    public void updateStudentGrade(int rollNumber, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                temp.grade = newGrade;
                System.out.println("Updated Grade for Roll Number " + rollNumber + " to " + newGrade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to display all student records
    public void displayAllStudents() {
        if (head == null) {
            System.out.println("No students in the record.");
            return;
        }

        System.out.println("Student Records:");
        Student temp = head;
        while (temp != null) {
            System.out.println(temp.rollNumber + " | " + temp.name + " | Age: " + temp.age + " | Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

// Main class to run the program
public class StudentRecordManagement {
    public static void main(String[] args) {
        StudentLinkedList studentList = new StudentLinkedList();

        // Adding students
        studentList.addStudentAtEnd(1, "Amit", 11, 'A');
        studentList.addStudentAtEnd(2, "Arjun", 12, 'B');
        studentList.addStudentAtBeginning(3, "Chandan", 11, 'C');
        studentList.addStudentAtPosition(4, "Manan", 11, 'B', 2);

        // Display all students
        studentList.displayAllStudents();

        // Searching for a student
        studentList.searchStudent(102);

        // Updating a student's grade
        studentList.updateStudentGrade(101, 'B');

        // Deleting a student
        studentList.deleteStudent(103);

        // Displaying students after deletion
        studentList.displayAllStudents();
    }
}


//SampleOutput
//Student Records:
//        3 | Chandan | Age: 11 | Grade: C
//4 | Manan | Age: 11 | Grade: B
//1 | Amit | Age: 11 | Grade: A
//2 | Arjun | Age: 12 | Grade: B
//Student with Roll Number 102 not found.
//Student with Roll Number 101 not found.
//Student with Roll Number 103 not found.
//Student Records:
//        3 | Chandan | Age: 11 | Grade: C
//4 | Manan | Age: 11 | Grade: B
//1 | Amit | Age: 11 | Grade: A
//2 | Arjun | Age: 12 | Grade: B
