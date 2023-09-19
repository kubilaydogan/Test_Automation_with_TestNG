package Framework.Utilities.Selenium;

import Framework.Core.CoreObjects;
import org.openqa.selenium.WebElement;

public class JavaScriptActions extends CoreObjects {
    /**
     * executes the given JavaScript command on given web element
     *
     * @param element
     */
    public void executeJScommand(WebElement element, String command) {
        // JavascriptExecutor jse = (JavascriptExecutor) driver;
        js.executeScript(command, element);
    }

    /**
     * executes the given JavaScript command on given web element
     *
     * @param command
     */
    public void executeJScommand(String command) {
        js.executeScript(command);
    }

    public static void scrollToElement(WebElement webElement) {
        js.executeScript("arguments[0].scrollIntoView",webElement);
    }

    public static void scrollUp() {
        js.executeScript("window.scrollBy(0,-300)");
    }

    public static void scrollDown() {
        js.executeScript("window.scrollBy(0,300)");
    }

    public static void scrollRight() { js.executeScript("window.scrollBy(300,0)"); }

    public static void scrollLeft() {
        js.executeScript("window.scrollBy(-300,0)");
    }

    public static void pageUp() {
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public static void pageDown() {
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void jsclickElement(WebElement webelement) {
        js.executeScript("arguments[0].click()", webelement);
    }

    public void jsenterText(WebElement webelement, String value) {
        webelement.clear();
        js.executeScript("arguments[0].value=arguments[1]", webelement,value);
    }

    /*
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     *
     * @param element
     * @param attributeName
     * @param attributeValue
     */
    public void setAttribute(WebElement element, String attributeName, String attributeValue) {
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }
}
