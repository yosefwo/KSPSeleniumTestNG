package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
    private static String url;
    private static String browser_properties;
    private static String browser_maven;
    private static String limit_DataProvider_properties;
    private static String limit_DataProvider_maven;

//   The first method you run should be called a method
    private static void setUpProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        Properties prop = new Properties();
        prop.load(fileInputStream);
        url = prop.getProperty("QAUrl");
        browser_properties = prop.getProperty("browser");
        browser_maven = System.getProperty("browser");
        limit_DataProvider_properties = prop.getProperty("dataProvider");
        limit_DataProvider_maven = System.getProperty("dataProvider");
    }

    public static String getBrowser() throws IOException {
        setUpProperties(); // need to run first
        String browser = browser_maven != null ? browser_maven : browser_properties;
        return browser.toLowerCase();
    }

    public static String getUrl() {
        return url;
    }

    public static int getLimit_DataProvider() throws IOException {
        String DataProvider = limit_DataProvider_maven != null ?
                limit_DataProvider_maven : limit_DataProvider_properties;
        try {
            return Integer.parseInt(DataProvider);
        } catch (NumberFormatException n){
            System.out.println("No limit to the amount of data from DataProvider");
            return -1;
        }
    }
}
