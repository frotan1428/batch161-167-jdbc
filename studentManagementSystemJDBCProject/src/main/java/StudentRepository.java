import java.sql.*;


//class which will be connted to DB(connection, statement, prepared statement)
public class StudentRepository {

    private Connection con;
    private Statement statement;
    private PreparedStatement prs;

    //step-3 method to create connection
    private void getConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //step-4 method to create statement
    private void getStatement(){
        try {
            this.statement = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //step-5; method to create preparedstatment
    private void getPreparedStatement(String query){
        try {
            this.prs = con.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //step -6; method to create table
    public void createTable(){
        getConnection(); //create connection
        getStatement(); //to be able to run queries

        try {
            String query = "CREATE TABLE IF NOT EXISTS tbl_student (id SERIAL, name VARCHAR(50), lastname VARCHAR(50), city VARCHAR (50), age INT)";
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
