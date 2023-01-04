package com.faladdin.pages;

import com.faladdin.base.BaseStepMethod;
import io.appium.java_client.android.AndroidElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

import static org.testng.Assert.*;

public class LoginTypes extends BaseStepMethod {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final By GOOGLE_BUTTON = By.id("com.faladdin.app:id/tvGoogle");
    private static final By FACEBOOK_BUTTON = By.id("com.faladdin.app:id/tvFacebook");
    private static final By EMAIL_BUTTON = By.id("com.faladdin.app:id/btnMail");
    private static final By SELECT_ACCOUNT = By.id("com.faladdin.app:id/btnAccountSelect");
    private static final By SELECT_ACCOUNT_LINE = By.id("com.faladdin.app:id/line");
    private static final By CREATE_YOUR_ACCOUNT = By.id("com.faladdin.app:id/tvCreateAccount");
    private static final By EMAIL = By.id("com.google.android.gms:id/container");

    public void googleButton_isDisplayed() {
        LOGGER.info("User ensures that \"Continue with Google\" button is displayed");
        assertEquals(getTextElement(GOOGLE_BUTTON), "Continue with Google", "web element not displayed");
    }

    public void faceBookButton_isDisplayed() {
        LOGGER.info("User ensures that \"Continue with Facebook\" button is displayed");
        assertEquals(getTextElement(FACEBOOK_BUTTON), "Continue with Facebook", "web element not displayed");
    }

    public void emailButton_isDisplayed() {
        LOGGER.info("User ensures that \"Continue with Email\" button is displayed");
        assertEquals(getTextElement(EMAIL_BUTTON), "Continue with Email", "web element not displayed");
    }

    public void createYourAccount_isDisplayed() {
        LOGGER.info("User ensures that \"Create Your Account\" is displayed");
        assertEquals(getTextElement(CREATE_YOUR_ACCOUNT), "Create Your Account", "web element not displayed");
    }

    public void userClosesSelectAccountTab() {
        LOGGER.info("User closes \"Select Account\" tab");
        if(element_isDisplayed(SELECT_ACCOUNT_LINE)) swipeByElements(SELECT_ACCOUNT_LINE, SELECT_ACCOUNT);
    }

    public void googleButton_isClicked() {
        LOGGER.info("User clicks on \"Continue with Google\" button");
        clickElement(GOOGLE_BUTTON);
    }

    public void selectTheEmail() throws InterruptedException {
        LOGGER.info("User selects the email");
        Thread.sleep(3000);
        waitAllVisibleByLocator(EMAIL).get(1).click();
    }
}
