package db;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    
    public class DBConnection {
        
        private static final String URL = "jdbc:mysql://localhost:3306/elevatex_club";
        private static final String USER = "root";
        private static final String PASSWORD = "Pragna@955";
        
        public static Connection getConnection() {
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected!");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
            return conn;
        }
    }
    

