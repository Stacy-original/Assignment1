package cict.assignment1.main;

import cict.assignment1.order.Order;
import cict.assignment1.product.Book;
import cict.assignment1.product.Clothing;
import cict.assignment1.product.Electronics;
import cict.assignment1.product.Product;
import cict.assignment1.user.Customer;
import cict.assignment1.user.Employee;
import cict.assignment1.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class systemMain {
    private static final List<User> userList = new ArrayList<>();
    private static final List<Product> productList = new ArrayList<>();
    private static final List<Order> orderList = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        productList.add(new Book(1, "Crime and Punishment", 10.0, 10));
        productList.add(new Electronics(2, "RTX 3090", 1000.0, 1));
        productList.add(new Clothing(3, "Shoes", 15.0, 20));

        userList.add(new Employee(1, "Stas", "stas@gmail.com", "mypass"));
        userList.add(new Customer(2, "kasyak", "kasyak@gmail.com", "mypass"));

        System.out.println("Welcome!");
        System.out.println("Try: kasyak@gmail.com / mypass");

        loginMenu();
    }

    private static void loginMenu() {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Log in");
            System.out.println("0. Exit");

            switch (readInt("Choose: ", 0, 2)) {
                case 1 -> createUser();
                case 2 -> {
                    User user = login();

                    if (user instanceof Employee) {
                        employeeMenu();
                    } else if (user instanceof Customer customer) {
                        customerMenu(customer);
                    }
                }
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
            }
        }
    }

    private static User login() {
        String email = readText("Email: ");
        String password = readText("Password: ");

        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(email) && user.checkPassword(password)) {
                System.out.println("Welcome, " + user.getName() + "!");
                return user;
            }
        }

        System.out.println("Incorrect email or password.");
        return null;
    }

    private static void employeeMenu() {
        while (true) {
            System.out.println("\n=== Employee Menu ===");
            System.out.println("1. List products");
            System.out.println("2. Add product");
            System.out.println("3. List users");
            System.out.println("4. Add user");
            System.out.println("5. View all orders");
            System.out.println("0. Log out");

            switch (readInt("Choose: ", 0, 5)) {
                case 1 -> listProducts();
                case 2 -> createProduct();
                case 3 -> listUsers();
                case 4 -> createUser();
                case 5 -> viewOrders(null);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. List products");
            System.out.println("2. Place an order");
            System.out.println("3. View my orders");
            System.out.println("4. View my account");
            System.out.println("0. Log out");

            switch (readInt("Choose: ", 0, 4)) {
                case 1 -> listProducts();
                case 2 -> placeOrder(customer);
                case 3 -> viewOrders(customer);
                case 4 -> customer.printAccountDetails();
                case 0 -> {return;}
            }
        }
    }

    private static void createUser() {
        String name = readText("Name: ");
        String email;

        while (true) {
            email = readText("Email: ");
            boolean exists = false;

            for (User user : userList) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {break;}

            System.out.println("This email is already registered.");
        }

        String password = readText("Password: ");

        System.out.println("1. Customer");
        System.out.println("2. Employee");
        int type = readInt("Account type: ", 1, 2);

        // Get next ID
        int id = userList.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;

        User user;
        if (type == 1) {
            user = new Customer(id, name, email, password);
        } else {
            user = new Employee(id, name, email, password);
        }

        userList.add(user);
        System.out.println("User created. ID: " + id);
    }

    private static void createProduct() {
        System.out.println("1. Book");
        System.out.println("2. Electronics");
        System.out.println("3. Clothing");
        int type = readInt("Product type: ", 1, 3);

        String name = readText("Name: ");
        double price = readPrice();
        int stock = readInt("Stock quantity: ", 0, Integer.MAX_VALUE);

        int id = productList.stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0) + 1;

        Product product = switch (type) {
            case 1 -> new Book(id, name, price, stock);
            case 2 -> new Electronics(id, name, price, stock);
            default -> new Clothing(id, name, price, stock);
        };

        productList.add(product);
        System.out.println("Product added. ID: " + id);
    }

    private static void listProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n=== Products ===");

        for (Product product : productList) {
            product.displayInfo();
            System.out.println("-----------------------");
        }
    }

    private static void listUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        System.out.println("\n=== Users ===");

        for (User user : userList) {
            user.printAccountDetails();
            System.out.println("--------------------");
        }
    }

    private static void placeOrder(Customer customer) {
        boolean available = false;

        //Checks if any product is available
        for (Product product : productList) {
            if (product.getStockQuantity() > 0) {
                available = true;
                break;
            }
        }

        if (!available) {
            System.out.println("No products are in stock.");
            return;
        }

        List<Product> cart = new ArrayList<>();
        listProducts();

        System.out.println("Select a product ID to add one unit.");
        System.out.println("Select the same ID again for another unit.");
        System.out.println("Enter 0 to checkout or -1 to cancel.");

        // Add product to order
        while (true) {
            int id = readInt("Product ID: ", -1, Integer.MAX_VALUE);

            if (id == -1) {
                System.out.println("Order cancelled.");
                return;
            }

            if (id == 0) {
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty.");
                    continue;
                }
                break;
            }

            Product selected = null;

            for (Product product : productList) {
                if (product.getId() == id) {
                    selected = product;
                    break;
                }
            }

            if (selected == null) {
                System.out.println("Product not found.");
                continue;
            }

            //Checks if product out of stock
            int quantityInCart = Collections.frequency(cart, selected);

            if (quantityInCart >= selected.getStockQuantity()) {
                System.out.println("No more units of this product are available.");
                continue;
            }

            cart.add(selected);
            System.out.println("Added: " + selected.getName());
        }

        double total = 0;

        for (Product product : cart) {
            total += product.getPrice();
        }

        System.out.println("Order total:" + total);

        if (readInt("Confirm order? 1 = Yes, 0 = No: ", 0, 1) == 0) {
            System.out.println("Order cancelled.");
            return;
        }

        int orderId = orderList.stream()
                .mapToInt(Order::getOrderID)
                .max()
                .orElse(0) + 1;

        Order order = new Order(orderId, customer.getId(), cart);

        for (Product product : cart) {
            product.setStockQuantity(product.getStockQuantity() - 1);
        }

        orderList.add(order);
        System.out.println("Order placed successfully!");
        order.printReceipt();
    }

    private static void viewOrders(Customer customer) {
        boolean found = false;

        for (Order order : orderList) {
            if (customer == null || order.getCustomerID() == customer.getId()) {
                order.printReceipt();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No orders found.");
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            try {
                int value = input.nextInt();

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println("Enter a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private static double readPrice() {
        while (true) {
            System.out.print("Price: ");
            try {
                double price = input.nextDouble();

                if (Double.isFinite(price) && price >= 0) {
                    return price;
                }
            } catch (NumberFormatException _) {

            }

            System.out.println("Enter a valid price.");
        }
    }
}