import java.sql.*;

public class PreparedStatement02 {

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement statement = con.createStatement();

        //TASK-1. Using prepared statement, delete students who are from Mathematics department, from students table.
        System.out.println("************ TASK-1 **************");
        String query1 = "DELETE FROM students WHERE department ILIKE ?";
        PreparedStatement prs = con.prepareStatement(query1);
        prs.setString(1, "mathematics");
        int numOfUpdatedRows =  prs.executeUpdate();
        System.out.println("Updated data num: "+numOfUpdatedRows);

        //TASK-2. Insert software engineering department using prepared statement into departments table.
        // (id = 5006, pass_grade=475, campus='South')

        System.out.println("************ TASK-2 **************");
        String query2 = "INSERT INTO departments VALUES ( ?, ? , ?, ?)";
        PreparedStatement prs2 =  con.prepareStatement(query2);

        prs2.setInt(1, 5006);
        prs2.setString(2, "software_engineering");
        prs2.setString(4, "South");
        prs2.setInt(3, 475);

        int insertedRows = prs2.executeUpdate();
        System.out.println("Insreted rows: "+ insertedRows);

        prs.close();//This line closes the previously created PreparedStatement object (prs).
        prs2.close();
        statement.close();
        con.close();
    }
}
