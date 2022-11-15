package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listeners implements ITestListener{

    public synchronized void onStart(ITestContext context) {
        ExtentManager.getReport();
        System.out.println("onStart");

    }

    @Override
    public void onTestStart(ITestResult result) {
//        result.getParameters().length>0 ? "length>0":"2length<0";

//        ExtentManager.log(p);
        if (DataProviderExel.firstTimeOnly)
            ExtentManager.createExtentTest(result.getName(),result.getTestName());
        else
            ExtentManager.createNode(result.getParameters().toString());
       /* System.out.println("--------- Executing :- " + getSimpleMethodName(result) + " ---------");
        ExtentTestManager.createTest(result.getName(),result.getMethod().getDescription());
        ExtentTestManager.setCategoryName(getSimpleClassName(result));*/
    }

    public synchronized void onTestFailure(ITestResult result) {
        ExtentManager.getExtentTest_ThreadLocal().fail(result.getThrowable());
        try {
            System.out.println("Test failed: " + result.getName());
            ExtentManager.attachImage(result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }

}