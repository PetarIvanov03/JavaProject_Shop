package store.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {
    private int id;
    private String name;
    private double buyPrice;
    private Category category;
    private LocalDate expirationDate;
    private double sellPrice;
    private int quantity;

    public Product(int id, String name, double buyPrice, Category category, LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.sellPrice = calculateSellPrice(this.buyPrice);
        this.quantity = quantity;
    }

    // Private functions
    double calculateSellPrice(double buyPrice) {
        double result = buyPrice;
        result *= category.getMarkUp();
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), this.expirationDate);

        if (daysBetween <= 5) {
            result *= 0.90;
            if (daysBetween < 0) {
                result = 0;
            }
        } else if (daysBetween <= 10) {
            result *= 0.95;
        }

        return result;
    }

    private boolean checkAvailability(int quantity) {
        return quantity <= this.quantity;
    }

    // Getters and setters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getBuyPrice() {
        return this.buyPrice;
    }

    public Category getCategory() {
        return this.category;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public double getSellPrice() {
        return this.sellPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Methods for shop administrator
    public boolean isValid() {
        return ChronoUnit.DAYS.between(LocalDate.now(), this.expirationDate) >= 0;
    }

    public void addQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity += quantity;
        }
    }

    public String getSellPriceAsString(double price) {
        return String.format("%.2f", price);
    }

    // Methods for shop customer
    public String getProductInfo() {
        return "id = " + this.id + " - " + this.name + ", price: " + getSellPriceAsString(this.sellPrice) + ", quantity: " + this.quantity;
    }

    public String getProductInfoCustomer() {
        return this.name + ", price: " + getSellPriceAsString(this.sellPrice) + " x " + this.quantity + " - total: " + getSellPriceAsString(this.sellPrice * this.quantity);
    }



    public void purchaseProduct(int quantity) {
        if (isValid()) {
            if (checkAvailability(quantity)) {
                this.quantity -= quantity;
                System.out.println("Successfully purchased " + quantity + "x of " + this.name);
            } else {
                System.out.println("Currently available quantity: " + this.quantity + "! You asked for: " + quantity);
            }
        } else {
            System.out.println("Product: " + this.name + " is expired");
        }
    }

    public boolean checkQuantityAndValidity(int quantity) {
        boolean result = true;
        if (isValid()) {
            if (!checkAvailability(quantity)) {
                System.out.println("Currently available quantity: " + this.quantity + "! You asked for: " + quantity);
                result = false;
            }
        } else {
            System.out.println("Product: " + this.name + " is expired");
            result = false;
        }

        return result;
    }

    @Override
    public String toString(){
        return "id = " + this.id + " - " + this.name + ", price: " + getSellPriceAsString(this.sellPrice) + ", quantity: " + this.quantity;
    }
}
