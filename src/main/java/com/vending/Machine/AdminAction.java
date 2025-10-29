package com.vending.Machine;

import java.util.List;

public class AdminAction {

    public String addProduct(Product product){
        boolean alreadyExists = MockDatabase.ProductExists(product.name());

        MockDatabase.addProduct(product);
        if(alreadyExists){
            return "Inventory Added";
        }
        return "New Product Added To Inventory";
    }

    public List<Product> getLowInventory(){
        List<Product> products = MockDatabase.getProducts();

        return MockDatabase.getProducts().stream().filter((product) -> product.quantity() < 5).toList();
    }

    // Add function to get amount of money collected in the machine once a class has been defined to track
}
