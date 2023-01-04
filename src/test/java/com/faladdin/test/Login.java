package com.faladdin.test;

import com.faladdin.base.BaseTest;
import com.faladdin.pages.AccountPage;
import com.faladdin.pages.ExplorePage;
import com.faladdin.pages.HomePage;
import com.faladdin.pages.LoginTypes;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.faladdin.extentreports.ExtentTestManager.*;

public class Login extends BaseTest {

    Login login;
    HomePage homePage;
    ExplorePage explorePage;
    LoginTypes loginTypes;
    AccountPage accountPage;

    @Test(enabled = true, description = "Faladdin - Login Test")
    public void loginTest(Method method) throws InterruptedException {
        startTest(method.getName(), "Login Scenario with Google");

        login = new Login();
        homePage = new HomePage();
        explorePage = new ExplorePage();
        loginTypes = new LoginTypes();
        accountPage = new AccountPage();

        getTest().info("User ensures that \"Explore\" button is displayed");
        homePage.ExploreButton_isDisplayed();

        getTest().info("User clicks on \"Explore\" button");
        homePage.ExploreButton_isClicked();

        getTest().info("User ensures that \"Account\" button is displayed");
        explorePage.AccountButton_isDisplayed();

        getTest().info("User clicks on \"Account\" button");
        explorePage.AccountButton_isClicked();

        getTest().info("User ensures that \"Select Account\" button disappeared");
        loginTypes.userClosesSelectAccountTab();

        getTest().info("User ensures that \"Continue with Facebook\" button is displayed");
        loginTypes.faceBookButton_isDisplayed();

        getTest().info("User ensures that \"Continue with Google\" button is displayed");
        loginTypes.googleButton_isDisplayed();

        getTest().info("User ensures that \"Continue with Email\" button is displayed");
        loginTypes.emailButton_isDisplayed();

        getTest().info("User ensures that \"Create Your Account\" button is displayed");
        loginTypes.createYourAccount_isDisplayed();

        getTest().info("User clicks on \"Google\" button");
        loginTypes.googleButton_isClicked();

        getTest().info("User clicks on expect email");
        loginTypes.selectTheEmail();

        getTest().info("User ensures that \"Continue\" is displayed");
        homePage.welcomeButton_isDisplayed();

        getTest().info("User clicks on \"Continue\" button");
        homePage.welcomeButton_isClicked();

        getTest().info("User ensures that \"Account\" is displayed");
        accountPage.accountButton_isDisplayed();

        getTest().info("User clicks on \"Account\" button");
        accountPage.accountButton_isClicked();

        getTest().info("User ensures that \"Alpaslan, 33\" is displayed");
        accountPage.accountName_isDisplayed();

    }

}
