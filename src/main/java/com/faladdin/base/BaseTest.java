package com.faladdin.base;


import com.faladdin.Listener.Listener;
import com.faladdin.capabilities.MobileCapabilityTypes;
import com.faladdin.utilities.DriverThread;
import io.appium.java_client.android.*;
import io.appium.java_client.android.AndroidElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners({Listener.class})
public class BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    public AndroidDriver<AndroidElement> androidDriver;

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return androidDriver;
    }


    @Parameters({"platformVersion", "deviceName", "appPackage", "appActivity", "appWaitForLaunch"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String platformVersion, String deviceName, String appPackage, String appActivity, String appWaitForLaunch) {
        DesiredCapabilities androidCaps = new DesiredCapabilities();

        androidCaps.setCapability(MobileCapabilityTypes.PLATFORM_NAME, Platform.ANDROID);
        androidCaps.setCapability(MobileCapabilityTypes.PLATFORM_VERSION, platformVersion);
        androidCaps.setCapability(MobileCapabilityTypes.AUTOMATION_NAME, MobileCapabilityTypes.AUTOMATOR_ANDROID);
        androidCaps.setCapability(MobileCapabilityTypes.DEVICE_NAME, deviceName);
        androidCaps.setCapability(MobileCapabilityTypes.APP_PACKAGE, appPackage);
        androidCaps.setCapability(MobileCapabilityTypes.APP_ACTIVITY, appActivity);
        androidCaps.setCapability(MobileCapabilityTypes.APP_WAIT_FOR_LAUNCH, Boolean.parseBoolean(appWaitForLaunch));

        try {
            androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), androidCaps);
        } catch (MalformedURLException e) {
            LOGGER.error("Driver could not be created! ErrorMessage: " + e.getMessage());
        }

        DriverThread.setDriver(androidDriver);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod // Before each case, runs the app.
    public void beforeMethod() {
        DriverThread.getDriver().launchApp();
    }

    @AfterClass // After each class, driver is closing and cleaning
    public void afterClass() {
        DriverThread.getDriver().quit();
        DriverThread.setDriver(null);
    }


}
