package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseTest;
import reports.ExtentReportManager;
import utils.ScreenshotUtils;
import utils.LoggerUtil;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ExtentReportManager.createTest(testName, description);
        LoggerUtil.info("Starting test: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed successfully");
        LoggerUtil.info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String screenshotPath = ScreenshotUtils.captureScreenshot(BaseTest.getDriverFromThreadLocal(), testName);

        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());
        ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);

        LoggerUtil.error("Test failed: " + testName + " - " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable().getMessage());
        LoggerUtil.warn("Test skipped: " + result.getMethod().getMethodName());
    }
}