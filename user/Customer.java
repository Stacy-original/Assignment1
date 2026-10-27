package cict.assignment1.user;

public class Customer extends User {
    private String phoneNumber;
    public Customer(int id, String name, String email, String password) {
        super(id,name,email,password, "Customer");

    }
    public Customer(int id, String name, String email, String password, String phoneNumber) {
        super(id,name,email,password, "Customer");
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
