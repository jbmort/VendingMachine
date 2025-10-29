package com.vending.Machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MockDatabaseTest {
    Product product;

    @BeforeEach
    public void setup() {
        product = new Product("skittles", 1.00, 1);
        MockDatabase.addProduct(product);
    }

    @Test
    public void shouldReturnTrueIfProductIsPresentElseFalse() {
        boolean existsPositive = MockDatabase.ProductExists("Skittles");
        boolean existsNegative = MockDatabase.ProductExists("Snickers");

        assertTrue(existsPositive);
        assertFalse(existsNegative);
    }

    @Test
    public void shouldReturnPrice() {
        double price = MockDatabase.getPrice("Skittles");

        assertEquals(1.00, price);
    }

    @Test
    public void shouldThrowExceptionIfProductDoesNotExistWhenGettingPrice() {
        assertThrows(
                IllegalArgumentException.class,
                () -> MockDatabase.getPrice("Snickers")
        );
    }

    @Test
    public void shouldAddExtraInventoryOfProduct() {
        MockDatabase.addProduct(product);
        int inventory = MockDatabase.getProduct(product.name()).quantity();

        assertEquals(2, inventory);
    }

    @Test
    public void shouldAddNewProduct() {
        Product newProduct = new Product("snickers", 1.00, 1);
        MockDatabase.addProduct(newProduct);

        boolean exists = MockDatabase.ProductExists("snickers");

        assertTrue(exists);


    }

    @Test
    public void shouldReturnProduct(){
        Product product = MockDatabase.getProduct("Skittles");

        assertNotNull(product);
        assertEquals("skittles", product.name());
        assertEquals(1.0, product.price);
    }

    @Test
    public void shouldReturnPurchaseStatus(){
        boolean status = MockDatabase.buyProduct(product, 3.00);
        boolean status2 = MockDatabase.buyProduct(product, .5);

        assertTrue(status);
        assertFalse(status2);
    }

    @Test
    public void shouldDecrementQuantityAndRestrictPurchaseIfQuantityIsZero(){
        boolean status = MockDatabase.buyProduct(product, 1.00);
        boolean status2 = MockDatabase.buyProduct(product, 1.00);

        Product product = MockDatabase.getProduct("Skittles");

        assertEquals(0, product.quantity());
        assertTrue(status);
        assertFalse(status2);
    }

    @Test
    public void shouldReturnFullInventory(){
        List<Product> inventory = MockDatabase.getProducts();

        assertEquals(1, inventory.size());
    }
}
