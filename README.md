![](src/test/resources/faladdin.png)

# Faladdin QA Task

- This project was built on;
  - Build management tool -> Maven 
  - Language -> Java
  - Assertion tool -> TestNG
  - Model -> Page Object Model
  - Report -> extent report base64
  
# Structure
- In base;
  - BaseStepMethod -> where ready methods are saved
  ```java
  public class BaseStepMethod {

    private static final Logger LOGGER = LogManager.getLogger(BaseStepMethod.class);

    protected AndroidDriver<AndroidElement> androidDriver = DriverThread.getDriver();
    private final WebDriverWait wait;

    public BaseStepMethod() {
        this.wait = new WebDriverWait(androidDriver, 30);

        if (androidDriver.isDeviceLocked())
            androidDriver.unlockDevice();
    }
  }
  ```
  - BaseTest -> where platform and application are set to driver before All
  ```java
  public class BaseTest {

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
  }
  ```
- In capabilities -> To this interface added Mobile Capability types
```java
public interface MobileCapabilityTypes {
  String PLATFORM_NAME = "platformName";
  String PLATFORM_VERSION = "platformVersion";
  String DEVICE_NAME = "deviceName";
  String APP_PACKAGE = "appPackage";
  String APP_ACTIVITY = "appActivity";
  String APP_WAIT_FOR_LAUNCH = "appWaitForLaunch";
  String AUTOMATION_NAME = "automationName";
  String AUTOMATOR_ANDROID = "UiAutomator2";
}
```

- In extentReports;
  - ExtentManager -> It helps us to create meta-data of the report
  - ExtentTestManager -> It includes report methods that help us to generate report details while tests run

- In listener;
  - AnnotationTransformer -> It analysis tests results
  - Listener -> Includes methods which decide test run situations and get information
  - Retry -> Runs with AnnotationTransformer and re-runs the failed tests

- In pages
  - AccountPage
  - ExplorePage
  - HomePage
  - LoginTypes

- In utilities
  - DriverThread -> Helps us to run tests with multi devices

- In test
  - Login -> tests are called and run this class
  ```java
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
    }
  ```
- testRunner
  - Runner.xml -> runs the tests
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">

<suite data-provider-thread-count="1" name="Faladdin" parallel="tests">

    <test name="Faladdin Login Test">
        <parameter name="platformVersion" value="11.0"/>
        <parameter name="deviceName" value="Redmi Note 8"/>
        <parameter name="appPackage" value="com.faladdin.app"/>
        <parameter name="appActivity" value="com.faladdin.app.ui.launcher.LauncherActivity"/>
        <parameter name="appWaitForLaunch" value="false"/>
        <classes>
            <class name="com.faladdin.test.Login"/>
        </classes>
    </test>

</suite>
```