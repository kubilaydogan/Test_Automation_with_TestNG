package Framework.Utilities;

import Framework.Core.CoreObjects;
import Framework.Core.Enums.ClickType;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CommonMethods extends CoreObjects {
    private static final Logger log = LoggerFactory.getLogger(CommonMethods.class);

    // ~~~~~~~~~~~~~~~~~~~~~~~ CLICK ~~~~~~~~~~~~~~~~~~~~~~~

    public void clickElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void clickWhenVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public void clickWhenVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void click(WebElement element, ClickType clickType) {
        switch (clickType) {
            case DEFAULT:
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                break;
            case ACTIONS:
                actions.moveToElement(element).click().perform();
                break;
            case JSEXECUTOR:
                js.executeScript("arguments[0].scrollIntoView();", element);
                js.executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);             //  js already defined
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public void doubleClick(WebElement element) {
        // new Actions(driver).doubleClick(element).perform();
        actions.doubleClick(element).perform();
    }

    /**
     * Checks or unchecks given checkbox
     *
     * @param element
     * @param check
     */
    public void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~ String / Text ~~~~~~~~~~~~~~~~~~~~~~~

    public void verifyTitle(String expectedTitle) {
        assertTrue(driver.getTitle().contains(expectedTitle));
    }

    public void writeText(By by, String text) {
        waitForVisibility(by).sendKeys(text);
    }

    public String readText(By by) {
        return waitForVisibility(by).getText();
    }

    public static boolean isNotEmpty(String str) {
        return null != str && !"".equals(str);
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~ WAIT ~~~~~~~~~~~~~~~~~~~~~~~

    public WebElement waitForVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement getLocatedElement(By by) {
        return wait.until(d -> driver.findElement(by));
    }

    public WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for element matching the locator to be clickable
     *
     * @param locator
     * @param timeout
     * @return
     */
    public WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForPresenceOfElement(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForsStaleness(By by) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(by)));
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~ WEB ELEMENT ~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Highlighs an element by changing its background and border color
     *
     * @param element
     */
    public void highlight(WebElement element) {
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        waitFor(1);
        js.executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    /**
     * Moves the mouse to given element
     *
     * @param element on which to hover
     */
    public static void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    /**
     * Waits for element to be not stale
     *
     * @param element
     */
    public void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            try {
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st) {
                y++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }


    /**
     * Verifies whether the element matching the provided locator is displayed on page
     *
     * @param by
     * @throws AssertionError if the element matching the provided locator is not found or not displayed
     */
    public static void verifyElementDisplayed(By by) {
        try {
            AssertJUnit.assertTrue("Element not visible: " + by, driver.findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            log.error("" + e);
            e.printStackTrace();
            Assert.fail("Element not found: " + by);
        }
    }

    /**
     * Verifies whether the element matching the provided locator is NOT displayed on page
     *
     * @param by
     * @throws AssertionError the element matching the provided locator is displayed
     */
    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse(driver.findElement(by).isDisplayed(), "Element should not be visible: " + by);
        } catch (NoSuchElementException e) {
            log.error("" + e);
            e.printStackTrace();
        }
    }

    /**
     * Verifies whether the element is displayed on page
     *
     * @param element
     * @throws AssertionError if the element is not found or not displayed
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            AssertJUnit.assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error(":::Element not found:::");
            Assert.fail("Element not found: " + element);
        }
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public void waitForPageToLoad(int timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~ COLLECTION ~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * return a list of string from a list of elements
     * text
     *
     * @param list of webelements
     * @return
     */
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> texts = new ArrayList<>();
        for (WebElement each : list) {
            if (!each.getText().isEmpty()) {
                texts.add(each.getText());
            }
        }
        return texts;
    }

    public static boolean isSorted(List<String> list) {
        if (list.isEmpty() || list.size() == 1) {
            return true;
        }

        Iterator<String> iter = list.iterator();
        String current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}


