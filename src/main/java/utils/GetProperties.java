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
    private static String skipInvalid_DataProvider_properties;
    private static String skipInvalid_DataProvider_maven;
    private static boolean setUp;

    //   The first method you run should be called a method
    private static void setUpProperties() throws IOException {
        System.out.println("setUpProperties");
        setUp = true;
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        Properties prop = new Properties();
        prop.load(fileInputStream);
        url = prop.getProperty("QAUrl");
        browser_properties = prop.getProperty("browser");
        browser_maven = System.getProperty("browser");
        limit_DataProvider_properties = prop.getProperty("dataProvider");
        limit_DataProvider_maven = System.getProperty("dataProvider");
        //The first line is an Invalid check to see if the data is Invalid and there is an error.
        //Skip Invalid data when we want a clean report, set true
        skipInvalid_DataProvider_properties = prop.getProperty("skipInvalid");
        skipInvalid_DataProvider_maven = System.getProperty("skipInvalid");
    }

    public static String getBrowser() throws IOException {
        System.out.println("getBrowser");
        if (!setUp)
            setUpProperties();
        String browser = browser_maven != null ? browser_maven : browser_properties;
        return browser.toLowerCase();
    }

    public static String getUrl() {
        return url;
    }

    public synchronized static int getLimit_DataProvider() throws IOException {
        System.out.println("getLimit_DataProvider");
        if (!setUp)
            setUpProperties();
        String limit = limit_DataProvider_maven != null ?
                limit_DataProvider_maven : limit_DataProvider_properties;

        try {
            return Integer.parseInt(limit);
        } catch (NumberFormatException n) {
            System.out.println("No limit to the amount of data from DataProvider");
            return -1;
        }
    }

    public synchronized static boolean skipInvalid_DataProvider() throws IOException {
        System.out.println("getSkip_DataProvider");
        if (!setUp)
            setUpProperties();
        if (skipInvalid_DataProvider_maven == null && skipInvalid_DataProvider_properties == null)
            return false;
            String skipInvalid = skipInvalid_DataProvider_maven != null ?
                    skipInvalid_DataProvider_maven : skipInvalid_DataProvider_properties;
        if (skipInvalid.contains("tr"))
            return true;
        else return false;
    }
}
