package com.faladdin.base;
;
import com.faladdin.utilities.DriverThread;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class BaseStepMethod {

    private static final Logger LOGGER = LogManager.getLogger(BaseStepMethod.class);

    protected AndroidDriver<AndroidElement> androidDriver = DriverThread.getDriver();
    private final WebDriverWait wait;

    public BaseStepMethod() {
        this.wait = new WebDriverWait(androidDriver, 30);

        if (androidDriver.isDeviceLocked())
            androidDriver.unlockDevice();
    }

    protected List<AndroidElement> waitAllVisibleByLocator(By locator) {
        List<AndroidElement> element = null;

        try {
            element = androidDriver.findElements(locator);
        } catch (Exception e) {
            LOGGER.error("Web element is not visible!");
        }
        return element;
    }

    protected MobileElement waitVisibleByLocator(By locator) {
        MobileElement element = null;

        try {
            element = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            LOGGER.error("Web element is not visible!");
        }
        return element;
    }

    protected MobileElement waitClickableByOfElement(MobileElement mobileElement) {
        MobileElement element = null;

        try {
            element = (AndroidElement) wait.until(ExpectedConditions.elementToBeClickable(mobileElement));
        } catch (Exception e) {
            LOGGER.error("Web element is not clickable!");
        }
        return element;
    }

    protected MobileElement waitPresenceOfElementByLocator(By locator) {
        MobileElement element = null;

        try {
            element = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            LOGGER.error("Web element not found in document!");
        }
        return element;
    }

    protected void clickElement(By locator) {
        MobileElement element = this.waitVisibleByLocator(locator);
        waitClickableByOfElement(element).click();
    }

    protected String getTextElement(By locator) {
        return waitPresenceOfElementByLocator(locator).getText();
    }


    protected MobileElement setTextElement(By locator, String text) {
        MobileElement element = waitPresenceOfElementByLocator(locator);
        element.clear();
        element.sendKeys(text);

        return element;
    }

    public void swipeByElements (By startElement, By endElement) {
        waitPresenceOfElementByLocator(startElement);
        int startX = androidDriver.findElement(startElement).getLocation().getX() + (androidDriver.findElement(startElement).getSize().getWidth() / 2);
        int startY = androidDriver.findElement(startElement).getLocation().getY() + (androidDriver.findElement(startElement).getSize().getHeight() / 2);
        int endX = androidDriver.findElement(endElement).getLocation().getX() + (androidDriver.findElement(endElement).getSize().getWidth() / 2);
        int endY = androidDriver.findElement(endElement).getLocation().getY() + (androidDriver.findElement(endElement).getSize().getHeight() / 2);
        new TouchAction(androidDriver)
                .press(point(startX,startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }

    public boolean isDisplayElement(By locator) {
        MobileElement element = waitPresenceOfElementByLocator(locator);
        return element.isDisplayed();

    }

    public boolean element_isDisplayed(By locator) {
        return androidDriver.findElement(locator).isDisplayed();

    }

}
