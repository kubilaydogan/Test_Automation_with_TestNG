package Framework.Core.Listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestRetryListener2 implements IAnnotationTransformer {

    // This is used for SampleTest under CustomAnnotations
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
                          Method testMethod) {
        annotation.setRetryAnalyzer(TestRetryAnalyzer2.class);
    }
}

