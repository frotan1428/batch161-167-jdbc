import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.step: Register Driver - (optional)
        Class.forName("org.postgresql.Driver");

        //2.step : Create Connection to get connected to DB

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        //3. step: Create Statement -- to execute SQL queries
        Statement statement= con.createStatement();

        //to test if we have created connection to DB
        //System.out.println("Connected successfully");

        //4. step: Execute SQL queries
        //TASK: create a table named "employee" with column names of :
        // "employee_id", "employee_name", "salary"

        boolean sql1 = statement.execute("CREATE TABLE employee (employee_id INT, employee_name VARCHAR(50), salary REAL)");

        /*
            --execute() returns boolean value and can be used for DDL (Data Definition Language) or
            DQL(Data Query Language)
            --if it is used with DDL it return false
            --if it is ued with DQL (Select..) it return true

         */

        //System.out.println("sql1: "+sql1);

        //TASK 2: add Varchar (20) column name "city" to employee table
        String query = "ALTER TABLE employee ADD COLUMN city VARCHAR(20)";
        boolean sql2 =  statement.execute(query);

        //System.out.println("sql2L : "+sql2);

        //TASK 3: Delete employee table from SCHEMA

        String query1 = "DROP TABLE employee";
        statement.execute(query1);

        // 5.step: close connection and statement
        statement.close();
        con.close();
    }


}
