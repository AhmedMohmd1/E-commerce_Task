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

    public boolean verifyLogoIsDisplayed() {
        return driver.findElement(By.cssSelector("a.logo")).isDisplayed();

    }

    public boolean verifySearchBarIsVisible() {
        return driver.findElement(By.id("search")).isDisplayed();

    }

    public boolean verifySearchButtonIsVisible() {
        return driver.findElement(searchBox).isDisplayed();

    }

    public boolean verifyCartIconIsVisible() {
        return driver.findElement(cartIcon).isDisplayed();
    }
}
