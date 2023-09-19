package Framework.Core.Listeners;

import Framework.Core.ConfigurationReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int maxRetryCount = Integer.parseInt(ConfigurationReader.get("retryCount"));

    @Override
    public boolean retry(ITestResult result) {
        if (counter < maxRetryCount) {
            System.out.println("Retry #" + counter + " for test: " + result.getMethod().getMethodName());
            counter++;
            return true;
        }
        return false;
    }

}