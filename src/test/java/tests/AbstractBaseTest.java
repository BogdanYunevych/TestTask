package tests;

import common.Config;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import webdriver.DriverFactory;
import webdriver.DriverSession;

public abstract class AbstractBaseTest {
    @BeforeMethod
    synchronized public void setUpTest() {
        if (DriverSession.getDriverSession() == null) {
            DriverSession.setDriverSession(DriverFactory.getDriverInstance());
            DriverSession.getDriverSession().get(Config.HOST_URL);
        }
    }

    @AfterSuite
    public void closeBrowsers() {
        DriverSession.closeDriverSessions();
    }
}
