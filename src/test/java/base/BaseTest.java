package base;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import test.LogInTest;
import utils.GetProperties;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    public BaseTest() throws IOException {
        super();
    }

    @BeforeMethod(firstTimeOnly = true)
    public void setup(ITestContext context) throws IOException {
        WebDriverInstance.getDriverThreadLocal().get(GetProperties.getUrl());
        LogInTest.notYet_beginning_LogInTest = true;
    }

    @AfterMethod(lastTimeOnly = true)
    public void tearDown() {
		WebDriverInstance.cleanupDriver();
    }
}
