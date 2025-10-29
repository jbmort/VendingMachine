package com.vending.Machine;

public class AdminAction {

    public String addProduct(Product product){
        boolean alreadyExists = MockDatabase.ProductExists(product.name());

        MockDatabase.addProduct(product);
        if(alreadyExists){
            return "Inventory Added";
        }
        return "New Product Added To Inventory";
    }
}
