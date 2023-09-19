package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTest {
    @Test
    public void test1() {
        Assert.assertEquals(5, (8-3));
    }

    @Test
    public void test2() {
        int n = 15;
        Assert.assertEquals(15, n);
    }
}
