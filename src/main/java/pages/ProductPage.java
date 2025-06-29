package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LoggerUtil;

import java.util.List;

@Slf4j
public class ProductPage extends BasePage {

    // Smart locators with fallback strategies

    private final By addToCartButton = By.cssSelector("button[title='Add to Cart']");
    private final By quantityField = By.id("qty");
    private final By cartIcon = By.cssSelector(".minicart-wrapper");
    private final By proceedCheckoutButton = By.id("top-cart-btn-checkout");
    private final By itemTitle = By.cssSelector("h1.page-title");
    private final By itemPrice = By.cssSelector(".product-info-price .normal-price .price");
    private final By cartCounter = By.cssSelector(".counter.qty");
    private final By shoppingCartLink = By.linkText("shopping cart");
    private final By checkoutButton = By.cssSelector("li button[title='Proceed to Checkout']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public ProductPage selectSize(String size) {
        By sizeLocator = By.cssSelector(".swatch-attribute.size .swatch-option[option-label='" + size + "']");
        clickElement(sizeLocator);
        return this;
    }

    public ProductPage selectColor(String color) {
        By colorLocator = By.cssSelector(".swatch-attribute.color .swatch-option[option-label='" + color + "']");
        clickElement(colorLocator);
        return this;
    }

    public ProductPage enterQuantity(String qnt) {
        enterText(quantityField, qnt);
        return this;
    }

    public ProductPage addToCart() {
        clickElement(addToCartButton);
        return this;
    }

//    public int getCartCount() {
//        int cartCount = Integer.parseInt(cartCounter.getText().trim());
//        System.out.println("Cart count = " + cartCount);
//        return cartCount;
//    }

    public ProductPage pressCartIcon() throws InterruptedException {
        scrollTillVisible(cartIcon);
        waitTillPresence(cartIcon);
        WaitUntilVisibleElement(cartCounter);
        clickElement(cartIcon);
        return this;
    }

    public CartPage navigateToCartPage() {
        clickElement(shoppingCartLink);
        return new CartPage(driver);
    }

}