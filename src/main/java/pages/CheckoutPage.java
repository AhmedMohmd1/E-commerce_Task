package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class CheckoutPage extends CartPage {
    public CheckoutPage(WebDriver driver) {
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
    private final By shippingMethod2 = By.name("ko_unique_2");
    private final By shippingMethod1 = By.name("ko_unique_1");

    private final By nextButton = By.xpath("//*[@id='shipping-method-buttons-container']//button[@type='submit']");
    private final By placeOrderButton = By.cssSelector("button[title='Place Order']");

    public CheckoutPage enterFirstName(String name) {
        enterText(firstNameField, name);
        return this;
    }

    public CheckoutPage enterLastName(String name) {
        enterText(lastNameField, name);
        return this;
    }

    public CheckoutPage enterCompany(String company) {
        enterText(companyField, company);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        scrollTillVisible(addressField);
        enterText(addressField, address);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        scrollTillVisible(cityField);
        enterText(cityField, city);
        return this;
    }

    public CheckoutPage selectState(String state) {
        scrollTillVisible(stateField);
        Select stateSelection = new Select(driver.findElement(stateField));
        stateSelection.selectByVisibleText(state);
        return this;
    }

    public CheckoutPage enterZipCode(String code) {
        scrollTillVisible(zipCodeField);
        enterText(zipCodeField, code);
        return this;
    }

    public CheckoutPage selectCoutnry(String country) {
        scrollTillVisible(countryDropdown);
        Select countrySelect = new Select(driver.findElement(countryDropdown));
        countrySelect.selectByVisibleText(country);
        return this;
    }


    public CheckoutPage enterPhone(String phone) {
        enterText(phoneField, phone);
        return this;
    }

    public CheckoutPage selectShippingMethod(String method) {
        if (Objects.equals(method, "Table Rate")) {
            clickElement(shippingMethod1);
        } else {
            clickElement(shippingMethod2);
        }
        return this;
    }

    public CheckoutPage pressNext() {
        clickElement(nextButton);
        return this;
    }

    public void pressPlaceOrder() {
        clickElement(placeOrderButton);
    }
}
