package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm(){
        click(By.xpath("//a[@href='/login?url=%2Fsearch']"));
    }

    public void fillLoginForm(String email, String password){
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillLoginForm(User user){
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }


    public boolean isLogged(){
        return isElementPresent(By.xpath("//a[text() = ' Logout ']"));
    }
    public void logout(){
        click(By.cssSelector("a[href='/logout?url=%2Fsearch']"));
    }
    //"//*[.=' Logout ' ]"





    public void clickOkButton() {
        click(By.xpath("//button[text() = 'Ok']"));
    }



    //*********Registration***************

    public void openRegistrationForm() {
        click(By.xpath("//*[text() = ' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {
            //click(By.id("terms-of-use")); // don't work because of 0 x 0
            //click(By.cssSelector("label[for = 'terms-of-use']"));
            JavascriptExecutor js = (JavascriptExecutor) wd;
            js.executeScript("document.querySelector('#terms-of-use').click()");
        }
    }

    public void unCheckPolicy() {
        if(wd.findElement(By.id("terms-of-use")).isSelected()) {
            JavascriptExecutor js = (JavascriptExecutor) wd;
            js.executeScript("document.querySelector('#terms-of-use').click()");
        }
    }
    public void checkPolicyXY(){
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {
            // Dimension size = wd.manage().window().getSize();// info about monitor
            WebElement label = wd.findElement(By.cssSelector("label[for = 'terms-of-use']"));
            Rectangle rect = label.getRect();
            int w = rect.getWidth();
            Actions actions = new Actions(wd);
            int xOffSet = -w / 2;
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOkButton();
    }
}
