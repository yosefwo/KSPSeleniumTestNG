package utils;

import base.WebDriverInstance;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ExtentManager {
    public static ExtentReports extentReport;
    public static String dirPathToReport;
    public static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();


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

        String date = timestamp();
        String fileName = dirPathToReport + "ExtentReports.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
//
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        extentReport.attachReporter(htmlReporter);
        extentReport.setSystemInfo("Release No", "22");
        extentReport.setSystemInfo("Environment", "QA");
        extentReport.setSystemInfo("Tester", "yosef wollman");
        htmlReporter.config().setCSS(".r-img {width : 50%;}");
        htmlReporter.config().setReportName("Regression Test");
        htmlReporter.config().setDocumentTitle("Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        return extentReport;

    }

    public static String createDirPathToReport(String testName) {
        String date = timestamp();
        dirPathToReport = System.getProperty("user.dir") + "/report/" +"/"+testName + "_" + date+"/";
        return dirPathToReport;
    }
    public synchronized static ExtentTest getMethodTest() {
        return methodTest.get();
    }
    public synchronized static ExtentTest getDataProviderTest() {
        System.out.println("getDataProviderTest!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (dataProviderTest.get() == null){
            System.out.println("dataProviderTest.get() == null");

            return getMethodTest();
        }
        return dataProviderTest.get();
    }

    public synchronized static void createTest(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println("createTest()");
        ExtentTest test = extentReport.createTest(methodName);
        methodTest.set(test);

        String[] groups = result.getMethod().getGroups();
        if (groups.length > 0) {
            Arrays.asList(groups)
                    .forEach(x -> methodTest.get().assignCategory(x));
        }
    }


    public synchronized static void createNode(String param) {
        getMethodTest().createNode(param);
    }

    public synchronized static void log(String message) {
        System.out.println("log("+ message +")");
        getMethodTest().info(message);
    }
    public synchronized static void logChild(String message) {
        System.out.println("log("+ message +")");
        getDataProviderTest().info(message);
    }

    public synchronized static void pass(String message) {
        System.out.println("pass("+ message +")");
        getMethodTest().pass(message);
    }
    public synchronized static void passChild(String message) {
        if(!DataProviderExel.useDataProvider) {
            pass(message);
            System.out.println("שים לב אפשר להתשמ באותה שיטה ואם זה לא ילד ישלח לאבא ההודעה הזו:"+ message);
        }
        else {
            System.out.println("pass("+ message +")");
            getDataProviderTest().pass(message);
        }
    }
    public synchronized static void fail(String message) {
        System.out.println("fail("+ message +")");
        getMethodTest().fail(message);
    }
    public synchronized static void failChild(String message) {
        System.out.println("fail("+ message +")");
        getDataProviderTest().fail(message);
    }

    public synchronized static void attachImage(String name) throws IOException {
        System.out.println("attachImage("+ name +") " + timestamp());
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
        System.out.println("getDataProviderTest().addScreenCaptureFromPath(nameFileScreenShot);");
        if(!DataProviderExel.useDataProvider) {
            methodTest.get().createNode("See screen shot attached").log(Status.INFO,"screenShot").addScreenCaptureFromPath(nameFileScreenShot);
//
//            System.out.println("(dataProviderTest.get() == null");
//            System.out.println(1);
//            methodTest.get().createNode("See screen shot attached1").addScreenCaptureFromPath(nameFileScreenShot);
//            System.out.println(2);
//            methodTest.get().createNode("See screen shot attached2").log(Status.INFO,"screenShot2").addScreenCaptureFromPath(nameFileScreenShot);
//            System.out.println(3);
//            methodTest.get().createNode("See screen shot attached3").log(Status.FAIL,"screenShot3").addScreenCaptureFromPath(nameFileScreenShot);
//            System.out.println(1);
//            methodTest.get().createNode("See screen shot attached").fail("screenShot").addScreenCaptureFromPath(nameFileScreenShot);
        }
        else{
//            System.out.println("(dataProviderTest.get() == null++++ else");
            getDataProviderTest().addScreenCaptureFromPath(nameFileScreenShot);
//            methodTest.get().createNode("See screen shot attached2").log(Status.INFO,"screenShot24").addScreenCaptureFromPath(nameFileScreenShot);

        }
    }

    public static void flushReport() {
        extentReport.flush();
    }

    public static ExtentTest getExtentTestOrChild(ITestResult result) {
        ExtentTest t = result.getParameters() != null && result.getParameters().length > 0
                ? dataProviderTest.get()
                : methodTest.get();
        return t;
    }
    public static String timestamp() {
        return new SimpleDateFormat("d-MMM-yy HH-mm-ss").format(new Date());
    }


}
