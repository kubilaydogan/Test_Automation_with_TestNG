package Framework.Core.CustomAnnotations.Alternatif;

import Framework.Core.CustomAnnotations.SampleTest;
import Framework.Core.Listeners.TestRetryAnalyzer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class RerunWithoutCustomAnnotation {
    private static final Logger log = LoggerFactory.getLogger(SampleTest.class);
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.firefoxdriver().setup();
        log.info("Test is starting!!!");
    }

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        log.info("Opening website");
        Thread.sleep(2000);
    }

    @Test(retryAnalyzer = TestRetryAnalyzer.class)
    public void loginToTheWebsite() {
        String username = "standard_user";
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.titleContains("Swag Labs"));
        assertEquals(driver.getTitle(), "Hello", "Title verification failed!!!");

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
