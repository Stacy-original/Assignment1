package cict.assignment1.user;

public class Employee extends User {
    public Employee(int id, String name, String email, String password) {
        super(id,name,email,password, "Employee");
    }
}
