package utils;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;


public class Listeners implements ITestListener{
    @Override
    public synchronized void onStart(ITestContext context) {
        ExtentManager.getReport();
        System.out.println("onStart");

    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        if (result.getParameters().length>0) {
            DataProviderExel.useDataProvider = true;
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if (ExtentManager.methodTest.get() != null && ExtentManager.methodTest.get().getModel().getName().equals(methodName)) { }
            else {
                ExtentManager.createTest(result);
            }
            String paramName = Arrays.asList(result.getParameters()).toString();
            ExtentTest paramTest = ExtentManager.methodTest.get().createNode(paramName);
            ExtentManager.dataProviderTest.set(paramTest);
        } else {
            DataProviderExel.useDataProvider = false;

            System.out.println("**!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            ExtentManager.createTest(result);
        }
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentManager.getExtentTestOrChild(result).fail(result.getThrowable());
        try {
            System.out.println("Test failed: " + result.getName());
            ExtentManager.attachImage(result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentManager.getExtentTestOrChild(result).pass("Test passed");
    }
    @Override
    public synchronized void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ExtentManager.getExtentTestOrChild(result).skip(result.getThrowable());
    }
}