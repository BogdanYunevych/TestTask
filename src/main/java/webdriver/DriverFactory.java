package webdriver;

import common.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.ClassLoader.getSystemResource;

public class DriverFactory {
    public static WebDriver getDriverInstance() {
        WebDriver driver;
        switch (Config.BROWSER.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", getSystemResource("chromedriver.exe").getPath());
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Incorrect Browser type - " + Config.BROWSER);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
