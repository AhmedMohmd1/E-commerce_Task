package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LoggerUtil;

import java.util.List;


public class P02_ProductPage extends BasePage {

    private final By addToCartButton = By.cssSelector("button[title='Add to Cart']");
    private final By quantityField = By.id("qty");
    private final By cartIcon = By.cssSelector(".minicart-wrapper");
    private final By proceedCheckoutButton = By.id("top-cart-btn-checkout");
    private final By itemTitle = By.cssSelector("h1.page-title");
    private final By itemPrice = By.cssSelector(".product-info-price .normal-price .price");
    private final By cartCounter = By.cssSelector(".counter.qty");
    private final By shoppingCartLink = By.linkText("shopping cart");
    private final By checkoutButton = By.cssSelector("li button[title='Proceed to Checkout']");

    public P02_ProductPage(WebDriver driver) {
        super(driver);
    }

    public P02_ProductPage selectSize(String size) {
        By sizeLocator = By.cssSelector(".swatch-attribute.size .swatch-option[option-label='" + size + "']");
        scrollTillVisible(sizeLocator);
        clickElement(sizeLocator);
        return this;
    }

    public P02_ProductPage selectColor(String color) {
        By colorLocator = By.cssSelector(".swatch-attribute.color .swatch-option[option-label='" + color + "']");
        scrollTillVisible(colorLocator);
        clickElement(colorLocator);
        return this;
    }

    public P02_ProductPage enterQuantity(String qnt) {
        scrollTillVisible(quantityField);
        enterText(quantityField, qnt);
        return this;
    }

    public P02_ProductPage addToCart() {
        scrollTillVisible(addToCartButton);
        clickElement(addToCartButton);
        return this;
    }

    // public int getCartCount() {
    // int cartCount = Integer.parseInt(cartCounter.getText().trim());
    // System.out.println("Cart count = " + cartCount);
    // return cartCount;
    // }

    public P02_ProductPage pressCartIcon() throws InterruptedException {
        scrollTillVisible(cartIcon);
        waitTillPresence(cartIcon);
        WaitUntilVisibleElement(cartCounter);
        clickElement(cartIcon);
        return this;
    }

    public P03_CartPage navigateToCartPage() {
        clickElement(shoppingCartLink);
        return new P03_CartPage(driver);
    }

}