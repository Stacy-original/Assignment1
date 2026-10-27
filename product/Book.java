package cict.assignment1.product;

public class Book extends Product {
    private int publishedYear;
    public Book(int id, String name, double price, int stockQuantity) {
        super(id, name, price , stockQuantity, "Book");
    }
    public Book(int id, String name, double price, int stockQuantity, int year) {
        super(id, name, price , stockQuantity, "Book");
        this.publishedYear=year;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
}
