package store.ui;

import store.Store;
import store.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CommandLineInterface {

    private ArrayList<Store> stores;
    private Scanner scanner;

    public CommandLineInterface() {
        this.stores = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws StoreNotFoundException {
        System.out.println("Commands:");
        System.out.println("commands");
        System.out.println("select shop");
        System.out.println("new shop");
        System.out.println("exit");
        System.out.println();

        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "commands":
                    showCommands();
                    break;
                case "select shop":
                    String name = scanner.nextLine();
                    //must not be null

                    selectShop(this.stores.stream()
                            .filter(x -> x.getName().equals(name))
                            .findFirst().orElseThrow(() -> new StoreNotFoundException("Store with name: " + name + " not found.")));
                    break;
                case "new shop":
                    System.out.println("Enter new shop name:");
                    //must not be null
                    String storeName = scanner.nextLine();
                    newShop(storeName);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command. Type 'commands' to see the list of available commands.");
            }
        }
    }

    private void showCommands() {
        System.out.println("Commands:");
        System.out.println("commands");
        System.out.println("select shop");
        System.out.println("new shop");
        System.out.println("exit");
        System.out.println();
    }

    private void selectShop(Store store) {
        System.out.println("Shop - Commands:");
        System.out.println("commands");
        System.out.println("shop name");
        System.out.println("products");
        System.out.println("checkouts");
        System.out.println("shop products");
        System.out.println("add new product");
        System.out.println("total income");
        System.out.println("total revenue");
        System.out.println("cashiers info");
        System.out.println("expired products");
        System.out.println("exit");
        System.out.println();

        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "commands":
                    showShopCommands();
                    break;
                case "shop name":
                    System.out.println("Shop name: " + store.getName());
                    break;
                case "products":
                    store.printAllValidProducts();
                    break;
                case "checkouts":
                    store.getCheckouts().forEach(System.out::println);
                    break;
                case "shop products":
                    try {
                        shopProducts(store);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "add new product":
                    addNewProduct(store);
                    break;
                case "total income":
                    System.out.println("Total income: " + store.getTotalIncome());
                    break;
                case "total revenue":
                    System.out.println("Total revenue: " + store.getTotalRevenue());
                    break;
                case "cashiers info":
                    store.getCashiers().forEach(System.out::println);
                    break;
                case "expired products":
                    store.printAllExpiredProducts();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command. Type 'commands' to see the list of available commands.");
            }
        }
    }

    private void showShopCommands() {
        System.out.println("Shop - Commands:");
        System.out.println("commands");
        System.out.println("shop name");
        System.out.println("products");
        System.out.println("checkouts");
        System.out.println("shop products");
        System.out.println("add new product");
        System.out.println("total income");
        System.out.println("total revenue");
        System.out.println("cashiers info");
        System.out.println("expired products");
        System.out.println("exit");
        System.out.println();
    }

    private void shopProducts(Store store) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        HashMap<Integer, Integer> productsList = new HashMap<>();

        while(!line.equals("end")) {
            System.out.print("Enter product Id: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            productsList.put(id, quantity);

            System.out.println("Continue?");

            line = scanner.nextLine();
        }

        System.out.println("Ended");
        System.out.println("Select checkout Id:");
        store.getCheckouts().forEach(System.out::println);

        int checkoutId = scanner.nextInt();
        scanner.nextLine();

        store.sellProducts(productsList, checkoutId);

    }




    private void newShop(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullStoreNameException("Shop name cannot be null or empty.");
        }

        this.stores.add(new Store(name));
    }

    private void addNewProduct(Store store) {
        Scanner scanner = new Scanner(System.in);

        // Get product name
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        if (name == null || name.trim().isEmpty()) {
            throw new NullProductNameException("Product name cannot be null or empty.");
        }

        // Get buy price
        System.out.print("Enter buy price: ");
        double buyPrice = scanner.nextDouble();
        scanner.nextLine(); // consume newline left-over

        if (buyPrice <= 0) {
            throw new NullOrNegativeProductPriceException("Product price must be positive");
        }

        // Get product type (food or non-food)
        System.out.print("Is the product food? (yes/no): ");
        boolean isFood;
        String line = scanner.nextLine();

        if (line.equalsIgnoreCase("yes")) {
            isFood = true;
        }
        else if(line.equalsIgnoreCase("no")){
            isFood = false;
        }
        else{
            throw new NullOrNegativeProductQuantityException("Quantity must be positive");
        }

        // Get expiration date
        System.out.print("Enter expiration date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();

        if (dateString == null || dateString.trim().isEmpty()) {
            throw new NullOrEmptyDateException("Expiration date cannot be null or empty.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expDate = LocalDate.now();
        try {
            expDate = LocalDate.parse(dateString, formatter);
        }catch(Exception e){
            System.err.println("Invalid input. Expecting LocalDate in format yyyy-MM-dd!");
            System.exit(1);
        }

        // Get quantity
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            throw new NullOrNegativeProductQuantityException("Quantity must be positive");
        }

        // Create the new product
        store.addNewProduct(name, buyPrice, isFood, expDate, quantity);

        System.out.println("Product added successfully.");
    }
}