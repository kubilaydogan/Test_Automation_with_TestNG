package Framework.Utilities.Selenium;

import Framework.Core.CoreObjects;

public class AlertActions extends CoreObjects {
    public void isAlertPresent(){
        customWait.forAlertToBePresent();
    }

    public void isAlertPresent(int customTimeout){
        customWait.forAlertToBePresent(customTimeout);
    }

    public void acceptAlert(){
        isAlertPresent();
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissAlert(){
        isAlertPresent();
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getAlertText(){
        isAlertPresent();
        alert = driver.switchTo().alert();
        String tempText = alert.getText().trim();
        alert.accept();
        if(tempText.length() > 0){
            return tempText;
        }
        return null;
    }
}
