package base;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class BaseTest {
    public WebDriver driver;
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected HomePage homePage;
    protected SoftAssert soft;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("edge") String browser, @Optional("false") String headless) {
        driver = createDriver(browser, Boolean.parseBoolean(headless));
        driverThreadLocal.set(driver);

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("base.url"));
        homePage = new HomePage(driver);
        LoggerUtil.info("Test setup completed for browser: " + browser);
    }

    private WebDriver createDriver(String browser, boolean headless) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--allow-running-insecure-content");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return driver;
    }

    public static WebDriver getDriverFromThreadLocal() {
        return driverThreadLocal.get();
    }

//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//            driverThreadLocal.remove();
//        }
//    }
}