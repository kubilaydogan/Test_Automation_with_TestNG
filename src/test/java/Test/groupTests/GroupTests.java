package Test.groupTests;

import org.testng.annotations.Test;

public class GroupTests {
    @Test(groups = { "Group A" })
    public void testMethodAOne() {
        System.out.println("Test method A-1");
    }

    @Test(groups = { "Group A" })
    public void testMethodATwo() {
        System.out.println("Test method A-2");
    }

    @Test(groups = { "Group B"})
    public void testMethodBOne() {
        System.out.println("Test method B-1");
    }

    @Test(groups = { "Group B", "Group C" })
    public void testMethodBTwo() {
        System.out.println("Test method B-2");
    }

    @Test(groups = { "Group C" })
    public void testMethodThree() {
        System.out.println("Test method 3 is exluded");
    }

    @Test(groups = { "Group D" })
    public void testMethodFour() {
        System.out.println("Test method 4 is exluded");
    }
}
