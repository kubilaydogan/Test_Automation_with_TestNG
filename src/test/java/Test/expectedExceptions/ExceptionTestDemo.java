package Test.expectedExceptions;

import org.testng.annotations.Test;

import java.io.IOException;

public class ExceptionTestDemo {

    @Test(expectedExceptions = { IOException.class })
    public void exceptionTest1() throws Exception {
        throw new IOException();        // PASS ==> bcos it was expected
    }

    @Test(expectedExceptions = { IOException.class, NullPointerException.class })
    public void exceptionTest2() throws Exception {
        throw new Exception();          // FAIL
    }

    @Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = "Pass Message test")
    public void exceptionTest3() throws Exception {
        throw new IOException("Pass Message test");     // PASS

    }

    @Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = ".* Message .*")
    public void exceptionTest4() throws Exception {
        throw new IOException("Pass Message test");     // PASS
    }

    @Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = "Error")
    public void exceptionTestThree() throws Exception {
        throw new IOException("Fail Message");          // FAIL
    }

}
