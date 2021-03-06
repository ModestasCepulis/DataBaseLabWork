import java.sql.*;

/**
 * Created by t00036478 on 01/02/2018.
 */
public class VanillaMovieManager5 {
    private String driverClass = "oracle.jdbc.driver.OracleDriver";
    private Connection connection = null;
    private String url = "jdbc:oracle:thin:@studentoracle.students.ittralee.ie:1521:orcl";
    private String username = "T00199237"; // your username
    private String password = "qs97qcpm"; //your password

    private String insertSql = "INSERT INTO MOVIES VALUES (3, 'Michael Collins','Neil Jordan', 'Irish civil war')";
    private String selectSql = "SELECT * FROM MOVIES";

    private String insertSql2 = "insert into movies " + "(id, title, director, synopsis) " + "values " + "(?, ?, ?, ?)";

    private int counter=0;


    public VanillaMovieManager5() {
    }

    private void setConnection() {
        try {
            Class.forName(driverClass).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.getMessage());
            ex.printStackTrace();
        }

    }

    private Connection getConnection() {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    public static void main(String[] args) {
        VanillaMovieManager5 manager = new VanillaMovieManager5();
        manager.setConnection();
        manager.queryMovies();
        manager.getNumberRows();

    }

    private void persistMovie() {
        try {
            PreparedStatement pst = getConnection().prepareStatement(insertSql2);
            pst.setInt(1, 4);
            pst.setString(2, "Lawrence of Arabia");
            pst.setString(3, "David Lean");
            pst.setString(4, "First World War");
// Execute the statement
            pst.execute();
            System.out.println("Movie persisted successfully!");
        } catch (java.sql.SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }


    private void queryMovies() {
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(selectSql);
            while (rs.next()) {
                System.out.println("Movie Found: " + rs.getInt("ID") +
                        ", Title:" + rs.getString("TITLE"));
            }
        } catch (java.sql.SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void getNumberRows() {
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(selectSql);
            while (rs.next()) {
                counter++;
            }
            System.out.println("\n\nTotal number of movies: " + counter);
        } catch (java.sql.SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    
}
