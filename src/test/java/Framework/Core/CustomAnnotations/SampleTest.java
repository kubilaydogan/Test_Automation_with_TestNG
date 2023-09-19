package Framework.Core.CustomAnnotations;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@Listeners({ExtentITestListenerAdapter.class})
public class SampleTest {
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

    @RerunIfTestFails(4)
    @Test
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
