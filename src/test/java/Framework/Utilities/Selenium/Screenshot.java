package Framework.Utilities.Selenium;

import Framework.Core.CoreObjects;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshot extends CoreObjects {
    public static void getScreenshot(String filename) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/" + filename + ".jpg"));
    }

    public static String captureScreenshot(String filename) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMdd_hh:mm:ss a"));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + filename + "_" + date + ".png";
        File finalDestination = new File(target);

        // save the screenshot to the path given
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    public static void main(String[] args) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh:mm:ss a"));
        System.out.println(date);       // 2023-08-29_03:46:56 PM

        String date1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMdd_hh:mm:ss a"));
        System.out.println(date1);       // 2023-08-29_03:46:56 PM
    }
}
