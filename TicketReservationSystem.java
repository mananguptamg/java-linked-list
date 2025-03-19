class TicketReservationSystem {
    private class TicketNode {
        int ticketId;
        String customerName;
        String movieName;
        int seatNumber;
        String bookingTime;
        TicketNode next;

        TicketNode(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
            this.next = null;
        }
    }

    private TicketNode head = null;
    private TicketNode tail = null;
    private int totalTickets = 0;

    public void addTicket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        TicketNode newNode = new TicketNode(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
        totalTickets++;
    }

    public void removeTicket(int ticketId) {
        if (head == null) return;
        if (head.ticketId == ticketId) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            totalTickets--;
            return;
        }
        TicketNode temp = head;
        while (temp.next != head) {
            if (temp.next.ticketId == ticketId) {
                temp.next = temp.next.next;
                if (temp.next == head) {
                    tail = temp;
                }
                totalTickets--;
                return;
            }
            temp = temp.next;
        }
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }
        TicketNode temp = head;
        while (true) {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
            if (temp == head) break;
        }
    }

    public void searchTicketByCustomer(String customerName) {
        if (head == null) return;
        TicketNode temp = head;
        while (true) {
            if (temp.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket Details - ID: " + temp.ticketId + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            }
            temp = temp.next;
            if (temp == head) break;
        }
    }

    public void searchTicketByMovie(String movieName) {
        if (head == null) return;
        TicketNode temp = head;
        while (true) {
            if (temp.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket Details - ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            }
            temp = temp.next;
            if (temp == head) break;
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(1, "Aman", "PK", 12, "10:00 AM");
        system.addTicket(2, "Samir", "3 Idiots", 15, "12:00 PM");
        system.addTicket(3, "Arjun", "Barfi", 20, "2:00 PM");

        System.out.println("Current Reservations:");
        system.displayTickets();

        System.out.println("Searching for tickets booked by Arjun");
        system.searchTicketByCustomer("Arjun");

        System.out.println("Searching for tickets for PK:");
        system.searchTicketByMovie("PK");

        System.out.println("Total Tickets Booked: " + system.getTotalTickets());
    }
}

//SampleOutput
//Current Reservations:
//Ticket ID: 1, Customer: Aman, Movie: PK, Seat: 12, Time: 10:00 AM
//Ticket ID: 2, Customer: Samir, Movie: 3 Idiots, Seat: 15, Time: 12:00 PM
//Ticket ID: 3, Customer: Arjun, Movie: Barfi, Seat: 20, Time: 2:00 PM
//Searching for tickets booked by Arjun
//Ticket Details - ID: 3, Movie: Barfi, Seat: 20, Time: 2:00 PM
//Searching for tickets for PK:
//Ticket Details - ID: 1, Customer: Aman, Seat: 12, Time: 10:00 AM
//Total Tickets Booked: 3