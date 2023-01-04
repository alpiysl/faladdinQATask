package com.faladdin.pages;

import com.faladdin.base.BaseStepMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AccountPage  extends BaseStepMethod {
    private static final Logger LOGGER = LogManager.getLogger(AccountPage.class);

    private static final By ACCOUNT_BUTTON = By.id("com.faladdin.app:id/imageBtnAccount");

    private static final By ACCOUNT_NAME = By.id("com.faladdin.app:id/tvName");

    public void accountButton_isDisplayed() {
        LOGGER.info("User ensures that \"Account\" button is displayed");
        assertTrue(isDisplayElement(ACCOUNT_BUTTON), "web element not displayed");
    }

    public void accountButton_isClicked() {
        LOGGER.info("User clicks on \"Account\" button");
        clickElement(ACCOUNT_BUTTON);
    }

    public void accountName_isDisplayed() {
        LOGGER.info("User ensures that \"Alpaslan, 33\" button is displayed");
        assertEquals(getTextElement(ACCOUNT_NAME),"Alpaslan, 33", "web element not displayed");
    }
}
