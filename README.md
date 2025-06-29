# Selenium E-commerce Test Automation

## Overview
This project is an advanced Selenium-based test automation framework for an e-commerce web application (Magento demo store). It is designed to automate end-to-end scenarios such as product search, cart management, checkout, and user registration, with a focus on maintainability, reporting, and CI/CD integration.

## Features
- **Selenium WebDriver** for browser automation
- **TestNG** for test orchestration and parallel execution
- **Page Object Model (POM)** for maintainable and reusable code
- **Advanced reporting** with ExtentReports and Allure
- **Configurable via properties and JSON test data**
- **Screenshots on failure**
- **Logging with Log4j**
- **Supports Chrome, Firefox, and Edge browsers**
- **CI/CD ready with GitHub Actions**

## Project Structure
```
E-commerce_Task/
├── pom.xml
├── README.md
├── reports/                # ExtentReports output
├── screenshots/            # Captured screenshots
├── src/
│   ├── main/java/
│   │   ├── pages/          # Page Object classes
│   │   ├── reports/        # Reporting utilities
│   │   └── utils/          # Utilities (config, logging, screenshots, test data)
│   └── test/java/
│       ├── base/           # Base test setup
│       ├── listeners/      # TestNG listeners
│       └── tests/          # Test classes
│   └── test/resources/
│       ├── config/         # config.properties
│       └── testdata.json   # Test data
├── testng.xml              # TestNG suite config
└── .github/workflows/      # GitHub Actions workflows
```

## Getting Started
### Prerequisites
- Java 17+
- Maven 3.6+
- Chrome, Firefox, or Edge browser

### Setup
1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd E-commerce_Task
   ```
2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

### Configuration
- **`src/test/resources/config/config.properties`**
  - Set the base URL, browser, timeouts, and reporting paths.
  - Example:
    ```properties
    base.url=https://magento.softwaretestingboard.com/
    browser=edge
    headless=false
    report.path=reports/
    screenshot.path=screenshots/
    ```
- **Test Data:**
  - Located in `src/test/resources/testdata.json` (used for product, user, and checkout data).

### Running Tests
- **Via Maven:**
  ```bash
  mvn test
  ```
- **Specify browser/headless mode:**
  ```bash
  mvn test -Dbrowser=chrome -Dheadless=true
  ```
- **TestNG Suite:**
  - The `testng.xml` file defines the test suite and parallel execution.

### Reporting
- **ExtentReports:**
  - HTML report generated at `reports/ExtentReport.html` after each run.
- **Screenshots:**
  - Captured on test failure and saved in the `screenshots/` directory.
- **Allure Reporting:**
  - Allure results are generated if the plugin is configured (see `pom.xml`).

### Logging
- Uses Log4j for detailed logging. Logs are output to the console and can be configured for file output.

## CI/CD
- **GitHub Actions** workflow is provided in `.github/workflows/selenium-ecommerce-tests.yml`.
  - Runs tests on push to `main`/`develop`, pull requests to `main`, and daily at 2 AM UTC.
  - Artifacts (reports, screenshots) are uploaded for each run.

## Test Structure
- **BaseTest:** Handles WebDriver setup/teardown, browser selection, and reporting hooks.
- **Page Objects:** Encapsulate UI interactions for Home, Product, Cart, and Checkout pages.
- **Test Classes:**
  - `LoginTests`, `RegisterTests`, `CartTests`, `HomePageTests` cover major user flows.
- **Listeners:**
  - Custom TestNG listener for reporting and screenshot capture on failure.

## Example Test Case
```java
@Test
public void validateUnregisteredUserCanCompletePurchase() throws Exception {
    // Uses test data from testdata.json
    homePage.searchProduct(data.get("productName"));
    productPage = homePage.selectProductByIndex(Integer.parseInt(data.get("productIndex")));
    productPage.selectSize(data.get("size"))
               .selectColor(data.get("color"))
               .enterQuantity(data.get("quantity"))
               .addToCart();
    cartPage = productPage.navigateToCartPage();
    checkoutPage = cartPage.navigateToCheckoutPage();
    // ... fill checkout fields ...
    checkoutPage.pressPlaceOrder();
    assert checkoutPage.isOrderPlaced();
}
```

## Customization
- Add new test data in `testdata.json` or extend page objects for new flows.
- Update `config.properties` for different environments or browsers.

## Contributing
Pull requests are welcome! Please follow the existing code style and add tests for new features.

## License
This project is for educational/demo purposes.
