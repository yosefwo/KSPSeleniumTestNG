package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.personalPage;

public class RegisterTest extends BaseTest {
    @Test
    public void register_enterAllValid() throws InterruptedException {
        personalPage personalPage = homePage.clickOnPersonalIcon();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.paneRegister_enter_MailAdress("abc123@ksp.com");
        personalPage.paneRegister_enter_PhoneNumber("0521234567");
        personalPage.paneRegister_enter_firstName("abc123");
        personalPage.paneRegister_enter_last_name("abc123");
        personalPage.paneRegister_enter_password("abc123ABC123");
        personalPage.paneRegister_enter_password2("abc123ABC123");
        Assert.assertEquals( personalPage.checkErrors(),0,"An error was found");
    }
    @Test
    public void register_enterOnlyMailAdress_submit() throws InterruptedException {

        personalPage personalPage = homePage.clickOnPersonalIcon();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.paneRegister_enter_MailAdress("abc123@ksp.com");
        personalPage.clickSubmit();
        Assert.assertEquals(personalPage.getText_Error_MailAdress(),
                "No error message",
                "The email address is valid, an error message should not appear, but there is");
    }
    @Test
    public void negativ_register_enterOnlyMailAdress_submit() throws InterruptedException {
        personalPage personalPage = homePage.clickOnPersonalIcon();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.paneRegister_enter_MailAdress("abc123@.ksp.com");
//        personalPage.clickSubmit();
        Assert.assertEquals(personalPage.getText_Error_MailAdress(),
                "כתובת דוא\"ל שמלאתם איננה תקינה",
                "not apear error massage: כתובת דוא\"ל שמלאתם איננה תקינה");
        Assert.assertEquals(personalPage.checkErrors(), 5,
                "the number of errors not equal 5 like the exepted found: " + personalPage.checkErrors()  + " errors");
        personalPage.paneRegister_enter_MailAdress("abc123@kspcom");
        Assert.assertEquals(personalPage.getText_Error_MailAdress(),
                "כתובת דוא\"ל שמלאתם איננה תקינה",
                "not apear error massage: כתובת דוא\"ל שמלאתם איננה תקינה");
        Assert.assertEquals(personalPage.checkErrors(), 5,
                "the number of errors not equal 5 like the exepted found: " + personalPage.checkErrors()  + " errors");

        quit = false;
    }
    @Test
    public void register_enterOnlyPhoneNumber_submit() throws InterruptedException {
        personalPage personalPage = homePage.clickOnPersonalIcon();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.paneRegister_enter_PhoneNumber("0521234567");
        personalPage.clickSubmit();
        Assert.assertEquals(personalPage.getText_Error_PhoneNumber(),
                "No error message1",
                "phone number is valid Shouldn't show up error massage");
        int numErrorsExpected = 4;
        Assert.assertEquals(personalPage.checkErrors(), numErrorsExpected,
                "the number of errors not equal " + numErrorsExpected
                        + " like the exepted found: " + personalPage.checkErrors()  + " errors");

    }
    @Test
    public void negativ_register_enterOnlyPhoneNumber_submit() throws InterruptedException {
        personalPage personalPage = homePage.clickOnPersonalIcon();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.paneRegister_enter_PhoneNumber("052123456");
        personalPage.clickSubmit();
        Assert.assertEquals(personalPage.getText_Error_PhoneNumber(),
                "דרוש טל' נייד ישראלי - 10 ספרות",
                "not apear error massage: כתובת דוא\"ל שמלאתם איננה תקינה");
        Assert.assertEquals(personalPage.checkErrors(), 5,
                "the number of errors not equal 5 like the exepted found: " + personalPage.checkErrors()  + " errors");
        System.out.println();
/*        for (int i = 0; i < personalPage.checkError(); i++) {
            System.out.println(personalPage.getTextError(i));
        }*/
    }
}
