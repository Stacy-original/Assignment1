package cict.assignment1.user;

public class Employee extends User {
    private String department;
    public Employee(int id, String name, String email, String password) {

        super(id,name,email,password, "Employee");
    }

    public Employee(int id, String name, String email, String password, String department) {

        super(id,name,email,password, "Employee");
        this.department=department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
