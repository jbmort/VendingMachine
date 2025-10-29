package com.vending.Machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockDatabase {
    public static Set<Product> products = new HashSet<>();

    public MockDatabase(List<Product> products) {
        List<Product> lowerCaseProductList = new ArrayList<>();
        for (Product product : products) {
            Product lowerCaseProduct = new Product(product.name().toLowerCase(), product.price(), product.quantity());
            MockDatabase.products.add(lowerCaseProduct);
        }
    }

    public static void addProduct(Product product) {
        boolean productExists = MockDatabase.ProductExists(product.name());
        if(!productExists) {
            MockDatabase.products.add(product);
        }
        else {
            Product currentItem = MockDatabase.getProduct(product.name());
            Product newItem = new Product(
                    product.name().toLowerCase(),
                    product.price(),
                    product.quantity() + currentItem.quantity());
            MockDatabase.products.remove(currentItem);
            MockDatabase.products.add(newItem);
        }

    }

    public static boolean ProductExists(String productName) {
        for (Product product : products) {
            if (productName.toLowerCase().equals(product.name())){
                return true;}
        }
        return false;
    }

    public static double getPrice(String productName) {
        double price = 0.0;
        for (Product product : MockDatabase.products) {
            if (product.name().equalsIgnoreCase(productName)) {
                price = product.price();
            }
        }
        if (price == 0.0) {
            throw new IllegalArgumentException("Product not found");
        }
        return price;
    }

    public static Product getProduct(String productName) {
        Product productToReturn = new Product();
        for (Product product : MockDatabase.products) {
            if (product.name().equalsIgnoreCase(productName)) {
                productToReturn = product;
            }
        }
        return productToReturn;
    }

    public static boolean buyProduct(Product productToBuy, double moneyInput) {
        // check for product and verify there is enough money for it
        double price = productToBuy.price();
        Product product = getProduct(productToBuy.name());

        if(moneyInput >= price) {
//          purchase product
            boolean productPurchased = product.purchase();
            if (productPurchased) {
                MockDatabase.products.add(product);
                // add product.price() to new class to keep track of payment collected by the machine
                return true;
            }
        }
        return false;

    }

    public static List<Product> getProducts() {
        return products.stream().toList();
    }
}
