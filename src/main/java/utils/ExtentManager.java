package utils;

import base.WebDriverInstance;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    public static ExtentReports extentReport;
    public static String dirPathToReport;
    public static ThreadLocal<ExtentTest> extentTest_ThreadLocal = new ThreadLocal<>();

    public ExtentManager() {
        super();
    }

    public static ExtentReports getReport() {
        if(extentReport == null) {
            setupExtentReport("KSP test");
        }
        return extentReport;
    }

    public static ExtentReports setupExtentReport(String testName) {
        extentReport = new ExtentReports();
        createDirPathToReport(testName);
        ExtentSparkReporter spark = new ExtentSparkReporter(dirPathToReport
                 + "ExtentReports.html");
        extentReport.attachReporter(spark);

        extentReport.setSystemInfo("Tester", "yosef wollman");
        spark.config().setReportName("Regression Test");
        spark.config().setDocumentTitle("Test Results");
        spark.config().setTheme(Theme.DARK);

        return extentReport;

    }

    public static String createDirPathToReport(String testName) {
        String date = timestamp();
        dirPathToReport = System.getProperty("user.dir") + "/report/" +"/"+testName + "_" + date+"/";
        return dirPathToReport;
    }
    public synchronized static ExtentTest getExtentTest_ThreadLocal() {
        return extentTest_ThreadLocal.get();
    }

    public synchronized static ExtentTest createExtentTest(String name, String description) {
        System.out.println("createExtentTest");

        ExtentTest test = extentReport.createTest(name, "description");

        extentTest_ThreadLocal.set(test);
        return getExtentTest_ThreadLocal();
    }

    public synchronized static void createNode(String param) {
        getExtentTest_ThreadLocal().createNode(param);
    }
    public synchronized static void log(String message) {
        System.out.println("log("+ message +")");
        getExtentTest_ThreadLocal().info(message);
    }

    public synchronized static void pass(String message) {
        System.out.println("pass("+ message +")");
        getExtentTest_ThreadLocal().pass(message);
    }

    public synchronized static void fail(String message) {
        System.out.println("fail("+ message +")");

        getExtentTest_ThreadLocal().fail(message);
    }

    public synchronized static void attachImage(String name) {
        System.out.println("attachImage("+ name +")");
        WebDriver driver = WebDriverInstance.getDriverThreadLocal();
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String nameFileScreenShot = timestamp() + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(dirPathToReport + nameFileScreenShot));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getExtentTest_ThreadLocal().addScreenCaptureFromPath(nameFileScreenShot);

    }

    public static void flushReport() {
        extentReport.flush();
    }

    public static String timestamp() {
        return new SimpleDateFormat("d-MMM-YY HH-mm-ss").format(new Date());
    }

}
/*
public class ExtentManager {

    public static ExtentReports extentReport;
    public static String extentReportPrefix;
    public static ThreadLocal<ExtentTest> extentTest_ThreadLocal = new ThreadLocal<>();


    public static ExtentReports getReport() {
        if(extentReport == null) {
            setupExtentReport("Live Project 1");
        }
        return extentReport;
    }

    public static ExtentReports setupExtentReport(String testName) {
        extentReport = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/report/" +
                extentReportsPrefix_Name(testName) + ".html");
        extentReport.attachReporter();

        extentReport.setSystemInfo("Tester", "John Smith");
        spark.config().setReportName("Regression Test");
        spark.config().setDocumentTitle("Test Results");
        spark.config().setTheme(Theme.STANDARD);

        return extentReport;

    }

    public static String extentReportsPrefix_Name (String testName) {
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        extentReportPrefix = testName + "_" + date;
        return extentReportPrefix;
    }

    public static void flushReport() {
        extentReport.flush();
    }

    public synchronized static ExtentTest getTest() {
        return extentTest_ThreadLocal.get();
    }

    public synchronized static ExtentTest createTest(String name, String description) {
        ExtentTest test = extentReport.createTest(name, description);
        extentTest_ThreadLocal.set(test);
        return getTest();
    }

    public synchronized static void log(String message) {
        getTest().info(message);
    }

    public synchronized static void pass(String message) {
        getTest().pass(message);
    }

    public synchronized static void fail(String message) {
        getTest().fail(message);
    }

    public synchronized static void attachImage() {
        System.out.println("attachImage()");
        File srcFile = ((TakesScreenshot) WebDriverInstance.getDriverThreadLocal()).getScreenshotAs(OutputType.FILE);

        String screenShot_DestinationPath = System.getProperty("user.dir") + "\\target\\screenshots\\" + timestamp() + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(screenShot_DestinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getTest().addScreenCaptureFromPath(screenShot_DestinationPath);
    }
    public static String timestamp() {
        return new SimpleDateFormat("d-MMM-YY HH-mm-ss").format(new Date());
    }

}
*/
