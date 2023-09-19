package Framework.Core.Listeners;

import Framework.Core.BaseTest;
import Framework.Core.ConfigurationReader;
import Framework.Core.ExtentReports.ExtentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);
    private static boolean setUpIsDone = false;

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        if (!setUpIsDone) {
            log.info("===============================================================");
            log.info("|     Test is Starting...");
            log.info("|     Running: " + context.getSuite().getName());
            log.info("|     Environment : " + ConfigurationReader.get("env"));
            log.info("|     Operating System : " + System.getProperty("os.name"));
            log.info("|     Browser: " + ConfigurationReader.get("browser"));
            log.info("|     URL: " + ConfigurationReader.get("url"));
            log.info("|     Tested by: " + ConfigurationReader.get("author"));
            log.info("===============================================================\n");
            setUpIsDone = true;
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.extentReports.flush();
        log.info("Report is ready for: " + context.getName());         // the final log
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("===============================================================");
        log.info("Test started : " + getTestMethodName(result));
        log.info("===============================================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed : " + getTestMethodName(result));
    }


    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test failed : " + getTestMethodName(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test skipped : " + getTestMethodName(result));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}