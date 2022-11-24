package test;

//import base.BaseTest;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.PersonalPage;
import utils.DataProviderExel;

import java.io.IOException;
//@Listeners(utils.Listeners.class)

public class RegisterTest extends BaseTest {
    public RegisterTest() throws IOException {
        super();
    }

    @Test
    public void register_valid_enterAll() throws InterruptedException, IOException {
        new HomePage().clickOnPersonalIcon();
        PersonalPage personalPage = new PersonalPage();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.registerPane_enter_MailAdress("abc123@ksp.com");
        personalPage.registerPane_enter_PhoneNumber("0521234567");
        personalPage.registerPane_enter_firstName("abc123");
        personalPage.registerPane_enter_last_name("abc123");
        personalPage.registerPane_enter_password("abc123ABC123");
        personalPage.registerPane_enter_password2("abc123ABC123");
//        personalPage.clickSubmit();
        Assert.assertEquals( personalPage.checkErrors(),20,"An error was found");
    }
    @Test(dataProviderClass = DataProviderExel.class, dataProvider = "col-provider")
    public void register_valid_Only_MailAdress(String mailAddressValid) throws InterruptedException, IOException {
        System.out.println("enter MailAdress= " + mailAddressValid);


//        if (DataProviderExel.firstTimeOnly = true){ for skip on befor and
        new HomePage().clickOnPersonalIcon();
        PersonalPage personalPage = new PersonalPage();

        personalPage.click_X_AtPane();
        personalPage.click_createAcount();
//          DataProviderExel.firstTimeOnly = false;

//        } else {
//            personalPage = homePage.skipHomePage();
//        }

        personalPage.registerPane_enter_MailAdress(mailAddressValid);
        Thread.sleep(1000);
        Assert.assertEquals(personalPage.registerPane_getText_Error_MailAdress(),
                "No error message",
                "The email address is valid, an error message should not appear, but there is");
//        goHome();
////        personalPage.skipBefore_backHomePage();
    }
    @Test(dataProviderClass = DataProviderExel.class, dataProvider = "col-provider")
    public void register_invalid_enterOnlyMailAdress(String mailAddressInvalid) throws InterruptedException, IOException {
        System.out.println("enter mailAddressInvalid ="+ mailAddressInvalid);

        new HomePage().clickOnPersonalIcon();
        PersonalPage personalPage = new PersonalPage();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.registerPane_enter_MailAdress(mailAddressInvalid);
//        personalPage.clickSubmit();
        Assert.assertEquals(personalPage.registerPane_getText_Error_MailAdress(),
                "כתובת דוא\"ל שמלאתם איננה תקינה",
                "not apear error massage: כתובת דוא\"ל שמלאתם איננה תקינה");
        Assert.assertEquals(personalPage.checkErrors(), 5,
                "the number of errors not equal 5 like the exepted found: " + personalPage.checkErrors()  + " errors");
        personalPage.registerPane_enter_MailAdress("abc123@kspcom");
        Assert.assertEquals(personalPage.registerPane_getText_Error_MailAdress(),
                "כתובת דוא\"ל שמלאתם איננה תקינה",
                "not apear error massage: כתובת דוא\"ל שמלאתם איננה תקינה");
        Assert.assertEquals(personalPage.checkErrors(), 5,
                "the number of errors not equal 5 like the exepted found: " + personalPage.checkErrors()  + " errors");

    }
    @Test
    public void register_valid_enterOnlyPhoneNumber_submit() throws InterruptedException, IOException {
        new HomePage().clickOnPersonalIcon();
        PersonalPage personalPage = new PersonalPage();
        personalPage.click_X_AtPane();
        personalPage.click_createAcount();

        personalPage.registerPane_enter_PhoneNumber("0521234567");
        personalPage.register_clickSubmit();
        Assert.assertEquals(personalPage.registerPane_getText_Error_PhoneNumber(),
                "No error message",
                "phone number is valid Shouldn't show up error massage");
        int numErrorsExpected = 4;
        Assert.assertEquals(personalPage.checkErrors(), numErrorsExpected,
                "the number of errors not equal " + numErrorsExpected
                        + " like the exepted found: " + personalPage.checkErrors()  + " errors");

    }
    @Test
    public void register_invalid_enterOnlyPhoneNumber_submit() throws InterruptedException, IOException {
        new HomePage().clickOnPersonalIcon();
        PersonalPage personalPage = new PersonalPage();
        personalPage.click_createAcount();

        personalPage.registerPane_enter_PhoneNumber("052123456");
        personalPage.register_clickSubmit();
        Assert.assertEquals(personalPage.registerPane_getText_Error_PhoneNumber(),
                "דרוש טל' נייד ישראלי - 10 ספרות",
                "not apear error massage: כתובת דוא\"ל שמלאתם איננה תקינה");
        Assert.assertEquals(personalPage.checkErrors(), 5,
                "the number of errors not equal 5 like the exepted found: " + personalPage.checkErrors()  + " errors");
        System.out.println();
        for (int i = 0; i < personalPage.checkErrors(); i++) {
            System.out.println(personalPage.getTextError(i));
        }
    }
}
