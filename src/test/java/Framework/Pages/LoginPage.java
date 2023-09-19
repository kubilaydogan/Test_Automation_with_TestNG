package Framework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertTrue;


public class LoginPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    final By usernameField = By.id("user-name");
    final By passwordField = By.id("password");
    final By loginButton = By.id("login-button");
    final By menuButton = By.cssSelector(".bm-burger-button");
    final By closeMenuButton = By.cssSelector("#react-burger-cross-btn");
    final By resetAppStateButton = By.id("reset_sidebar_link");


    public LoginPage openLandingPage() {
        driver.get(landingPageURL);
        log.info("User in on landing page");
        return this;
    }

    public LoginPage login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        log.info("Login attempt by {}", username);
        return this;
    }

    public LoginPage resetAppState() {
        clickElement(menuButton);
        clickElement(resetAppStateButton);
        clickElement(closeMenuButton);
        return this;
    }

    public LoginPage verifySuccessfulLogin() {
        try {
            WebElement header = driver.findElement(By.xpath("//span[@class='title']"));
            assertTrue(header.getText().contentEquals("Products"));
            log.info("Successful login");
        } catch (Exception e) {
            log.error("TEST FAILED :" + e);
        }
        return this;
    }

    public LoginPage verifyFailureMessage(String message) {
        assertTrue(driver.findElement(By.cssSelector("h3[data-test=error]")).getText().contains(message));
        log.info("Page displays failure message as expected!");
        return this;
    }

    public LoginPage verifyTitleIs(String expectedTitle) {
        verifyTitle(expectedTitle);
        log.info("Title verified: {}", expectedTitle);
        return this;
    }

}