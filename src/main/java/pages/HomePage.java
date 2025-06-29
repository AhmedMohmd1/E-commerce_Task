package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    private final By searchBox = By.cssSelector("#search");
    private final By searchButton = By.cssSelector("button[title='Search']");
    private final By productGrid = By.cssSelector(".products-grid .product-item");

    public void searchProduct(String productName) {
        enterText(searchBox, productName);
        waitForProductResults();
    }

    public ProductPage selectProductByIndex(int index) {
        List<WebElement> products = driver.findElements(productGrid);
        if (index < products.size()) {
            products.get(index).click();
        }
        return new ProductPage(driver);
    }

    private void waitForProductResults() {
        wait.until(driver -> !driver.findElements(productGrid).isEmpty());
    }
}
