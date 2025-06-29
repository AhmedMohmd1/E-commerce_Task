package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P01_HomePage extends BasePage {
    public P01_HomePage(WebDriver driver) {
        super(driver);
    }

    private final By searchBox = By.cssSelector("#search");
    private final By productGrid = By.cssSelector(".products-grid .product-item");
    private final By cartIcon = By.cssSelector(".minicart-wrapper");

    public void searchProduct(String productName) {
        enterText(searchBox, productName);
        waitForProductResults();
    }

    public P02_ProductPage selectProductByIndex(int index) {
        List<WebElement> products = driver.findElements(productGrid);
        if (index < products.size()) {
            products.get(index).click();
        }
        return new P02_ProductPage(driver);
    }

    private void waitForProductResults() {
        wait.until(driver -> !driver.findElements(productGrid).isEmpty());
    }

    public void verifyLogoIsDisplayed() {
        if (!driver.findElement(By.cssSelector("a.logo")).isDisplayed()) {
            throw new AssertionError("Logo is not displayed");
        }
    }

    public void verifySearchBarIsVisible() {
        if (!driver.findElement(By.id("search")).isDisplayed()) {
            throw new AssertionError("Search bar is not visible");
        }
    }

    public void verifySearchButtonIsVisible() {
        if (!driver.findElement(searchBox).isDisplayed()) {
            throw new AssertionError("Search box is not visible");
        }
    }

    public void verifyCartIconIsVisible() {
        if (!driver.findElement(cartIcon).isDisplayed()) {
            throw new AssertionError("Cart icon is not visible");
        }
    }
}
