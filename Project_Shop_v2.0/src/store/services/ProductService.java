package store.services;

import store.exceptions.InsufficientQuantityException;
import store.models.Product;
import store.seed.SeedData;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products;

    public ProductService() {
        this.products = SeedData.FillListWithItems();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    public List<Integer> getAllAvailableProductIds() {
        List<Integer> result = new ArrayList<>();

        for (Product p : this.products){
            if (p.isValid()){
                result.add(p.getId());
            }
        }
        return result;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setBuyPrice(product.getBuyPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setExpirationDate(product.getExpirationDate());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setSellPrice(product.getSellPrice());
        }
    }

    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public void purchaseProduct(int id, int quantity) throws InsufficientQuantityException {
        Product product = getProductById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        if (!product.checkQuantityAndValidity(quantity)) {
            throw new InsufficientQuantityException("Insufficient quantity for product: " + product.getName());
        }
        product.purchaseProduct(quantity);
    }
}