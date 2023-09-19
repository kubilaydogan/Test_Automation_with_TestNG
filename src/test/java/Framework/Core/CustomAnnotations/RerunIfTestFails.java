package Framework.Core.CustomAnnotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RerunIfTestFails {
    int value() default 0;
}

/*
    SAMPLE OF USAGE:

    @RerunIfTestFails(4)        ==> Specify how many times you want to retry the test if it fails
    @Test
    public void loginToTheWebsite() {
        // your code
    }


    Note: It is used together with:
      - TestRetryAnalyzer2
      - TestRetryListener2
 */
