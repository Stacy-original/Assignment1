package cict.assignment1.product;

public class Electronics extends Product {
    private String generation;
    public Electronics(int id, String name, double price, int stockQuantity) {
        super(id, name, price , stockQuantity, "Electronics");
    }
    public Electronics(int id, String name, double price, int stockQuantity, String generation) {
        super(id, name, price , stockQuantity, "Electronics");
        this.generation=generation;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }
}
