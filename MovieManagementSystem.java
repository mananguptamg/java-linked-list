// Class representing a Movie node in the doubly linked list
class Movie {
    String title;
    String director;
    int releaseYear;
    double rating;
    Movie next; // Pointer to the next movie
    Movie prev; // Pointer to the previous movie

    // Constructor to initialize movie details
    public Movie(String title, String director, int releaseYear, double rating) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

// Class to manage the Movie list using a doubly linked list
class MovieManager {
    private Movie head; // Points to the first movie
    private Movie tail; // Points to the last movie

    // Method to add a movie at the beginning
    public void addMovieAtBeginning(String title, String director, int releaseYear, double rating) {
        Movie newMovie = new Movie(title, director, releaseYear, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    // Method to add a movie at the end
    public void addMovieAtEnd(String title, String director, int releaseYear, double rating) {
        Movie newMovie = new Movie(title, director, releaseYear, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    // Method to add a movie at a specific position
    public void addMovieAtPosition(String title, String director, int releaseYear, double rating, int position) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }

        Movie newMovie = new Movie(title, director, releaseYear, rating);

        if (position == 1) {
            addMovieAtBeginning(title, director, releaseYear, rating);
            return;
        }

        Movie temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            addMovieAtEnd(title, director, releaseYear, rating);
        } else {
            newMovie.next = temp.next;
            newMovie.prev = temp;
            temp.next.prev = newMovie;
            temp.next = newMovie;
        }
    }

    // Method to remove a movie by title
    public void removeMovie(String title) {
        if (head == null) {
            System.out.println("No movies in the list.");
            return;
        }

        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Movie with title '" + title + "' not found.");
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

        System.out.println("Movie '" + title + "' removed.");
    }

    // Method to search for a movie by Director
    public void searchMovieByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println("Movie Found: " + temp.title + " | Directed by " + temp.director);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movies found directed by '" + director + "'.");
        }
    }

    // Method to search for a movie by Rating
    public void searchMovieByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println("Movie Found: " + temp.title + " | Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movies found with rating " + rating + ".");
        }
    }

    // Method to update a movie's rating by title
    public void updateMovieRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Updated rating of '" + title + "' to " + newRating);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie with title '" + title + "' not found.");
    }

    // Method to display all movies in forward order
    public void displayMoviesForward() {
        if (head == null) {
            System.out.println("No movies in the list.");
            return;
        }

        System.out.println("Movies (Forward Order):");
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | Director: " + temp.director + " | Year: " + temp.releaseYear + " | Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    // Method to display all movies in reverse order
    public void displayMoviesReverse() {
        if (tail == null) {
            System.out.println("No movies in the list.");
            return;
        }

        System.out.println("Movies (Reverse Order):");
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | Director: " + temp.director + " | Year: " + temp.releaseYear + " | Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
}

// Main class to run the program
public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieManager movieList = new MovieManager();

        // Adding movies
        movieList.addMovieAtEnd("PK", "RajKumar", 2016, 8.8);
        movieList.addMovieAtEnd("3 Idiots", "Rajkumar", 2012, 8.6);
        movieList.addMovieAtBeginning("83", "Kabir Khan", 2021, 7.8);
        movieList.addMovieAtPosition("Tiger Zinda Hai", "Ali Khan", 2016, 9.2, 2);

        // Display movies in forward order
        movieList.displayMoviesForward();

        // Display movies in reverse order
        movieList.displayMoviesReverse();

        // Searching for a movie by Director
        movieList.searchMovieByDirector("Rajkumar");

        // Searching for a movie by Rating
        movieList.searchMovieByRating(9.2);

        // Updating a movie's rating
        movieList.updateMovieRating("PK", 8.0);

        // Removing a movie
        movieList.removeMovie("PK");

        // Display movies after update
        movieList.displayMoviesForward();
    }
}


//SampleOutput
//Movies (Forward Order):
//        83 | Director: Kabir Khan | Year: 2021 | Rating: 7.8
//Tiger Zinda Hai | Director: Ali Khan | Year: 2016 | Rating: 9.2
//PK | Director: RajKumar | Year: 2016 | Rating: 8.8
//        3 Idiots | Director: Rajkumar | Year: 2012 | Rating: 8.6
//Movies (Reverse Order):
//        3 Idiots | Director: Rajkumar | Year: 2012 | Rating: 8.6
//PK | Director: RajKumar | Year: 2016 | Rating: 8.8
//Tiger Zinda Hai | Director: Ali Khan | Year: 2016 | Rating: 9.2
//        83 | Director: Kabir Khan | Year: 2021 | Rating: 7.8
//Movie Found: PK | Directed by RajKumar
//Movie Found: 3 Idiots | Directed by Rajkumar
//Movie Found: Tiger Zinda Hai | Rating: 9.2
//Updated rating of 'PK' to 8.0
//Movie 'PK' removed.
//Movies (Forward Order):
//        83 | Director: Kabir Khan | Year: 2021 | Rating: 7.8
//Tiger Zinda Hai | Director: Ali Khan | Year: 2016 | Rating: 9.2
//        3 Idiots | Director: Rajkumar | Year: 2012 | Rating: 8.6
