/*
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


    protected boolean quit;

    @BeforeClass
    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "C:\\project\\webDriver\\chromedriver.exe");
        WebDriverManager.chromedriver().setup(); //   <dependency> at pom.xml
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new EventReporter());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(urlHome);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        quit = true;
        setCookie();
    }
   @BeforeMethod */
/*(firstTimeOnly = true)*//*

    public void goHome(){
       */
/*DataProviderExel.firstTimeOnly = true;*//*

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

    @AfterMethod*/
/* (lastTimeOnly = true)*//*

    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            TakesScreenshot takesScreenshot = driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
//            kk
            System.out.println(screenshot.getPath()+ " path");
            System.out.println(screenshot.getName()+ " path");

            moveToDir(result.getName(),screenshot.getPath(),System.getProperty("user.dir") +
                    "\\test-output\\" + screenshot.getName());
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yy HH-mm-ss");
        String date = dtf.format(LocalDateTime.now());
        try{
            // rename a file in the same directory
            String pathName = date + newName + ".png";
            Files.move(source, source.resolveSibling(pathName));

            System.out.println("look screenshot at: " + pathName);
            Reporter.log("look screenshot at: " + source.resolveSibling(pathName));
//            Reporter.log("\"<br> <img src="+System.getProperty("user.dir")+ pathName + " /> <br>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }
}
*/
