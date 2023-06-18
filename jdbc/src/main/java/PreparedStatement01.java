import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement statement = con.createStatement();

        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)
        //Normal  string query
        //String query1 = "UPDATE departments SET pass_grade = 475 WHERE department ILIKE 'mathematics'";
        System.out.println("************** Task-1 **************");

        //parameterized query
        String query1 = "UPDATE departments SET pass_grade = ? WHERE department ILIKE ?";
        //create prepared statement
        PreparedStatement prs = con.prepareStatement(query1);
        //set values for the parameters
        prs.setInt(1, 475);
        prs.setString(2, "mathematics");
        //execute the prepared statement
        int numOfUpdatedRows =  prs.executeUpdate();
        System.out.println("Updated data num: "+numOfUpdatedRows);

        System.out.println("************** Task-2 **************");
        //TASK-2. Update pass_grade to 455 of Literature department (use PreparedStatement)

        prs.setString(2, "literature");
        prs.setInt(1, 455);
        int numOfUpdatedRows2 = prs.executeUpdate();
        System.out.println("Updated: "+numOfUpdatedRows2);

        prs.close();
        statement.close();
        con.close();
    }
}
