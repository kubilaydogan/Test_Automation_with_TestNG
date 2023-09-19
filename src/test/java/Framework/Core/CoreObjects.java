package Framework.Core;

import Framework.Pages.LoginPage;
import Framework.Utilities.CommonMethods;
import Framework.Utilities.Selenium.CustomWait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoreObjects extends BaseDataProvider{

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static CustomWait customWait;
    public static JavascriptExecutor js;
    public static Actions actions;
    public static Alert alert;

    // Page objects
    public LoginPage loginPage;

    //Utilities
    public CommonMethods commonMethods;


//    public static CustomWait Wait;
//    public static WindowActions WindowHandler;
//    public static AlertActions AlertHandler;
//    public static KeyboardActions KeyboardHandler;

}
