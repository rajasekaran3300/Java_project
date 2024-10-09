package zoho_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection_to_DB {

    public static Connection DBconnect(String url, String user, String password) {
        Connection connection = null;
        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed. Check output console.");
            e.printStackTrace();
        }
        return connection;
    }

    // Login setup
    public static void createlogin(Connection conn, String tableName) {
        Statement statement = null;
        try {
            String query = "CREATE TABLE " + tableName + " (" +
                           "emp_id SERIAL PRIMARY KEY, " +
                           "name VARCHAR(200), " +
                           "password VARCHAR(200)" +
                           ")";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.err.println("Table creation failed.");
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the statement.");
                    e.printStackTrace();
                }
            }
        }
    }
    public static boolean entrycheck(Connection conn, String table_name,String name, String password) {
    	 Statement statement = null;
         ResultSet resultSet= null;
         try {
         	String query = String.format("SELECT * FROM %s WHERE name='%s' AND password='%s'", table_name, name, password);
             statement = conn.createStatement();
             resultSet = statement.executeQuery(query);

             if (resultSet.next()) {
                 System.out.println("User already signed up continue!!!");
                 return true;
             } else {
                 System.out.println("continue:");
             }
         }
         
         catch(Exception e) {
         }
         return false;
    }
    // Saving name into table
    public static void insert_into_table(Connection conn, String table_name, String name, String password) {
        Statement statement = null;
        ResultSet resultSet= null;
        try {
            String query = String.format("INSERT INTO %s(name, password) VALUES('%s', '%s')", table_name, name, password);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("-------User details saved successfully.-------- \n signed up succesfully");
        } catch (SQLException e) {
            System.err.println("Insertion failed.");
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the statement.");
                    e.printStackTrace();
                }
            }
        }
    }

	public void check(Connection conn, String table_name, String name, String password) {
		   Statement statement = null;
	        ResultSet resultSet = null;
	        try {
				String query = String.format("SELECT * FROM %s WHERE name='%s' AND password='%s'", table_name, name, password);
	            statement = conn.createStatement();
	            resultSet = statement.executeQuery(query);

	            if (resultSet.next()) {
	                System.out.println("Login successful!");
	            } else {
	                System.out.println("Invalid username or password. or not signed up ....!");
	            }
	        } catch (SQLException e) {
	            System.err.println("Error while checking credentials.");
	            e.printStackTrace();
	        }
		
	}
}
