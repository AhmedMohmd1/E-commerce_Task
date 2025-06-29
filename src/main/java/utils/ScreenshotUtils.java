package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String destinationPath = "screenshots/" + fileName;

            File destinationFile = new File(destinationPath);
            FileUtils.copyFile(sourceFile, destinationFile);

            LoggerUtil.info("Screenshot captured: " + destinationPath);
            return destinationPath;

        } catch (Exception e) {
            LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    public static String captureFullPageScreenshot(WebDriver driver, String testName) {
        // Implementation for full page screenshot
        // This would require additional libraries like AShot
        return captureScreenshot(driver, testName);
    }
}