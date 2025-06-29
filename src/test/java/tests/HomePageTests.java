package tests;

import base.BaseTest;
import reports.ExtentReportManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;
import java.lang.reflect.Method;

public class HomePageTests extends BaseTest {
    @BeforeMethod
    public void beforeMethod(Method method) {
        ExtentReportManager.createTest(method.getName(), "Test: " + method.getName());
    }

    @Test
    public void validateHomePageElements() {
        ExtentReportManager.getTest().info("Starting test for Homepage");

        Assert.assertTrue(homePage.verifyLogoIsDisplayed());
        Assert.assertTrue(homePage.verifySearchBarIsVisible());
        Assert.assertTrue(homePage.verifySearchButtonIsVisible());
        Assert.assertTrue(homePage.verifyCartIconIsVisible());
    }
}
