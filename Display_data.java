package zoho_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Display_data {
    public void display_seats(Connection conn, String table_name) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + table_name;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Table Contents:");
            System.out.print("Special seats"+"  "+"normal_seats\n");
            while (resultSet.next()) {
                String spec = resultSet.getString("special_seat");
                String nor = resultSet.getString("normal_seats");
                System.out.println(spec +"              "+ nor);
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching table contents: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the ResultSet.");
                    e.printStackTrace();
                }
            }
            // Close the Statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the Statement.");
                    e.printStackTrace();
                }
            }
        }
    }
}
