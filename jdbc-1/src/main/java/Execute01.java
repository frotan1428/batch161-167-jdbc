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

        if(con!=null){
            System.out.println("connection is successfull ");
        }else {
            System.out.println("connection filed");
        }

        //to test if we have created connection to DB
        //System.out.println("Connected successfully");

        //4. step: Execute SQL queries

        //CREATE TABLE employee(employee_id INT,employee_name VARCHAR(50),salary REAL)
        //TASK: create a table named "employee" with column names of :
        // "employee_id", "employee_name", "salary"

        boolean sql1 = statement.execute("CREATE TABLE employee (employee_id INT, employee_name VARCHAR(50), salary REAL)");


        /*
          //execute () returns boolean value and can be used for DDL data definition language or Data Query Language
          execute() method can be used in DDL(Data Definition Language --> Crate Table,
                Drop Table, Alter Table) and DQL (Data Query Language --> Select)
            1) If you use execute() method with DDL everytime you will get false.
            2) If you use execute() method with DQL  you will get false or true.
            If you get the resultSet object as return you will get true otherwise you will get false.
         */
//
//        System.out.println("sql1: "+sql1);
//
//        //TASK 2: add Varchar (20) column name "city" to employee table
        String query = "ALTER TABLE employee ADD COLUMN city VARCHAR(20)";
        boolean sql2 =  statement.execute(query);

        //System.out.println("sql2L : "+sql2);

//        //TASK 3: Delete employee table from SCHEMA
//
        String query1 = "DROP TABLE employee";
        statement.execute(query1);




        // 5.step: close connection and statement
        statement.close();
        con.close();//it is realised the resource
    }


}
