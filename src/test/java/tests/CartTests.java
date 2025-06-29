package tests;

import base.BaseTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P03_CartPage;
import pages.P04_CheckoutPage;
import reports.ExtentReportManager;
import pages.P02_ProductPage;
import utils.TestDataReader;
import java.util.Map;
import java.lang.reflect.Method;

public class CartTests extends BaseTest {
    P02_ProductPage productPage;
    P03_CartPage cartPage;
    P04_CheckoutPage checkoutPage;

    @BeforeMethod
    public void beforeMethod(Method method) {
        ExtentReportManager.createTest(method.getName(), "Test: " + method.getName());
    }

    @Test
    public void validateUnregisteredUserCanCompletePurchase() throws Exception {
        String testDataPath = "src/test/resources/testdata.json";
        Map<String, String> data = TestDataReader.getTestData(testDataPath);
        ExtentReportManager.getTest().info("Starting test for unregistered user purchase");
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
        ExtentReportManager.getTest().pass("Purchase completed successfully");

    }

    @Test
    public void validateUserCanUpdateCartQuantity() throws Exception {
        String testDataPath = "src/test/resources/testdata.json";
        Map<String, String> data = TestDataReader.getTestData(testDataPath);
        ExtentReportManager.getTest().info("Starting test for updating cart quantity");
        homePage.searchProduct(data.get("productName") + "\n");
        productPage = homePage.selectProductByIndex(Integer.parseInt(data.get("productIndex")));
        productPage.selectSize(data.get("size"))
                .selectColor(data.get("color"))
                .enterQuantity(data.get("quantity"))
                .addToCart();
        cartPage = productPage.navigateToCartPage();
        cartPage.enterQuantity(data.get("quantity"));
        cartPage.validateUpdateQuantity(Double.parseDouble(data.get("updatedQuantity")));
        ExtentReportManager.getTest().pass("Item Quantity updated successfully");

    }

    @Test
    public void validateUserCanRemoveItemFromCart() throws Exception {
        String testDataPath = "src/test/resources/testdata.json";
        Map<String, String> data = TestDataReader.getTestData(testDataPath);
        ExtentReportManager.getTest().info("Starting test for removing item from cart");
        homePage.searchProduct(data.get("productName") + "\n");
        productPage = homePage.selectProductByIndex(Integer.parseInt(data.get("productIndex")));
        productPage.selectSize(data.get("size"))
                .selectColor(data.get("color"))
                .enterQuantity(data.get("quantity"))
                .addToCart();
        cartPage = productPage.navigateToCartPage();
        cartPage.deleteItemFromCart().isCartEmpty();
        ExtentReportManager.getTest().pass("Item Deleted successfully");

    }
}