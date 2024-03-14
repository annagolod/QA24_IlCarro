package manager;

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

    public void submitLogin(){
        click(By.cssSelector("button[type = 'submit']"));
    }

    public boolean isLogged(){
        return isElementPresent(By.xpath("//h2[text() = 'Logged in success']"));
    }
    public void logout(){
        click(By.cssSelector("a[href='/logout?url=%2Fsearch']"));
    }




}
