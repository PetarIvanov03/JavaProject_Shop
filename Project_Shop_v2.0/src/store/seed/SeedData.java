package store.seed;

import store.models.Cashier;
import store.models.Category;
import store.models.Checkout;
import store.models.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeedData {
    public static List<Product> FillListWithItems(){
        // Adding food products
        List<Product> products = new ArrayList<>();

        products.add(new Product(products.size(), "Pizza", 10, Category.FOOD, LocalDate.of(2024, 2, 26), 7));
        products.add(new Product(products.size(), "Dunner", 15, Category.FOOD, LocalDate.of(2024, 6, 5), 7));
        products.add(new Product(products.size(), "Croissant", 12, Category.FOOD, LocalDate.of(2024, 7, 30), 7));
        products.add(new Product(products.size(), "Burger", 8, Category.FOOD, LocalDate.of(2024, 8, 15), 7));
        products.add(new Product(products.size(), "Pasta", 11, Category.FOOD, LocalDate.of(2024, 9, 1), 7));
        products.add(new Product(products.size(), "Salad", 6, Category.FOOD, LocalDate.of(2024, 10, 12), 7));
        products.add(new Product(products.size(), "Sushi", 20, Category.FOOD, LocalDate.of(2024, 4, 20), 7));
        products.add(new Product(products.size(), "Steak", 25, Category.FOOD, LocalDate.of(2024, 12, 25), 7));
        products.add(new Product(products.size(), "Tacos", 7, Category.FOOD, LocalDate.of(2025, 1, 14), 7));
        products.add(new Product(products.size(), "Sandwich", 5, Category.FOOD, LocalDate.of(2025, 7, 10), 7));
        products.add(new Product(products.size(), "Ramen", 14, Category.FOOD, LocalDate.of(2025, 3, 18), 7));
        products.add(new Product(products.size(), "Curry", 13, Category.FOOD, LocalDate.of(2025, 4, 20), 7));
        products.add(new Product(products.size(), "Burrito", 9, Category.FOOD, LocalDate.of(2025, 5, 25), 7));
        products.add(new Product(products.size(), "Soup", 4, Category.FOOD, LocalDate.of(2025, 6, 30), 7));
        products.add(new Product(products.size(), "Wrap", 6, Category.FOOD, LocalDate.of(2025, 7, 10), 7));

        // Adding non-food products
        products.add(new Product(products.size(), "Shampoo", 3, Category.NON_FOOD, LocalDate.of(2024, 4, 20), 7));
        products.add(new Product(products.size(), "Soap", 1, Category.NON_FOOD, LocalDate.of(2025, 9, 30), 7));
        products.add(new Product(products.size(), "Toothpaste", 2, Category.NON_FOOD, LocalDate.of(2025, 10, 25), 7));
        products.add(new Product(products.size(), "Detergent", 4, Category.NON_FOOD, LocalDate.of(2025, 11, 10), 7));
        products.add(new Product(products.size(), "Laptop", 700, Category.NON_FOOD, LocalDate.of(2025, 1, 5), 7));
        products.add(new Product(products.size(), "Phone", 300, Category.NON_FOOD, LocalDate.of(2026, 1, 15), 7));
        products.add(new Product(products.size(), "TV", 400, Category.NON_FOOD, LocalDate.of(2026, 2, 20), 7));
        products.add(new Product(products.size(), "Table", 50, Category.NON_FOOD, LocalDate.of(2026, 3, 5), 7));
        products.add(new Product(products.size(), "Chair", 20, Category.NON_FOOD, LocalDate.of(2026, 4, 12), 7));
        products.add(new Product(products.size(), "Blender", 30, Category.NON_FOOD, LocalDate.of(2026, 5, 20), 7));

        return products;
    }

    public static List<Cashier> FillListWithCashiers(){
        // Adding food products
        List<Cashier> cashiers = new ArrayList<>();

        cashiers.add(new Cashier(1, "Pesho", 2500));
        cashiers.add(new Cashier(2, "Gosho", 2200));
        cashiers.add(new Cashier(3, "Misho", 2000));

        return cashiers;
    }

    public static List<Checkout> FillListWithCheckouts(){
        List<Checkout> checkouts = new ArrayList<>();

        checkouts.add(new Checkout(1));
        checkouts.add(new Checkout(2));
        checkouts.add(new Checkout(3));

        return checkouts;
    }
}
