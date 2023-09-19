package Framework.Core.Listeners;

import Framework.Core.CustomAnnotations.RerunIfTestFails;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer2 implements IRetryAnalyzer {

    int counter = 0;

    @Override
    public boolean retry(ITestResult result) {

        // check if the test method had RerunIfTestFails annotation
        RerunIfTestFails annotation = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(RerunIfTestFails.class);

        // based on the value of annotation see if test needs to be rerun
        if ((annotation != null) && (counter < annotation.value())) {
            counter++;
            return true;
        }
       return false;
    }
}
