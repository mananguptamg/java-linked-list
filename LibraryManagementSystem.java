// Class representing a Book node in the doubly linked list
class Book {
    String title;
    String author;
    String genre;
    int bookID;
    boolean isAvailable;
    Book next; // Pointer to the next book
    Book prev; // Pointer to the previous book

    // Constructor to initialize book details
    public Book(String title, String author, String genre, int bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

// Class to manage the Library using a doubly linked list
class LibraryManager {
    private Book head; // Points to the first book
    private Book tail; // Points to the last book
    private int bookCount; // Stores the total number of books

    // Method to add a book at the beginning
    public void addBookAtBeginning(String title, String author, String genre, int bookID, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookID, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        bookCount++;
    }

    // Method to add a book at the end
    public void addBookAtEnd(String title, String author, String genre, int bookID, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookID, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        bookCount++;
    }

    // Method to add a book at a specific position
    public void addBookAtPosition(String title, String author, String genre, int bookID, boolean isAvailable, int position) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }

        Book newBook = new Book(title, author, genre, bookID, isAvailable);

        if (position == 1) {
            addBookAtBeginning(title, author, genre, bookID, isAvailable);
            return;
        }

        Book temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            addBookAtEnd(title, author, genre, bookID, isAvailable);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
            bookCount++;
        }
    }

    // Method to remove a book by Book ID
    public void removeBook(int bookID) {
        if (head == null) {
            System.out.println("No books in the library.");
            return;
        }

        Book temp = head;
        while (temp != null && temp.bookID != bookID) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book with ID " + bookID + " not found.");
            return;
        }

        if (temp == head) {
            head = temp.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (temp == tail) {
            tail = temp.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        bookCount--;
        System.out.println("Book with ID " + bookID + " removed.");
    }

    // Method to search for a book by Title
    public void searchBookByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Book Found: " + temp.title + " | Author: " + temp.author);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No books found with title '" + title + "'.");
        }
    }

    // Method to search for a book by Author
    public void searchBookByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                System.out.println("Book Found: " + temp.title + " | Author: " + temp.author);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No books found by author '" + author + "'.");
        }
    }

    // Method to update a book’s availability status by Book ID
    public void updateBookAvailability(int bookID, boolean isAvailable) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookID == bookID) {
                temp.isAvailable = isAvailable;
                System.out.println("Updated availability of book ID " + bookID + " to " + (isAvailable ? "Available" : "Not Available"));
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID " + bookID + " not found.");
    }

    // Method to display all books in forward order
    public void displayBooksForward() {
        if (head == null) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("Books (Forward Order):");
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.bookID + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.next;
        }
    }

    // Method to display all books in reverse order
    public void displayBooksReverse() {
        if (tail == null) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("Books (Reverse Order):");
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.bookID + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.prev;
        }
    }

    // Method to count total books in the library
    public int countTotalBooks() {
        return bookCount;
    }
}

// Main class to run the program
public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        // Adding books
        library.addBookAtEnd("Gulliver's Travels", "Jonathan Swift", "Adventure", 101, true);
        library.addBookAtEnd("Story of My Life", "Helen Keller", "Autobiography", 102, true);
        library.addBookAtBeginning("Java Essentials", "James Gosling", "Education", 103, false);
        library.addBookAtPosition("Panchatantra", "Vishnu Sharma", "Stories", 104, true, 1);

        // Display books
        library.displayBooksForward();
        library.displayBooksReverse();

        // Search books
        library.searchBookByTitle("Story of My Life");
        library.searchBookByAuthor("Jonathan Swift");

        // Update availability
        library.updateBookAvailability(102, false);

        // Remove book
        library.removeBook(103);

        // Display after updates
        library.displayBooksForward();

        // Count total books
        System.out.println("Total Books: " + library.countTotalBooks());
    }
}

//SampleOutput
//Books (Forward Order):
//104 | Panchatantra | Vishnu Sharma | Stories | Available
//103 | Java Essentials | James Gosling | Education | Not Available
//101 | Gulliver's Travels | Jonathan Swift | Adventure | Available
//102 | Story of My Life | Helen Keller | Autobiography | Available
//Books (Reverse Order):
//102 | Story of My Life | Helen Keller | Autobiography | Available
//101 | Gulliver's Travels | Jonathan Swift | Adventure | Available
//103 | Java Essentials | James Gosling | Education | Not Available
//104 | Panchatantra | Vishnu Sharma | Stories | Available
//Book Found: Story of My Life | Author: Helen Keller
//Book Found: Gulliver's Travels | Author: Jonathan Swift
//Updated availability of book ID 102 to Not Available
//Book with ID 103 removed.
//Books (Forward Order):
//104 | Panchatantra | Vishnu Sharma | Stories | Available
//101 | Gulliver's Travels | Jonathan Swift | Adventure | Available
//102 | Story of My Life | Helen Keller | Autobiography | Not Available
//Total Books: 3
