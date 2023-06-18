import java.util.Scanner;

public class Runner {

    //!!! step 1:

    /*
    MiniProject: Student Management System
        1. Create Student Management System that can be used by any educational institution
        2. User (Admin) can: CRUD operations
            -register user (id, name, lastName, city, age fields)
            -list/display students/student
            -update student by id
            -delete student by id

 */

    // 1. create menu for the app
    // 2. create student class(entity)
    // 3. create methods for the students
    // 4. create class to connect database

    public static void main(String[] args) {
        start();

    }


    // step-1 create menu
    public static void start(){
        Scanner input = new Scanner(System.in);

        //to reach out methods from service class we need to create instance of StudentService class
        StudentService service = new StudentService();
        //step -8 . table should be crated when start() method is called
        service.createTable();


        int select;
        do {
            System.out.println("===============================");
            System.out.println("**** Student Admin Panel ****");
            System.out.println("1-Student Register");
            System.out.println("2-List All Students");
            System.out.println("3-Delete Student");
            System.out.println("4-Update Student");
            System.out.println("5-Find Student By ID");
            System.out.println("0-Exit");
            System.out.println("Select Activity");

            select = input.nextInt();
            input.nextLine(); //to call next enter/ new line
            int id;
            switch (select){
                case 1:
                    //register
                    break;
                case 2:
                    //display all students
                    break;
                case 3:
                    //delete student by id
                    id = getStudentById(input);
                    break;
                case 4:
                    //update student by id
                    id = getStudentById(input);
                    break;
                case 5:
                    //find student by ID
                    id = getStudentById(input);
                    break;
                case 0:
                    //exist the  app
                default:
                    System.out.println("Incorrect input");


            }


        }while (select != 0);

    }
    //method to get id from user
    private static int getStudentById(Scanner inp){
        System.out.println("Enter student id: ");
        int id = inp.nextInt();
        inp.nextLine();
        return id;
    }

    //Create StudentTable
}
