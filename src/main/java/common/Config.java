package common;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

@NoArgsConstructor
public class Config {
    public static final String BROWSER;
    public static final String HOST_URL;
    public static final String LOGIN;
    public static final String PASSWORD;
    public static final int TIMEOUT = 10;

    private static Properties properties = new Properties();

    static {
        try {
            URL url = ClassLoader.getSystemResource("config.properties");
            properties.load(new FileInputStream(new File(url.getFile())));
        } catch (Exception e) {
            throw new RuntimeException("Unable to read properties", e);
        }

        // get browser type
        String browser = System.getProperty("browser"); // mvn command line
        if (browser == null) browser = System.getenv("browser"); // from configuration
        if (browser == null) browser = properties.getProperty("browser"); // from properties
        if (browser == null) browser = "chrome";
        BROWSER = browser;

        System.out.println(String.format("Running tests under %s browser", browser.toUpperCase()));

        HOST_URL = properties.getProperty("host");
        LOGIN = properties.getProperty("login");
        PASSWORD = properties.getProperty("password");
    }
}