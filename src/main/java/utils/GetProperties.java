package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
    private final String url;
    private final String browser_properties;

    public String getUrl() {
        return url;
    }

    public String getBrowser_properties() {
        return browser_properties;
    }

    public String getBrowser_maven() {
        return browser_maven;
    }

    private final String browser_maven;

    public GetProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\global.properties");
        Properties prop = new Properties();
        prop.load(fileInputStream);
        url = prop.getProperty("QAUrl");
        browser_properties = prop.getProperty("browser");
        browser_maven = System.getProperty("browser");
    }

}
