package base;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.GetProperties;


import java.io.IOException;
import java.time.Duration;

public class WebDriverInstance {
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static WebDriver driverGet;

    public static WebDriver getDriverThreadLocal() {
        if (driverThreadLocal.get() == null) {
            try {
                driverThreadLocal.set(createDriver());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driverGet = driverThreadLocal.get();
        System.out.println("System.currentTimeMillis()" +System.currentTimeMillis());
        System.out.println("driver.toString() "+driverGet.toString());
        return driverThreadLocal.get();
    }

    public static WebDriver createDriver() throws IOException {
        WebDriver driver = null;



        switch (GetProperties.getBrowser()) {
            case "firefox"-> {
//                WebDriverManager.firefoxdriver();
                driver = new FirefoxDriver();
            }
            case "edge"-> {
//                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default-> {
//                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(

                );
                driver = new ChromeDriver(chromeOptions);
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    public static void cleanupDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
        System.out.println("cleanupDriver");
    }

}
