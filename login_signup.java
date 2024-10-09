package zoho_project;

import java.sql.Connection;
import java.util.Scanner;

public class login_signup {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("       Welcome to Movie ticket booking");

        String url = "jdbc:postgresql://localhost:5432/TestDB";
        String user = "postgres";
        String password = "rajav@06123";

        Connection_to_DB dbConnection = new Connection_to_DB();
        Connection conn = Connection_to_DB.DBconnect(url, user, password);
        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
            return; 
        }
        
        System.out.print("-----Login or signup: ");
        String login_type = sc.nextLine();
        if (login_type.equalsIgnoreCase("signup")) {
            System.out.print("-----Enter user name: ");
            String name = sc.nextLine();
            System.out.print("-----Enter user password: ");
            String pass = sc.nextLine();
            if(dbConnection.entrycheck(conn, "login_table", name, pass)) {
            	System.out.print("User already signed up.");
            } else {
            	dbConnection.insert_into_table(conn, "login_table", name, pass);
            }
            
        } else if (login_type.equalsIgnoreCase("login")) {
        	System.out.print("-----Enter user name: ");
            String name = sc.nextLine();
            System.out.print("-----Enter user password: ");
            String pass = sc.nextLine();
            dbConnection.check(conn, "login_table", name, pass);
        }

        Display_data display = new Display_data();
        System.out.println("TO display seats in theatre press display ");
        String dis = sc.nextLine();
        if (dis.equalsIgnoreCase("display")) {
            display.display_seats(conn, "movie_seats");
        } else {
            System.out.print("Something went wrong ..!!");
        }

        System.out.println("To book a movie ticket press --book--");
        String book = sc.nextLine();
        if (book.equalsIgnoreCase("book")) {
            System.out.println("For special seat Enter ---1 , For normal seat Enter ---2");
            int seat_type = sc.nextInt();
            sc.nextLine(); // consume newline

            Book_seat booking = new Book_seat();
            if (seat_type == 1) {
                System.out.print("Tell me which seat do you want to book : ");
                String seat_no = sc.nextLine().trim();
                booking.special_ticket_booking(conn, "movie_seats", seat_no);
                System.out.print("TO show up the seats available enter show");
                String sh = sc.nextLine();
                if(sh.equals("show")) {
                	display.display_seats(conn, "movie_seats");
                }
            } else if (seat_type == 2) {
                System.out.print("Tell me which seat do you want to book : ");
                String seat_no = sc.nextLine().trim();
                booking.normal_ticket_booking(conn, "movie_seats", seat_no);
            } else {
                System.out.println("Invalid seat type selected.");
            }
        }
    }
}
