package Test.usingParameters;

import Framework.Core.BaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({ExtentITestListenerAdapter.class})
public class ParameterTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(ParameterTest.class);

    @Test(description = "Title verification", priority = 1)
    public void titleTest() {
        driver.get("https://nightwatchjs.org");
        assertTrue(driver.getTitle().contains("Nightwatch"));
        log.info("Title verified: {}", driver.getTitle());
    }

    // this test can run only with the xml file

    @Parameters({ "username", "password" })
    @Test
    public void loginToTheWebsite(String username, String password) {
        driver.get("https://www.saucedemo.com/");

        By userName = By.id("user-name");
        driver.findElement(userName).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        String expectedURL = "https://www.saucedemo.com/inventory.html";
        try {
            wait.until(ExpectedConditions.titleContains("Swag Labs"));
            assertEquals(driver.getCurrentUrl(), expectedURL);
            log.info("{} has logged in", username);
        } catch (NoSuchElementException e) {
            log.error("Login failed for {} !!", username);
        }
    }

}
