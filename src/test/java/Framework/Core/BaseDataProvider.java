package Framework.Core;

import org.testng.annotations.DataProvider;

public class BaseDataProvider {
    @DataProvider(name = "valid-authentication")
    public static Object[][] credentials() {
        return new Object[][] { { "standard_user", "secret_sauce" }};
    }

    @DataProvider(name = "invalid-authentication")
    public static Object[][] credentials2() {
        return new Object[][] {{ "locked_out_user", "secret_sauce", "Sorry, this user has been locked out." }};
    }
}
