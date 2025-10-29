package com.vending.Machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserSelectionTest {
    UserSelection selection;
    Product product;

//
    @BeforeEach
    void setUp() {
        selection = new UserSelection();
        product = new Product("skittles", 1.00, 1);
        MockDatabase.addProduct(product);

    }

    @Test
    public void shouldReturnExpectedPriceForProduct(){
        double expectedPrice = 1.00;
        double price = selection.getPriceForItem("skittles");

        assertEquals(expectedPrice, price);
    }

    @Test
    public void shouldThrowExceptionWhenProductDoesNotExist(){
        assertThrows(
                IllegalArgumentException.class,
                () -> selection.getPriceForItem("snickers"));
    }

    @Test
    void shouldAllowUserToBuyItemIfEnoughMoney() {
        String expectedMessage = "Congratulations! You have successfully purchased skittles. Your change is $2.0.";
        String message = this.selection.BuyItem("skittles", 3.00);

        assertEquals(expectedMessage, message);
    }

    @Test
    void shouldNotAllowUserToBuyItemIfNotEnoughMoney() {
        String expectedString = "Purchase not successful";
        String message = this.selection.BuyItem("skittles", .50);

        assertEquals(expectedString, message);
    }

//    @Test
//    void shouldReturnTrueIfSelectionExistsInProducts() {
//        boolean existsPositive = selection.pick("Skittles");
//        boolean existsNegative = selection.pick("Snickers");
//
//        assertTrue(existsPositive);
//        assertFalse(existsNegative);
//    }

//    @Test
//    void shouldConfirmAndDenyAbilityToPurchase(){
//        boolean canPurchasePositive = selection.canBuy("Skittles", 3.50 );
//        boolean canPurchaseNegative = selection.canBuy("Skittles", .50);
//
//        assertTrue(canPurchasePositive);
//        assertFalse(canPurchaseNegative);
//    }

}
