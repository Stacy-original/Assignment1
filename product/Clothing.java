package cict.assignment1.product;

public class Clothing extends Product {
    private String brand;
    public Clothing(int id, String name, double price, int stockQuantity) {
        super(id, name, price , stockQuantity, "Clothing");
    }
    public Clothing(int id, String name, double price, int stockQuantity, String brand) {
        super(id, name, price , stockQuantity, "Clothing");
        this.brand=brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
