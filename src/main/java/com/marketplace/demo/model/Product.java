package com.marketplace.demo.model;

public class Product {

    private Long productID;
    private String name;
    private String imagePath;
    private double price;
    private String color;

    public Product(Long productID, String name, String imagePath, double price, String color) {
        this.productID = productID;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.color = color;
    }

    public Product() {
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
