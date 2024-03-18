package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void submitLogin(){
        click(By.cssSelector("button[type = 'submit']"));
    }

    public boolean isLogged(){
        return isElementPresent(By.xpath("//a[text() = ' Logout ']"));
    }
    public void logout(){
        click(By.cssSelector("a[href='/logout?url=%2Fsearch']"));
    }
    //"//*[.=' Logout ' ]"


    public String getMessage() {
        pause(2000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }


    public void clickOkButton() {
        click(By.xpath("//button[text() = 'Ok']"));
    }

}
