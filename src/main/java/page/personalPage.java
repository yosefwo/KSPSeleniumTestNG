package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class personalPage {
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



    public personalPage(WebDriver driver) {
        this.driver = driver;
    }
    public void click_X_AtPane() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(x_button).click();
    }

    public void click_createAcount() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(createAcountButton).click();
    }
    public void paneRegister_enter_MailAdress(String mailAdress) {
        driver.findElement(register_email).sendKeys(mailAdress);
    }
    public void paneRegister_enter_PhoneNumber(String phoneNumber) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(register_phone).sendKeys(phoneNumber);
    }
    public void paneRegister_enter_firstName(String firstName) {
        driver.findElement(register_first_name).sendKeys(firstName);
    }
    public void paneRegister_enter_last_name(String last_name) {
        driver.findElement(register_last_name).sendKeys(last_name);
    }
    public void paneRegister_enter_password(String password) {
        driver.findElement(register_password).sendKeys(password);
    }
    public void paneRegister_enter_password2(String password2) {
        driver.findElement(register_password2).sendKeys(password2);
    }
    public void clickSubmit() {
        WebElement element_submitRegister = driver.findElement(registerSubmit);
        new Actions(driver).scrollToElement(element_submitRegister).perform();
        driver.findElement(registerSubmit).click();
    }


    public String getText_Error_MailAdress() {
        try {
            return driver.findElement(registerError_email).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    public String getText_Error_PhoneNumber() {
        try {
            return driver.findElement(registerError_phone).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }

    public String getText_Error_firstName() {
        try {
            return driver.findElement(registerError_first_name).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    public String getText_Error_last_name() {
            try {
            return driver.findElement(registerError_last_name).getText();
        }   catch (Exception e) {
                return "No error message";
            }
    }
    public String getText_Error_password() {
        try {
            return driver.findElement(registerError_password).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    public String getText_Error_password2() {
        try {
            return driver.findElement(registerError_password2).getText();
        } catch (Exception e) {
            return "No error message";
        }
    }
    public int checkErrors() throws InterruptedException {
        Thread.sleep(5000);
        try {
            var errors = driver.findElements(errorClass);
            return errors.size();
        } catch (Exception e){
            return 0;
        }
    }
    public String getTextError(int numError) throws InterruptedException {
        Thread.sleep(5000);
        try {
            List<WebElement> errors = driver.findElements(errorClass);
            return errors.get(numError).getText();
        } catch (Exception e){
            return "without errors";
        }
    }

}
