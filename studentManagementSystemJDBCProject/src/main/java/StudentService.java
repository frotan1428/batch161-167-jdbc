
//Service layer is to handle business logic
public class StudentService {

    //to get access to methods in repository class
    StudentRepository repository = new StudentRepository();

    //step 7. method to call createTable method from Repository Layer
    public void createTable(){
        repository.createTable();
    }

}
