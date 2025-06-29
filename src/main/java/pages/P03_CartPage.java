package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class P03_CartPage extends BasePage {
    public P03_CartPage(WebDriver driver) {
        super(driver);
    }

    private final By checkoutButton = By.cssSelector("li button[title='Proceed to Checkout']");
    private final By itemPriceLocator = By.cssSelector(".cart-price .price");
    private final By totalPriceLocator = By.cssSelector(".grand .price");

    private final By quantity = By.cssSelector("input[title='Qty']");
    private final By updateButton = By.cssSelector(".cart button[type='submit']");
    private final By deleteIcon = By.cssSelector(".actions-toolbar [title='Remove item']");
    private final By emptyCartMessage = By.linkText("You have no items in your ");

    public P03_CartPage deleteItemFromCart() {
        scrollTillVisible(deleteIcon);
        clickElement(deleteIcon);
        return this;
    }

    public boolean isCartEmpty() {
        try {
            waitTillPresence(emptyCartMessage);
            return driver.findElement(emptyCartMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public double getItemPrice() {
        String priceText = driver.findElements(itemPriceLocator).get(0).getText().replaceAll("[^\\d.]", "");
        System.out.println("Item Price: " + priceText);
        return Double.parseDouble(priceText);
    }

    public P03_CartPage clickUpdateButton() {
        scrollTillVisible(updateButton);
        clickElement(updateButton);
        return this;
    }

    public P03_CartPage enterQuantity(String qty) {
        enterText(quantity, qty);
        return this;
    }

    public void validateUpdateQuantity(double qnt) {
        double itemPriceValue = getItemPrice();
        enterQuantity(String.valueOf(qnt));
        clickUpdateButton();
        double expectedTotalPrice = itemPriceValue * qnt;
        double actualTotalPrice = getTotalPrice();
        assert actualTotalPrice == expectedTotalPrice : "Total price does not match expected value. Expected: "
                + expectedTotalPrice + ", Actual: " + actualTotalPrice;

    }

    public double getTotalPrice() {
        String priceText = driver.findElement(totalPriceLocator).getText().replaceAll("[^\\d.]", "");
        System.out.println("Total Price: " + priceText);
        return Double.parseDouble(priceText);
    }

    public P04_CheckoutPage navigateToCheckoutPage() {
        scrollTillVisible(checkoutButton);
        clickElement(checkoutButton);
        return new P04_CheckoutPage(driver);
    }

}
