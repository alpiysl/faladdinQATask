package com.faladdin.Listener;

import com.aventstack.extentreports.Status;
import com.faladdin.base.BaseTest;
import com.faladdin.extentreports.ExtentManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

import static com.faladdin.extentreports.ExtentTestManager.getTest;


public class Listener extends BaseTest implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(Listener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public Listener() {
    }


    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.info("I am in onStart method " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("I am in onFinish method " + iTestContext.getName());
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("Test Started: " + getTestMethodName(iTestResult));
    }


    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.info("Test Failed: " + getTestMethodName(iTestResult));
        LOGGER.error(iTestResult.getThrowable());
        String errormsg = iTestResult.getThrowable().getMessage();
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        AndroidDriver<AndroidElement> androidDriver = ((BaseTest) testClass).getAndroidDriver();

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(androidDriver)).getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed : " + errormsg, getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.info("Test Skipped: " + getTestMethodName(iTestResult));
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOGGER.info("Test Failed Within Success Percentage: " + getTestMethodName(iTestResult));
    }


}
