import java.sql.*;

public class Transaction01 {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement statement = con.createStatement();

        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678
        //by default it is true
        con.setAutoCommit(false); //// Disable auto-commit mode
        Savepoint transferSavepoint=null;

        //!!step 2:
        try {


            /*
                suppose we have other queries
             */
            //!!step 3: savepoint

            transferSavepoint=  con.setSavepoint("transfer_savepoint"); // Set a savepoint

            //!!step1
            String query = "UPDATE accounts SET amount = amount + ? WHERE account_nu = ?";
            //create prepared statement
            PreparedStatement prs = con.prepareStatement(query);
            //first update query starts
            prs.setInt(1, -1000);//// Deduct $1000 from Account Fred
            prs.setInt(2, 1234);
            prs.executeUpdate();
            //first update query ends


            //since it is always true, exception will be thrown
            //suppose we have some problem while updating second update query
            if(true){
                throw new Exception();
            }

            //after exception this update will not run
            //second update query starts
            prs.setInt(1, 1000);// Add $100 to Account Bernie
            prs.setInt(2, 5678);
            prs.executeUpdate();
            System.out.println(" Transfer is Successfully ");
            //second update query end
            //!!end Step 1:

            con.commit(); //we have set this to false, now we are committing manually
            prs.close();
            statement.close();
            con.close();

        }catch (Exception e){
            con.rollback(transferSavepoint); //cancels all previous activities
            System.out.println("Transfer failed. Rolled back to savepoint.");
        }



    }
}
