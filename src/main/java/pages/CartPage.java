package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By checkoutButton = By.cssSelector("li button[title='Proceed to Checkout']");

    public CheckoutPage navigateToCheckoutPage() {
        scrollTillVisible(checkoutButton);
        clickElement(checkoutButton);
        return new CheckoutPage(driver);
    }


}
