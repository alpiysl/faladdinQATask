package com.faladdin.pages;

import com.faladdin.base.BaseStepMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import static org.testng.Assert.*;

public class HomePage extends BaseStepMethod {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    private static final By EXPLORE_BUTTON = By.id("com.faladdin.app:id/getStartedButton");

    private static final By WELCOME_TRIGGER_BUTTON = By.id("com.faladdin.app:id/btnWelcomeTrigger");

    public void ExploreButton_isDisplayed() {
        LOGGER.info("User ensures that \"Explore\" button is displayed");
        assertEquals(getTextElement(EXPLORE_BUTTON),"Explore", "web element not visible");
    }

    public void ExploreButton_isClicked() {
        LOGGER.info("User clicks on \"Explore\" button");
        clickElement(EXPLORE_BUTTON);
    }

    public void welcomeButton_isDisplayed() {
        LOGGER.info("User ensures that \"Continue\" button is displayed");
        assertEquals(getTextElement(WELCOME_TRIGGER_BUTTON),"Continue", "web element not visible");
    }

    public void welcomeButton_isClicked() {
        LOGGER.info("User clicks on \"Continue\" button");
        clickElement(WELCOME_TRIGGER_BUTTON);
    }
}
