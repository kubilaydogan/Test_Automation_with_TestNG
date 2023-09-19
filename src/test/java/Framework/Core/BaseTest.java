package Framework.Core;

import Framework.Pages.LoginPage;
import Framework.Utilities.CommonMethods;
import Framework.Utilities.Selenium.CustomWait;
import Framework.Utilities.Selenium.Screenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest extends CommonMethods {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeSuite() {
    }

    @BeforeClass
    public void start() {
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void initilizeDriver(@Optional String browser, Method method) {
        if (driver == null) {
            browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigurationReader.get("browser");
        }
        log.info("============ TEST STARTED: " + method.getName() + "============");
        driver = Driver.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        initObjects();
    }

    @AfterMethod
    public void quit(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Screenshot.captureScreenshot(result.getName());
        }
        System.out.println("Test executed: " + result.getName());
        Driver.closeDriver();
    }

    @AfterClass
    public void afterClass() {
    }

    @AfterSuite
    public void afterSuite() {
    }

    public void initObjects() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        customWait = new CustomWait();
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        loginPage = new LoginPage(driver);
    }
}
