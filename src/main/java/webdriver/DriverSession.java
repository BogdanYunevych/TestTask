package webdriver;

import org.openqa.selenium.WebDriver;

import java.util.Hashtable;
import java.util.Map;

import static java.lang.Thread.currentThread;

public class DriverSession {
    private static Map<Long, WebDriver> sessions = new Hashtable<Long, WebDriver>();

    public static WebDriver getDriverSession() {
        return sessions.get(currentThread().getId());
    }

    public static void setDriverSession(WebDriver driver) {
        sessions.put(currentThread().getId(), driver);
    }

    public static void closeDriverSessions() {
        for (WebDriver driver : sessions.values()) {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }
        sessions.clear();
    }
}