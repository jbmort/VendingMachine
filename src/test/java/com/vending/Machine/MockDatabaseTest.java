package com.vending.Machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void shouldReturnProduct(){
        Product product = MockDatabase.getProduct("Skittles");

        assertNotNull(product);
        assertEquals("skittles", product.name());
        assertEquals(1.0, product.price);
    }

    @Test
    public void shouldReturnMessageOnPurchaseStatus(){
        String message = MockDatabase.buyProduct(product, 3.00);
        String message2 = MockDatabase.buyProduct(product, .5);
        String expectedPositiveMessage = "Congratulations! You have successfully purchased skittles. Your change is $2.0.";
        String expectedNegativeMessage = "Purchase not successful";


        assertNotNull(message);
        assertEquals(expectedPositiveMessage, message);
        assertEquals(expectedNegativeMessage, message2);
    }

    @Test
    public void shouldDecrementQuantityAndRestrictPurchaseIfQuantityIsZero(){
        String message = MockDatabase.buyProduct(product, 1.00);
        String message2 = MockDatabase.buyProduct(product, 1.00);
        String expectedPositive = "Congratulations! You have successfully purchased skittles. Your change is $0.0.";
        String expectedNegativeMessage = "Purchase not successful";

        Product product = MockDatabase.getProduct("Skittles");

        assertEquals(0, product.quantity());
        assertEquals(expectedPositive, message);
        assertEquals(expectedNegativeMessage, message2);
    }
}
