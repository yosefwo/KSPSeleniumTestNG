package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By iconPersonal = By.xpath("//*[@id=\"site-header\"]/div[2]/div[3]/a[1]/button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public personalPage clickOnPersonalIcon() throws InterruptedException {
        WebElement element_iconPersonal = driver.findElement(iconPersonal);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        driver.manage().timeouts().;
        Thread.sleep(6000);
        driver.findElement(iconPersonal).click();
        return new personalPage(driver);
    }

}
