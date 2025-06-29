package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.P03_CartPage;
import pages.P04_CheckoutPage;
import pages.P02_ProductPage;
import utils.TestDataReader;
import java.util.Map;

public class CartTests extends BaseTest {
    P02_ProductPage productPage;
    P03_CartPage cartPage;
    P04_CheckoutPage checkoutPage;

    @Test
    public void validateUnregisteredUserCanCompletePurchase() throws Exception {
        String testDataPath = "src/test/resources/testdata.json";
        Map<String, String> data = TestDataReader.getTestData(testDataPath);

        homePage.searchProduct(data.get("productName") + "\n");
        productPage = homePage.selectProductByIndex(Integer.parseInt(data.get("productIndex")));
        productPage.selectSize(data.get("size"))
                .selectColor(data.get("color"))
                .enterQuantity(data.get("quantity"))
                .addToCart();
        cartPage = productPage.navigateToCartPage();
        checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterFirstName(data.get("firstName"))
                .enterEmail(data.get("email"))
                .enterLastName(data.get("lastName"))
                .enterCompany(data.get("company"))
                .enterAddress(data.get("address"))
                .enterCity(data.get("city"))
                .selectState(data.get("state"))
                .enterZipCode(data.get("zipCode"))
                .selectCoutnry(data.get("country"))
                .enterPhone(data.get("phone"))
                .selectShippingMethod(data.get("shippingMethod"))
                .pressNext().pressPlaceOrder();
        checkoutPage.isOrderPlaced();
    }

    @Test
    public void validateUserCanUpdateCartQuantity() throws Exception {
        String testDataPath = "src/test/resources/testdata.json";
        Map<String, String> data = TestDataReader.getTestData(testDataPath);

        homePage.searchProduct(data.get("productName") + "\n");
        productPage = homePage.selectProductByIndex(Integer.parseInt(data.get("productIndex")));
        productPage.selectSize(data.get("size"))
                .selectColor(data.get("color"))
                .enterQuantity(data.get("quantity"))
                .addToCart();
        cartPage = productPage.navigateToCartPage();
        cartPage.enterQuantity(data.get("quantity"));
        cartPage.validateUpdateQuantity(Double.parseDouble(data.get("updatedQuantity")));
    }

    @Test
    public void validateUserCanRemoveItemFromCart() throws Exception {
        String testDataPath = "src/test/resources/testdata.json";
        Map<String, String> data = TestDataReader.getTestData(testDataPath);

        homePage.searchProduct(data.get("productName") + "\n");
        productPage = homePage.selectProductByIndex(Integer.parseInt(data.get("productIndex")));
        productPage.selectSize(data.get("size"))
                .selectColor(data.get("color"))
                .enterQuantity(data.get("quantity"))
                .addToCart();
        cartPage = productPage.navigateToCartPage();
        cartPage.deleteItemFromCart().isCartEmpty();
    }
}