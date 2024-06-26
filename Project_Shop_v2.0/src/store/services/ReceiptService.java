package store.services;

import store.models.Cashier;
import store.models.Product;
import store.models.Receipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptService {
    private List<Receipt> receipts;

    public ReceiptService() {
        this.receipts = new ArrayList<>();
    }

    public Receipt issueReceipt(Cashier cashier, List<Product> products) {
        Receipt receipt = new Receipt(this.receipts.size(), cashier, products);
        receipts.add(receipt);

        receipt.printReceipt();
        try {
            receipt.saveReceiptToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return receipt;
    }

    public List<Receipt> getAllReceipts() {
        return new ArrayList<>(receipts);
    }

    public double getTotalRevenue() {
        double totalRevenue = 0;
        for (Receipt receipt : receipts) {
            totalRevenue += receipt.getTotalAmount();
        }
        return totalRevenue;
    }

    public List<Product> getAllSoldProducts() {
        List<Product> products = new ArrayList<>();

        for (Receipt receipt : receipts) {
            for (Product p : receipt.getProducts()){
                products.add(p);
            }
        }
        return products;
    }
}
