package com.faladdin.pages;

import com.faladdin.base.BaseStepMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import static org.testng.Assert.*;

public class ExplorePage extends BaseStepMethod {

    private static final Logger LOGGER = LogManager.getLogger(ExplorePage.class);

    private static final By ACCOUNT_BUTTON = By.id("com.faladdin.app:id/imageBtnAccount");

    public void AccountButton_isDisplayed() {
        LOGGER.info("User ensures that \"Account\" button is displayed");
        assertTrue(isDisplayElement(ACCOUNT_BUTTON), "web element not displayed");
    }

    public void AccountButton_isClicked() {
        LOGGER.info("User clicks on \"Explore\" button");
        clickElement(ACCOUNT_BUTTON);
    }
}
