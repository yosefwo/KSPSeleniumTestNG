/*
package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import pages.DynamicLoadingPage;

import java.util.Set;

public class WindowManager {
    private WebDriver driver;
    private final WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
        navigate = driver.navigate();
    }
    public void goBack(){
        navigate.back();
    }
    public void goForward(){
        navigate.forward();
    }
    public void refreshPage(){
        navigate.refresh();
    }
    public void goTo(String url){
        navigate.to(url);
    }
    public void switchToTab (String tabTitle){
        Set<String> windows  = driver.getWindowHandles();
        windows.forEach(System.out::println);

        for (String window : windows) {
            driver.switchTo().window(window);
            if(tabTitle.equals(driver.getTitle())){
                break;
            }
        }

    }
    public void OpenInNewTab(DynamicLoadingPage dynamicLoadingPage){
        Actions action=new Actions(driver);
        action.keyDown(Keys.CONTROL).build().perform();
        dynamicLoadingPage.clicExample_2();
        action.keyUp(Keys.CONTROL).build().perform();
        */
/*new Actions(driver).keyDown(Keys.LEFT_CONTROL)
                .click(webElement)
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();*//*

    */
/*.keyDown(Keys.CONTROL).
                click(webElement).build().perform();*//*

    }

}
*/

package utils;

import org.openqa.selenium.WebDriver;

public class WindowManager {

    private WebDriver driver;
    private WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver){
        this.driver = driver;
        navigate = driver.navigate();
    }

    public void goBack(){
        navigate.back();
    }

    public void goForward(){
        navigate.forward();
    }

    public void refreshPage(){
        navigate.refresh();
    }

    public void goTo(String url){
        navigate.to(url);
    }

    public void switchToTab(String tabTitle){
        var windows = driver.getWindowHandles();
        for(String window : windows){
            driver.switchTo().window(window);
            if(tabTitle.equals(driver.getTitle())){
                break;
            }
        }
    }
    public void switchToNewTab(){
        var windows = driver.getWindowHandles();
        windows.forEach(driver.switchTo()::window);

//        Iterator<String> stringIterator = windows.iterator();
//        driver.switchTo().window(stringIterator.next());
//        driver.switchTo().window(stringIterator.next());
//        windows.forEach(driver.switchTo()::window);
//        windows.forEach(driver.switchTo()::window);
    }
}