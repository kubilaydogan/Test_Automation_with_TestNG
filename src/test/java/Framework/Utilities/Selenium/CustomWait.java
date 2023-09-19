package Framework.Utilities.Selenium;

import Framework.Core.Constants;
import Framework.Core.CoreObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /*
        visibilityOf is used for checking that an element you have already found is visible
        visibilityOfElementLocated means find that element first then check that element is visible or not
    */
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class CustomWait extends CoreObjects {

    public void waitForElementVisibility(WebElement webelement) {
        wait = new WebDriverWait(driver, Constants.OBJECT_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOf(webelement));
    }

    public void waitForElementVisibility(WebElement webelement, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(webelement));
    }

    public void waitForElementInVisibility(By locator) {
        wait = new WebDriverWait(driver, Constants.OBJECT_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementInVisibility(By locator, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementClickablity(WebElement webelement) {
        wait = new WebDriverWait(driver, Constants.OBJECT_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.elementToBeClickable(webelement));
    }

    public void forAlertToBePresent() {
        wait = new WebDriverWait(driver, Constants.OBJECT_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void forAlertToBePresent(int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void forPageTitle(String pagetitle) {
        wait = new WebDriverWait(driver, Constants.OBJECT_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.titleIs(pagetitle));
    }

    public void forPageTitle(String pagetitle, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.titleIs(pagetitle));
    }

    public void forPageToLoad(WebElement webelement) {
        wait = new WebDriverWait(driver, Constants.PAGE_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOf(webelement));
    }

    public void forPageToLoad(WebElement webelement, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(webelement));
    }

    public void forPageToLoad(String pageTitle) {
        wait = new WebDriverWait(driver, Constants.PAGE_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }

    public void forPageToLoad(String pageTitle, int customTimeout) {
        forPageTitle(pageTitle,customTimeout);
    }

    public void forElementToBeEnabled(WebElement webelement) {
        forElementToBeEnabled(webelement, 20);
    }

    public void forElementToBeEnabled(WebElement webelement, int customTimeout) {
        int count = 0;
        boolean flag = false;
        do {
            if(webelement.isEnabled()) {
                flag = true;
                break;
            } else {
                count++;
                wait(1);
            }
        } while (count < customTimeout);

        if(!flag) {
            throw new ElementNotInteractableException("Element disabled");
        }
    }

    public void forElementToBeVisibleAndEnabled(WebElement webelement) {
        waitForElementVisibility(webelement);
        forElementToBeEnabled(webelement);
    }

    public void forElementToBeVisibleAndEnabled(WebElement webelement, int customTimeout) {
        waitForElementVisibility(webelement,customTimeout);
        forElementToBeEnabled(webelement,customTimeout);
    }

    public void wait(int seconds) {
        seconds = seconds * 1000;
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
