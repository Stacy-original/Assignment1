package cict.assignment1.order;

import cict.assignment1.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private int customerID;
    private List<Product> productList;
    private double totalAmount;

    public Order(int orderID, int customerID, List<Product> products) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productList = new ArrayList<>();

        for (Product product : products) {
            this.productList.add(new Product(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    1,
                    product.getType()
            ));
        }

        calculateTotalAmount();
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void calculateTotalAmount() {
        totalAmount = 0;

        for (Product product : productList) {
            totalAmount += product.getPrice();
        }
    }

    public void printReceipt() {
        System.out.println("\n========== RECEIPT ==========");
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer ID: " + customerID);

        for (Product product : productList) {
            System.out.printf(
                    "%s (%s) - %.2f%n",
                    product.getName(),
                    product.getType(),
                    product.getPrice()
            );
        }

        System.out.printf("Total: %.2f%n", totalAmount);
        System.out.println("=============================");
    }
}