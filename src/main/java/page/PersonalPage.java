package page;

import base.WebDriverInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentManager;
import utils.WindowManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class PersonalPage {
    private WebDriver driver;
    private By x_button = By.xpath("//*[@id=\"login_modal\"]/div/div/div[1]/button/span");
    private By createAcountButton = By.xpath("//*[@id=\"site-main\"]/div[2]/div/div[2]/button");
    private By register_email = By.id("register_email");
    private By register_phone = By.id("register_phone");
    private By register_first_name = By.id("register_first_name");
    private By register_last_name = By.id("register_last_name");
    private By register_password = By.id("register_password");
    private By register_password2 = By.id("register_password2");


    private By errorClass = By.cssSelector("span.help-block.form-error");
    private By registerError_email = By.xpath("//*[@id=\"registration\"]/div[1]/div[1]/div/span");
    private By registerError_phone = By.xpath("//*[@id=\"registration\"]/div[1]/div[2]/div/span");
    private By registerError_first_name = By.xpath("//*[@id=\"registration\"]/div[2]/div[1]/div/span");
    private By registerError_last_name = By.xpath("//*[@id=\"registration\"]/div[2]/div[2]/div/span");
    private By registerError_password = By.xpath("//*[@id=\"registration\"]/div[3]/div[1]/div/span");
    private By registerError_password2 = By.xpath("//*[@id=\"password_confirmation_wrapper\"]/span");
    private By registerSubmit = By.xpath("//*[@id=\"registration\"]/div[4]/div/button");

    private By enterLogInButton = By.xpath("//*[@id=\"site-main\"]/div[2]/div/div[1]/button");
    private By logIn_email = By.id("login_email");
    private By logIn_password = By.id("login_password");
    private By logIn_remember_me = By.cssSelector("input[name=remember]");
    private By loginSubmit = By.xpath("//*[@id=\"login_using_email\"]/button");

    private By logInError_email = By.xpath("//*[@id=\"login_using_email\"]/div[2]/span");
    private By logInError_password = By.xpath("//*[@id=\"login_using_email\"]/div[3]/span");
    private Set<Cookie> cookieSet;


    public PersonalPage()  throws IOException {
        this.driver = WebDriverInstance.getDriverThreadLocal();
    }

/*    public void skipBefore_backHomePage() { //for dataProvider
        new Actions(driver).sendKeys(Keys.ESCAPE);
        new WindowManager(driver).goBack();
    }*/

    public void click_X_AtPane() {
/*        List<WebElement> elementList = driver.findElements(By.cssSelector("button[class=\"close\"]"));
        System.out.println("elementList " + elementList.size());
        Thread.sleep(3000);
        for (WebElement a: elementList) {
            System.out.println(a.isDisplayed());
        }*/
        driver.findElement(x_button).click();
    }

    public void click_createAcount() throws InterruptedException {
        WebElement element_createAcountButton = driver.findElement(createAcountButton);
        if (element_createAcountButton.isDisplayed())
            driver.findElement(createAcountButton).click();
        else{
            click_X_AtPane();
            click_createAcount();
        }
    }
    public void registerPane_enter_MailAdress(String mailAdress) {
        driver.findElement(register_email).clear();
        driver.findElement(register_email).sendKeys(mailAdress);
    }
    public void registerPane_enter_PhoneNumber(String phoneNumber) {
        driver.findElement(register_phone).sendKeys(phoneNumber);
    }
    public void registerPane_enter_firstName(String firstName) {
        driver.findElement(register_first_name).sendKeys(firstName);
    }
    public void registerPane_enter_last_name(String last_name) {
        driver.findElement(register_last_name).sendKeys(last_name);
    }
    public void registerPane_enter_password(String password) {
        driver.findElement(register_password).sendKeys(password);
    }
    public void registerPane_enter_password2(String password2) {
        driver.findElement(register_password2).sendKeys(password2);
    }
    public void register_clickSubmit() {
        WebElement element_submitRegister = driver.findElement(registerSubmit);
        new Actions(driver).scrollToElement(element_submitRegister).perform();
        driver.findElement(registerSubmit).click();
    }


    public String registerPane_getText_Error_MailAdress() {
        try {
            return driver.findElement(registerError_email).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    public String registerPane_getText_Error_PhoneNumber() {
        try {
            return driver.findElement(registerError_phone).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }

    public String registerPane_getText_Error_firstName() {
        try {
            return driver.findElement(registerError_first_name).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    public String registerPane_getText_Error_last_name() {
            try {
            return driver.findElement(registerError_last_name).getText();
        }   catch (Exception e) {
                return "No error message";
            }
    }
    public String registerPane_getText_Error_password() {
        try {
            return driver.findElement(registerError_password).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    public String registerPane_getText_Error_password2() {
        try {
            return driver.findElement(registerError_password2).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }

    public void click_LogIn() {
        try {
            WebElement para = (WebElement) new WebDriverWait(driver,Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login_modal\"]/div/div")));
        } catch (Exception e) {
            System.out.println("pane login not opened after until 2 Seconds");
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"site-main\"]/div[2]/div/div[1]/button")).click();
        }catch (Exception e){
            System.out.println("login not clicked");
        }
    }
    public void click_LogInByEmail() {
        driver.findElement(By.className("icon-email")).click();
    }
    public void loginPane_enter_MailAdress(String mailAdress) {
        WebElement element_MailAdress = driver.findElement(logIn_email);
//        new Actions(driver).scrollToElement(element_MailAdress).perform();
        driver.findElement(logIn_email).sendKeys("1");
        driver.findElement(logIn_email).clear();
        driver.findElement(logIn_email).sendKeys(mailAdress);
        driver.findElement(logIn_password).click();
        ExtentManager.logChild("loginPane_enter_MailAdress("+mailAdress+")");


    }
    public void loginPane_enter_password(String password) {
        WebElement element_password = driver.findElement(logIn_password);
//        new Actions(driver).scrollToElement(element_password).perform();
        driver.findElement(logIn_password).sendKeys(password);
        driver.findElement(logIn_email).click();

    }
    public void loginClickSubmit() {
        WebElement element_loginSubmit = driver.findElement(loginSubmit);
//        new Actions(driver).scrollToElement(element_loginSubmit).perform();
        driver.findElement(loginSubmit).click();
    }


    public String loginPane_getText_Error_Email_or_phone() {
        try {
            return driver.findElement(logInError_email).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }


    public String loginPane_getText_Error_password() {
        try {
            return driver.findElement(logInError_password).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }

    public int checkErrors() {
//        Thread.sleep(5000);
        try {
            var errors = driver.findElements(errorClass);
            return errors.size();
        } catch (Exception e){
            return 0;
        }
    }
    public String getTextError(int numError) {
//        Thread.sleep(5000);
        try {
            List<WebElement> errors = driver.findElements(errorClass);
            return errors.get(numError).getText();
        } catch (Exception e){
            return "without errors";
        }
    }
    public boolean checkSuccessful_login(){
        try {
            System.out.println(driver.findElement(By.xpath("//*[@id=\"user_bar\"]/div")).getText());
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public void printCoockies() {
        System.out.print("printCoockies: ");
        cookieSet = driver.manage().getCookies();

        if (cookieSet.size() == 0)
            System.out.print("No cookies");
        if (cookieSet.size() > 0){
//            Cookie[] cookieArr = (Cookie[]) cookieSet.toArray();
//            for (int i = 0; i < cookieArr.length; i++) {
//                System.out.print(cookieArr[i].getName());
//                System.out.print(" getValue ->" + cookieArr[i].getValue());
//                System.out.print(" getPath ->" + cookieArr[i].getPath());
//                System.out.print(" | ");
//            }
            for (Cookie c: cookieSet) {
                System.out.print(c.getName());
                System.out.print(" Value " + c.getValue());
                System.out.print(" getPath ->" + c.getPath());
                System.out.print(" | ");
            }
        }
        System.out.println();
    }

    public void log_OutAND_printCoockiesChenge() {
        driver.findElement(By.xpath("//*[@id=\"user-navbar\"]/ul[2]/li/a")).click();
        printCoockies();
        /*Set<Cookie> cookieSet1 = driver.manage().c

        for (Cookie c: cookieSet) {
            c.getValue()
            if (cookieSet1.contains(c)){
                System.out.println("cookie: " + c.getName() +" -exit in 2 sets ");
                cookieSet.remove(c);
                cookieSet1.remove(c);
            }
        }
        System.out.print("cookieSet remain before log out :");
        if (cookieSet.size() == 0)
            System.out.print("No cookies");
        if (cookieSet.size() > 0){
            for (Cookie c: cookieSet) {
                System.out.print(c.getName());
                System.out.print(" | ");
            }
        }
        System.out.print("cookieSet remain after log out");
        if (cookieSet.size() == 0)
            System.out.print("No cookies");
        if (cookieSet.size() > 0){
            for (Cookie c: cookieSet) {
                System.out.print(c.getName());
                System.out.print(" | ");
            }
        }*/
        System.out.println();
    }


}
