package cict.assignment1.user;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;


    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    };

    public int getId() {return id;};
    public String getName() {return name;};
    public String getEmail() {return email;};
    public String getrole() {return role;};

    public void setId (int id) {
        this.id = id;
    }
    public void setName (String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean checkPassword(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public void printAccountDetails() {
        System.out.println("Your id: "+id);
        System.out.println("Your name: "+name);
        System.out.println("Your email: "+email);
        System.out.println("Your role: "+role);
    }



    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", email=" + email +
                ", password=" + password +
                ", role=" + role + '\'' +
                '}';
    }

}

