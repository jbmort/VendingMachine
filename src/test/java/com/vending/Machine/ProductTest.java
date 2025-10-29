package com.vending.Machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {
    Product product;
    @BeforeEach
    public void setUp() {
        product = new Product("Skittles", 1.00, 1);
    }
    @Test
    public void shouldAllowPurchaseIfEnoughQuantity() {
//        should allow first purchase
        boolean result = product.purchase();
//        second purchase should fail due to low inventory
        boolean result2 = product.purchase();

        assertTrue(result);
        assertFalse(result2);
    }
}
