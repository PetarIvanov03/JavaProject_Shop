package store.models;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Receipt {
    private int id;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> products;
    private double totalAmount;

    public Receipt(int id, Cashier cashier, List<Product> products) {
        this.id = id;
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.products = products;
        this.totalAmount = calculateTotalAmount();
    }

    private double calculateTotalAmount() {
        return products.stream().mapToDouble(p -> p.getSellPrice() * p.getQuantity()).sum();
    }

    public void printReceipt() {
        System.out.println("Receipt #" + id);
        System.out.println("Cashier: " + cashier.getName());
        System.out.println("Date: " + dateTime);
        products.forEach(p -> System.out.println(p.getName() + " x " + p.getQuantity() + " = " + p.getSellPriceAsString(p.getSellPrice() * p.getQuantity())));
        System.out.println("Total: " + totalAmount);
    }

    public void saveReceiptToFile() throws IOException {
        String fileName = "receipt_" + id + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Receipt #" + id + "\n");
            writer.write("Cashier: " + cashier.getName() + "\n");
            writer.write("Date: " + dateTime + "\n");
            for (Product p : products) {
                writer.write(p.getName() + " x " + p.getQuantity() + " = " + p.getSellPriceAsString(p.getSellPrice() * p.getQuantity()) + "\n");
            }
            writer.write("Total: " + totalAmount + "\n");
        }
    }

    public static void readReceiptFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt Number: ").append(this.id).append("\n");
        sb.append("Cashier: ").append(cashier.getName()).append("\n");
        sb.append("Date and Time: ").append(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        sb.append("Products:\n");
        for (Product product : products) {
            sb.append(product.getName()).append(" - ")
                    .append("Quantity: ").append(product.getQuantity()).append(", ")
                    .append("Price: ").append(String.format("%.2f", product.getSellPrice())).append(", ")
                    .append("Total: ").append(String.format("%.2f", product.getSellPrice() * product.getQuantity())).append("\n");
        }
        sb.append("Total Amount: ").append(String.format("%.2f", totalAmount)).append("\n");
        return sb.toString();
    }
}
