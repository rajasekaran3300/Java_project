package zoho_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Book_seat {
    public static void special_ticket_booking(Connection conn, String table_name, String seat_no) {
        PreparedStatement preparedStatement = null;
        try {
            String query = String.format("UPDATE %s SET special_seat = 'booked' WHERE special_seat = ?", table_name);
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, seat_no);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("-------Seat status updated successfully.-------- \n      Booking successful");
            } else {
                System.out.println("-------No seats were updated.-------- \n Seat might not exist.");
            }
        } catch (SQLException e) {
            System.err.println("Update failed.");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the prepared statement.");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void normal_ticket_booking(Connection conn, String table_name, String seat_no) {
        // Implement the method for normal ticket booking
    	 PreparedStatement preparedStatement = null;
    	 try {
    		 String query = String.format("Update %s set Normal_seat ='booked' where Normal_seat = ?",table_name);
    		 preparedStatement = conn.prepareStatement(query);
    		 preparedStatement.setString(1, seat_no);
    		 int rowupdated = preparedStatement.executeUpdate();
    		 
    		 
    		 
    	 }
    	 catch(SQLException e){
    		 
    	 }
    	 finally {
    		 
    	 }
    }
}
