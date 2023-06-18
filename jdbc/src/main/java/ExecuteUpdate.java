import java.sql.*;

public class ExecuteUpdate {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement statement = con.createStatement();

        //TASK-1. Update salaries of developers whose salaries are less than average salary with average salary
        System.out.println("************** Task-1 **************");
        String query1 = "UPDATE developers SET salary = (SELECT AVG(salary) FROM developers) " +
                "WHERE salary<(SELECT AVG(salary) FROM developers)";

        int updatedRows = statement.executeUpdate(query1); //executeUpdate() return int value which number of updated records
        System.out.println("Updated rows: "+updatedRows);

        //to see all records
        String query2 = "SELECT name, salary FROM developers";
        ResultSet rs1 = statement.executeQuery(query2);
        while (rs1.next()){
            System.out.println(rs1.getString("name")+" -- "+ rs1.getDouble("salary"));
        }

        //TASK-2. Add new developer to developers table
        System.out.println("************** Task-2 **************");
        String query3 = "INSERT INTO developers (id, name, salary, prog_lang) VALUES (21, 'Bedreddin', 6000, 'Python')";
        int numInserted = statement.executeUpdate(query3);
        System.out.println("Inserted rows: "+numInserted);

        //TASK-3. DELETE row which has id of 14
        System.out.println("************** Task-3 **************");
        String query4 = "DELETE FROM developers WHERE id = 14";
        int deletedRows = statement.executeUpdate(query4);
        System.out.println("Deleted rows: " + deletedRows);

        System.out.println("************** Task-4 **************");

        //TASK-4. DELETE rows from developers table if  prog_lang is "Ruby"

        String query5="DELETE  FROM developers WHERE prog_lang ILIKE 'ruby'";
        int deletedRows2 = statement.executeUpdate(query5);
        System.out.println("Deleted rows: "+deletedRows2);

        statement.close();
        con.close();
    }
}
