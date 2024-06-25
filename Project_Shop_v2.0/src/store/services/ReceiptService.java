package store.services;

import store.models.Cashier;
import store.models.Product;
import store.models.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ReceiptService {
    private List<Receipt> receipts;

    public ReceiptService() {
        this.receipts = new ArrayList<>();
    }

    public Receipt issueReceipt(int id,Cashier cashier, List<Product> products) {
        Receipt receipt = new Receipt(id, cashier, products);
        receipts.add(receipt);
        return receipt;
    }

    public List<Receipt> getAllReceipts() {
        return new ArrayList<>(receipts);
    }

    public int getTotalReceiptsCount() {
        return receipts.size();
    }

    public double getTotalRevenue() {
        double totalRevenue = 0;
        for (Receipt receipt : receipts) {
            totalRevenue += receipt.getTotalAmount();
        }
        return totalRevenue;
    }
}
