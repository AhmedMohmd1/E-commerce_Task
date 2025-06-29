package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;

public class CartTests extends BaseTest {
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @Test
    public void validateOrderSummary() throws InterruptedException {
        homePage.searchProduct("Primo Endurance Tank\n");
        productPage = homePage.selectProductByIndex(1);
        productPage.selectSize("M")
                .selectColor("Red")
                .enterQuantity("2")
                .addToCart();
        cartPage = productPage.navigateToCartPage();
        checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterFirstName("test")
                .enterLastName("test2")
                .enterCompany("company")
                .enterAddress("add")
                .enterCity("city")
                .selectState("Alabama")
                .enterZipCode("2345")
                .selectCoutnry("Afghanistan")
                .enterPhone("012356546")
                .selectShippingMethod("Fixed")
                .pressNext().pressPlaceOrder();

    }
}
