package Framework.Pages;

import Framework.Utilities.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage extends CommonMethods {

    public String landingPageURL = "https://www.saucedemo.com/";

    public BasePage(WebDriver driver) {
        super();
    }

    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = driver -> js.executeScript("return document.readyState").toString().equals("complete");
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(expectation);
        } catch (StaleElementReferenceException | InterruptedException e) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void verifyDropdownOptions(String dropdownName, List<String> content) {
        if (dropdownName.contentEquals("State")) {
            Select select = new Select(findElement(By.id("..")));
            List<WebElement> options = select.getOptions();
            List<String> texts = options.stream().map(WebElement::getText).collect(Collectors.toList());
            Assert.assertEquals(texts, content);
        }else{
            //log.error("Could not verify the content of %s dropdown", dropdownName);
        }
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public WebElement waitFor(ExpectedConditions ec) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("")));
    }
}
