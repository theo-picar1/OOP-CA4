import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nSample 1 - Connecting to MySQL Database called \"test\" using MySQL JDBC Driver");


        String url = "jdbc:mysql://localhost/"; // location of database
        String dbName = "OOPCA4";     // database name
        String userName = "root";   // default
        String password = "";       // default

        /// Note that we need a DriverManager installed to connect to the database.
        /// The driver is specified in the "pom.xml" file as follows: (take a look)
        ///      <dependency>
        ///       <groupId>mysql</groupId>
        ///       <artifactId>mysql-connector-java</artifactId>
        ///

        ///  Attempt to connect to the database using the credentials supplied
        try ( Connection conn =
                      DriverManager.getConnection(url + dbName, userName, password) )
        {
            System.out.println("SUCCESS ! - Your program has successfully connected to the MySql Database Server. Well done.");
            System.out.println("... we could query the database using the SQL commands you learned in DBMS...");
            System.out.println("... but for now, we will simply close the connection.");

            System.out.println("Your program is disconnecting from the database - goodbye.");
        }
        catch (SQLException ex)
        {
            /// If the attempt to connect fails, and exception is thrown we end up here in
            /// the  catch block (the exception handler)
            System.out.println("Failed to connect to database - check that you have started the MySQL from XAMPP, and that your connection details are correct.");
            ex.printStackTrace();
        }
    }
}
