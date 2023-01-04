package com.faladdin.utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DriverThread {

    private static final ThreadLocal<AndroidDriver<AndroidElement>> driverThreadLocal = new ThreadLocal<>();


    public DriverThread() {
    }

    public static synchronized AndroidDriver<AndroidElement> getDriver() {
        return driverThreadLocal.get();
    }

    public static synchronized void setDriver(AndroidDriver<AndroidElement> driver) {
        DriverThread.driverThreadLocal.set(driver);
    }

}
