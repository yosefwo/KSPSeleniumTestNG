package page;

import base.WebDriverInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By iconPersonal = By.xpath("//*[@id=\"site-header\"]/div[2]/div[3]/a[1]/button");
    private By downAd = By.cssSelector("div.jss83");

    public HomePage() throws IOException {
        this.driver = WebDriverInstance.getDriverThreadLocal();
    }
    public WebElement clickOnPersonalIcon() {
        WebElement iconPersonalElement = driver.findElement(iconPersonal);
        try{
            iconPersonalElement.click();
            return iconPersonalElement;
        } catch (ElementClickInterceptedException e){
            try {
                driver.findElement(downAd).click();
                if (iconPersonalElement.isDisplayed()) {
                    iconPersonalElement.click();
                    return iconPersonalElement;
                }
            } catch (Exception ee){
                System.out.println("not found ad");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(iconPersonal));
                iconPersonalElement.click();
            }
        }
        return iconPersonalElement;
    }

}
