package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class P04_CheckoutPage extends P03_CartPage {
    public P04_CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By firstNameField = By.cssSelector("input[name=firstname]");
    private final By lastNameField = By.cssSelector("input[name=lastname]");
    private final By companyField = By.cssSelector("input[name=company]");
    private final By addressField = By.name("street[0]");
    private final By cityField = By.cssSelector("input[name=city]");
    private final By stateField = By.cssSelector("select[name=region_id]");
    private final By zipCodeField = By.name("postcode");
    private final By countryDropdown = By.cssSelector("[name='country_id']");
    private final By phoneField = By.cssSelector("[name='telephone']");
    private final By emailField = By.cssSelector("#customer-email-fieldset #customer-email");
    private final By shippingMethod2 = By.name("ko_unique_2");
    private final By shippingMethod1 = By.name("ko_unique_1");

    private final By nextButton = By.xpath("//*[@id='shipping-method-buttons-container']//button[@type='submit']");
    private final By placeOrderButton = By.xpath("//span[normalize-space()='Place Order']");

    private final By validateMessage = By.linkText("Thank you for your purchase!");

    public P04_CheckoutPage enterFirstName(String name) {
        enterText(firstNameField, name);
        return this;
    }

    public P04_CheckoutPage enterEmail(String email) {
        scrollTillVisible(emailField);
        enterText(emailField, email);
        return this;
    }

    public P04_CheckoutPage enterLastName(String name) {
        enterText(lastNameField, name);
        return this;
    }

    public P04_CheckoutPage enterCompany(String company) {
        enterText(companyField, company);
        return this;
    }

    public P04_CheckoutPage enterAddress(String address) {
        scrollTillVisible(addressField);
        enterText(addressField, address);
        return this;
    }

    public P04_CheckoutPage enterCity(String city) {
        scrollTillVisible(cityField);
        enterText(cityField, city);
        return this;
    }

    public P04_CheckoutPage selectState(String state) {
        scrollTillVisible(stateField);
        Select stateSelection = new Select(driver.findElement(stateField));
        stateSelection.selectByVisibleText(state);
        return this;
    }

    public P04_CheckoutPage enterZipCode(String code) {
        scrollTillVisible(zipCodeField);
        enterText(zipCodeField, code);
        return this;
    }

    public P04_CheckoutPage selectCoutnry(String country) {
        scrollTillVisible(countryDropdown);
        Select countrySelect = new Select(driver.findElement(countryDropdown));
        countrySelect.selectByVisibleText(country);
        return this;
    }

    public P04_CheckoutPage enterPhone(String phone) {
        enterText(phoneField, phone);
        return this;
    }

    public P04_CheckoutPage selectShippingMethod(String method) {
        if (Objects.equals(method, "Table Rate")) {
            clickElement(shippingMethod1);
        } else {
            clickElement(shippingMethod2);
        }
        return this;
    }

    public P04_CheckoutPage pressNext() {
        scrollTillVisible(nextButton);
        clickElement(nextButton);
        return this;
    }

    public P04_CheckoutPage pressPlaceOrder() {
        scrollTillVisible(placeOrderButton);
        waitUntilVisibleElement(placeOrderButton);
        clickElement(placeOrderButton);
        return this;
    }

    public boolean isOrderPlaced() {
        try {
            WebElement element = driver.findElement(validateMessage);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
