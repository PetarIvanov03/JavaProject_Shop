package store.services;

import store.models.Checkout;
import store.models.Product;
import store.models.Receipt;
import store.seed.SeedData;
import store.util.ReceiptGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private List<Checkout> checkouts;
    private ReceiptGenerator receiptGenerator;

    public CheckoutService() {
        this.checkouts = SeedData.FillListWithCheckouts();
        this.receiptGenerator = new ReceiptGenerator();
    }

    public void addCheckout(Checkout checkout) {
        checkouts.add(checkout);
    }

    public List<Checkout> getAllCheckouts() {
        return new ArrayList<>(checkouts);
    }

    public Checkout getCheckoutById(int id) {
        return checkouts.stream()
                .filter(checkout -> checkout.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateCheckout(Checkout checkout) {
        Checkout existingCheckout = getCheckoutById(checkout.getId());
        if (existingCheckout != null) {
            existingCheckout.setCashier(checkout.getCashier());
        }
    }



    public Receipt processPurchase(int id, int checkoutId, List<Product> products) throws IOException {
        Checkout checkout = getCheckoutById(checkoutId);
        if (checkout == null) {
            throw new IllegalArgumentException("Checkout not found");
        }

        Receipt receipt = new Receipt(id, checkout.getCashier(), products);

        ReceiptGenerator generator = new ReceiptGenerator();
        generator.generateReceiptFile(receipt);
        return receipt;
    }
}