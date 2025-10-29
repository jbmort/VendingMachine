package com.vending.Machine;


public class UserSelection {
    MockDatabase mockDatabase;
//    Removed the product management from this class in preference of the mock database//

    public UserSelection() {}

    public double getPriceForItem(String selection){
        double price = 0;
        if(MockDatabase.ProductExists(selection.toLowerCase())){
            price = MockDatabase.getProduct(selection.toLowerCase()).price();
        }
        else{
            throw new IllegalArgumentException("Product not found");
        }
        return price;
    }

    public String BuyItem(String selection, double moneyInput) {
        Product productToBuy = MockDatabase.getProduct(selection);

//        verify product exists
        if (productToBuy.name().isEmpty() || productToBuy.price() > moneyInput) {
            return "Purchase not successful";
        }

//        attempt to purchase product
        return MockDatabase.buyProduct(productToBuy, moneyInput);
    }

}
