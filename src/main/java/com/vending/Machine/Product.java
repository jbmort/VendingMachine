package com.vending.Machine;

public class Product {
    String name;
    double price;
    int quantity;

    public Product() {
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String name(){
        return name;
    }

    public double price(){
        return price;
    }
    public int quantity(){
        return quantity;
    }

    public boolean purchase() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }
}
