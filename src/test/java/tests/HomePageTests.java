package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {
    @Test
    public void validateHomePageElements() {
        homePage.verifyLogoIsDisplayed();
        homePage.verifySearchBarIsVisible();
        homePage.verifySearchButtonIsVisible();
        homePage.verifyCartIconIsVisible();
    }
}
