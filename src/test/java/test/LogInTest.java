package test;

import org.testng.annotations.Listeners;
import utils.DataProviderExel;
import utils.ExtentManager;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.PersonalPage;

import java.io.IOException;

@Listeners(utils.Listeners.class)

public class LogInTest extends BaseTest {
    private final String messageErroer_passEnter = "The login test was successful in entering the personal area";
    private final String messageErroer_failedEnter = "The login test failed to enter the personal area";
    public static boolean notYet_beginning_LogInTest;

    public LogInTest() throws IOException {
        super(); //parent without constructor
    }


    private PersonalPage beginningLogInTest(PersonalPage personalPage) throws IOException {
        new HomePage().clickOnPersonalIcon();
        ExtentManager.pass("Reached the personal area page");
        personalPage.click_LogIn();
        ExtentManager.pass("Reached the login pane");
        notYet_beginning_LogInTest = false;
        return personalPage;
    }

    @Test(groups = { "smoke", "regression" } )
    public void login_valid_enterAll() throws IOException {
        ExtentManager.log("Starting login_enterAllValid()..." );
        PersonalPage personalPage = new PersonalPage();
        personalPage = beginningLogInTest(personalPage);

        personalPage.loginPane_enter_MailAdress("abc123@ksp.com");
        personalPage.loginPane_enter_password("abc123ABC123");
        Assert.assertEquals( personalPage.checkErrors(),0,"An error was found");
        personalPage.loginClickSubmit();
        ExtentManager.passChild("Have successfully entered customer details valid and click Submit");
        personalPage.log_OutAND_printCoockiesChenge();

        // using an assertion to make sure the total amount is what we are expecting
        try {
            Assert.assertTrue(personalPage.checkSuccessful_login());
            ExtentManager.passChild(messageErroer_passEnter);
        } catch (AssertionError a) {
            Assert.fail(messageErroer_failedEnter);
            ExtentManager.failChild(messageErroer_failedEnter);
        }
    }

    @Test(dataProviderClass = DataProviderExel.class, dataProvider = "col-provider")
    public void login_valid_enterOnlyMailAdress(String mailAddressValid) throws IOException {
        PersonalPage personalPage = new PersonalPage();
        if (notYet_beginning_LogInTest){
            System.out.println("start of firstTimeOnly");
            personalPage = beginningLogInTest(personalPage);
            ExtentManager.log("login_enterOnlyMailAdress()..." );
        } else{
            System.out.println("firstTimeOnly = false and skip beginningOfThe_LogInTest()");
            ExtentManager.logChild("firstTimeOnly = false and skip beginningOfThe_LogInTest()");
        }
        personalPage.loginPane_enter_MailAdress(mailAddressValid);
        try {
            // using an assertion to make sure the total amount is what we are expecting
            Assert.assertEquals(personalPage.loginPane_getText_Error_MailAdress(),
                    "No error message");
            ExtentManager.passChild("When entering a valid email address, an error message does not appear");
        } catch (AssertionError a) {
            Assert.fail("The email address is valid, an error message should not appear, but there is");
//            ExtentManager.failChild("The email address is valid, an error message should not appear, but there is");
        }
    }
    @Test(dataProviderClass = DataProviderExel.class, dataProvider = "col-provider")
    public void login_invalid_enterOnlyMailAdress(String mailAddressValid) throws IOException {
        PersonalPage personalPage = new PersonalPage();
        if (notYet_beginning_LogInTest){
            System.out.println("start of firstTimeOnly");
            personalPage = beginningLogInTest(personalPage);
            ExtentManager.log("login_enterOnlyMailAdress()..." );
        } else{
            System.out.println("firstTimeOnly = false and skip beginningOfThe_LogInTest()");
            ExtentManager.logChild("firstTimeOnly = false and skip beginningOfThe_LogInTest()");
        }
        personalPage.loginPane_enter_MailAdress(mailAddressValid);
        ExtentManager.logChild("loginPane_enter_MailAdress("+mailAddressValid+")");
        try {
            // using an assertion to make sure the total amount is what we are expecting
            Assert.assertEquals(personalPage.loginPane_getText_Error_MailAdress(),
                    "No error message");
            ExtentManager.passChild("When entering a valid email address, an error message does not appear");
        } catch (AssertionError a) {
            Assert.fail("The email address is valid, an error message should not appear, but there is");
            ExtentManager.failChild("The email address is valid, an error message should not appear, but there is");
        }
    }




}
