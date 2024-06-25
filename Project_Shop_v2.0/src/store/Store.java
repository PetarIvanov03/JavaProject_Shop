package store;

import store.exceptions.InsufficientQuantityException;
import store.models.*;
import store.seed.SeedData;
import store.services.CashierService;
import store.services.CheckoutService;
import store.services.ProductService;

import java.time.LocalDate;
import java.util.*;

public class Store {
    private CashierService cashiers;
    private CheckoutService checkouts;
    private ProductService products;
    private ArrayList<Receipt> receipts;
    private String name;

    public Store(String name) {
        this.cashiers = new CashierService();
        this.checkouts = new CheckoutService();
        this.products = new ProductService();
        this.receipts = new ArrayList<>();
        this.name = name;

        checkouts.getCheckoutById(1).setCashier(cashiers.getCashierById(1));
        checkouts.getCheckoutById(2).setCashier(cashiers.getCashierById(2));
        checkouts.getCheckoutById(3).setCashier(cashiers.getCashierById(3));
        checkouts.addCheckout(new Checkout(4));
    }


    private ArrayList<Product> HashMapToArrayList(HashMap<Integer, Integer> productsList) throws InsufficientQuantityException {
        ArrayList<Product> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> item : productsList.entrySet()) {

            Product p = products.getAllProducts().stream()
                    .filter(x -> x.getId() == item.getKey())
                    .findFirst().orElseThrow(() -> new InsufficientQuantityException("Product with ID: " + item.getKey() + " not found."));


            result.add(new Product(p.getId(), p.getName(), p.getBuyPrice(), p.getCategory(), p.getExpirationDate(), item.getValue()));
        }

        return result;
    }

    // Add cashier
    public void addCashier(Cashier cashier) {
        cashiers.addCashier(cashier);
    }

    // Add product
    public void addProduct(Product product) {
        products.addProduct(product);
    }

    // Add checkout
    public void addCheckout(Checkout checkout) {
        checkouts.addCheckout(checkout);
    }

    // Add receipt
    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    // Get total expenses for salaries
    public double getTotalSalaries() {
        return cashiers.getAllCashiers().stream().mapToDouble(Cashier::getSalary).sum();
    }

    // Get total expenses for products
    public double getTotalExpensesForProducts() {
        return products.getAllProducts().stream().mapToDouble(p -> p.getBuyPrice() * p.getQuantity()).sum();
    }

    // Get total income from sold products
    public double getTotalIncome() {
        return receipts.stream().mapToDouble(Receipt::getTotalAmount).sum();
    }

    // Get total revenue from sold products
    public double getTotalRevenue() {
        return this.getTotalIncome() - this.getTotalSalaries() - this.getTotalExpensesForProducts();
    }

    // Get profit
    public double getProfit() {
        return getTotalIncome() - (getTotalSalaries() + getTotalExpensesForProducts());
    }

    // Print all receipts
    public void printAllReceipts() {
        receipts.forEach(Receipt::printReceipt);
    }



    public boolean CheckProductsAvailabilityRequest(HashMap<Integer, Integer> productsList) throws InsufficientQuantityException {
        boolean result = true;

        ArrayList<Product> list = HashMapToArrayList(productsList);

        for (Product item : list) {
            Product product = this.products.getAllProducts().stream()
                    .filter(p -> p.getId() == item.getId())
                    .findFirst().orElseThrow(() -> new InsufficientQuantityException("Product not found."));

            if(!product.checkQuantityAndValidity(item.getQuantity())){
                result = false;
            }
        }

        return result;
    }


    //add new product
    public void addNewProduct(String name, double buyPrice, boolean isFood, LocalDate expDate, int quantity){

        if (isFood){
            Product p = new Product(this.products.getAllProducts().size(), name,
                    buyPrice, Category.FOOD, expDate, quantity);
            this.products.addProduct(p);
        }
        else{
            Product p = new Product(this.products.getAllProducts().size(), name,
                    buyPrice, Category.NON_FOOD, expDate, quantity);
            this.products.addProduct(p);
        }
    }



    // Sell products
    public void sellProducts(HashMap<Integer, Integer> productsList, int checkoutId) throws Exception {

        if (CheckProductsAvailabilityRequest(productsList)) {
            ArrayList<Product> customerProducts = HashMapToArrayList(productsList);

            for (Product item : customerProducts) {
                Product p = this.products.getAllProducts().stream()
                        .filter(x -> x.getId() == item.getId())
                        .findFirst().orElseThrow(() -> new InsufficientQuantityException("Product not found."));

                if (p != null) {
                    this.products.purchaseProduct(p.getId(), p.getQuantity());

                } else {
                    System.out.println("Something went wrong with product: " + item.getName());
                }
            }
            Receipt receipt = new Receipt(this.receipts.size(), this.checkouts.getCheckoutById(checkoutId).getCashier(), customerProducts);
            addReceipt(receipt);
            receipt.printReceipt();
            receipt.saveReceiptToFile();
        }
    }

    // Getters
    public List<Cashier> getCashiers() {
        return this.cashiers.getAllCashiers();
    }

    public List<Checkout> getCheckouts() {
        return this.checkouts.getAllCheckouts();
    }

    public List<Product> getProducts() {
        return this.products.getAllProducts();
    }

    public List<Receipt> getReceipts() {
        return this.receipts;
    }

    public String getName() {
        return this.name;
    }

    public void printAllValidProducts(){
        for(Product p : this.getProducts()){
            if (p.isValid()) {
                System.out.println(p.getProductInfo());
            }
        }
    }

    public void printAllExpiredProducts(){
        for(Product p : this.getProducts()){
            if (!p.isValid()) {
                System.out.println(p.getProductInfo());
            }
        }
    }



}
