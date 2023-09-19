package Framework.Utilities.Selenium;

import Framework.Core.CoreObjects;
import org.openqa.selenium.NoSuchWindowException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowActions extends CoreObjects {
    public void switchToParentWindow() {
        Set<String> parentWindowHandle = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(parentWindowHandle);
        if (windowList.size() > 0) {
            driver.switchTo().window(windowList.get(0));
        } else {
            throw new NoSuchWindowException("Parent window does not exist");
        }
    }

    public void switchToWindow(int windowIndex) {
        Set<String> windowHandle = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(windowHandle);
        if (windowIndex < windowHandle.size()) {
            driver.switchTo().window(windowList.get(windowIndex));
        } else {
            throw new NoSuchWindowException("Window does not exist with index: " + windowIndex);
        }
    }

    /*
     * Switches to new window by the exact title.
     * Returns to original window if target title not found
     * @param targetTitle
     */
    public static void switchToWindow(String targetTitle) {
        String origin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);
    }

    public void closeWindow(int windowindex) {
        Set<String> windowHandle = driver.getWindowHandles();
        List<String> windowList = new ArrayList<String>(windowHandle);
        if (windowindex < windowHandle.size()) {
            driver.switchTo().window(windowList.get(windowindex)).close();
        } else {
            throw new NoSuchWindowException("Window with index: " + windowindex + " does not exist ");
        }
    }

    public void closeAllChildWindows() {
        Set<String> windowHandle = driver.getWindowHandles();
        List<String> windowList = new ArrayList<String>(windowHandle);
        for (int i = 1; i < windowList.size(); i++) {
            driver.switchTo().window(windowList.get(i)).close();
        }
    }

    public int getWindowCount() {
        Set<String> windowHandle = driver.getWindowHandles();
        List<String> windowList = new ArrayList<String>(windowHandle);
        return windowList.size();
    }

}
