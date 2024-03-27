package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text != null){
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element){
        element.sendKeys("a");
        element.sendKeys(Keys.BACK_SPACE);
    }


    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return !list.isEmpty();

    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isYallaButtonNotActive() {
        return isElementPresent(By.cssSelector("button[disabled]"));

        //return !wd.findElement(By.cssSelector("button[type = 'submit']")).isEnabled();

    }

    public void submit(){
        click(By.cssSelector("button[type = 'submit']"));
    }

    public String getMessage() {
        pause(2000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }
}
