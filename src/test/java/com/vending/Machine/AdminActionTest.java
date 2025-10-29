package com.vending.Machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminActionTest {
    AdminAction adminAction;
    Product skittles;

    @BeforeEach
    void setUp() {
        adminAction = new AdminAction();
        skittles = new Product("skittles", 1.00, 2);
        MockDatabase.addProduct(skittles);
    }

    @Test
    public void shouldAllowAdminToAddProduct() {
        Product snickers =  new Product("snickers", 1.00, 2);
        String productAddedStatus = adminAction.addProduct(snickers);

        boolean checkForSnickers = MockDatabase.ProductExists("snickers");

        assertTrue(checkForSnickers);
    }

    @Test
    public void shouldAddInventoryWithoutDuplicationIfProductExists() {
        String expectedResult = "New Product Added To Inventory";
        String expectedAddInventory = "Inventory Added";

        Product MM =  new Product("M&M's", 1.00, 2);

        String addQuantity = adminAction.addProduct(skittles);
        String productAddedStatus = adminAction.addProduct(MM);

        Product skittlesCheck = MockDatabase.getProduct("skittles");

        assertEquals(expectedResult, productAddedStatus);
        assertEquals(expectedAddInventory, addQuantity);
        assertEquals(4, skittlesCheck.quantity());

    }

    @Test
    public void shouldReturnLowInventoryProducts(){
        Product snickers =  new Product("snickers", 1.00, 10);
        MockDatabase.addProduct(snickers);
        Product MM =  new Product("M&M's", 1.00, 3);
        MockDatabase.addProduct(MM);

        List<Product> lowInventory = adminAction.getLowInventory();

        assertEquals(2, lowInventory.size());
    }

}
