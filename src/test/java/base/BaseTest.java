package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import page.HomePage;
import utils.EventReporter;
import utils.GetProperties;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    private EventFiringWebDriver driver;
    protected HomePage homePage;
    private  String urlHome;

    {
        try {
            urlHome = new GetProperties().getUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private By logo = By.className("logo-base");
    private By close2 = By.cssSelector("img.btn-close");
    private By close = By.cssSelector("img._24EHh");

//    private By close2 = By.className("btn-close");
    protected boolean quit;

    @BeforeClass
    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "C:\\project\\webDriver\\chromedriver.exe");
        WebDriverManager.chromedriver().setup(); //   <dependency>
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new EventReporter());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(urlHome);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        quit = true;
        setCookie();
    }
   @BeforeMethod
    public void goHome(){
        boolean toContinue = true;
        while (toContinue) {

            if (driver.getCurrentUrl().equals(urlHome)){
                toContinue = false;
                continue;
            }
            else if (driver.getCurrentUrl().contains(urlHome)){
                driver.navigate().back();
                goHome();
            } else
                driver.get(urlHome);
            toContinue = false;
            continue;
        }
    }
   /* @BeforeMethod
    public void closeAllAlerts() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("BeforeMethod1");
        try {
            driver.findElement(logo).click();
            System.out.println("BeforeMethod3");

        } catch (Exception e) {
            System.out.println("BeforeMethod3");
            try {
                driver.findElement(close2).click();
            } catch (Exception e1) {
                System.out.println("The element of the ad was not found");
            }
            try {
                Thread.sleep(5000);
                WebElement iframeElement = driver.findElement(By.cssSelector("iframe[style=\"display: none;\"]"));
//                driver.switchTo().frame(iframeElement);
                var frameElement = driver.findElements(By.tagName("iframe"));
                System.out.println(frameElement.size() + " elements"+ "\n " + frameElement.toString());
                driver.findElement(close).click();
            } catch (Exception e2) {
                System.out.println("The element of alert frame was not found");
            }
        }

    }*/
    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            TakesScreenshot takesScreenshot = driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
//            kk
            System.out.println(screenshot.getPath()+ " path");
            System.out.println(screenshot.getName()+ " path");

            moveToDir(result.getName(),screenshot.getPath(),
                    "src\\main\\resources\\screenshots\\" + screenshot.getName());
//            kk
    /*        try {
                Files.move(screenshot, new File("src\\main\\resources\\screenshots\\" +
                        result.getName() +
                        ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
    @AfterClass
    public void tearDown(){
        if (quit)
            driver.quit();
    }
    public WindowManager getWindowManager(){
        return new WindowManager(driver);
    }

    private void setCookie(){
        driver.manage().deleteAllCookies();
    }


    private static void moveToDir(String name, String srcPlsFile, String newDirPlsfile) {

        Path result = null;
        try {
            result = Files.move(Paths.get(srcPlsFile), Paths.get(newDirPlsfile));
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if(result != null) {
            System.out.println("screenshot moved successfully.");
            rename(Paths.get(newDirPlsfile),name);
        }else{
            System.out.println("File movement failed and found: " + srcPlsFile);

        }

    }

    private static void rename(Path source, String newName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" dd_MM_yy HH-mm-ss");
        String date = dtf.format(LocalDateTime.now());
        try{
            // rename a file in the same directory
            String pathName = newName + date + ".png";
            Files.move(source, source.resolveSibling(pathName));

            Reporter.log("look screenshot at: " + source.resolveSibling(pathName));
//            Reporter.log("\"<br> <img src="+System.getProperty("user.dir")+ pathName + " /> <br>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
