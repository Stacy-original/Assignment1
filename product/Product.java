package cict.assignment1.product;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stockQuantity;
    private String type;

    public Product(int id, String name, double price, int stockQuantity, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.type = type;
    }

    public int getId() {return id;};
    public String getName() {return name;};
    public double getPrice() {return price;};
    public int getStockQuantity() {return stockQuantity;};
    public String getType() {return type;};

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Stock quantity: " + stockQuantity);
        System.out.println("Type: " + type);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", type=" + type + '\'' +
                '}';
    }
}

