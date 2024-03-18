package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @AfterMethod
    public void postCondition(){
        if(app.getHelperUser().isElementPresent(By.xpath("//button[text() = 'Ok']"))) {
            app.getHelperUser().clickOkButton();
        }
    }

    @Test
    public void loginPositiveTest1(){
        User user = new User().withEmail("tretam0810@gmail.com").withPassword("Carro54321#");
//        user.setEmail("tretam0810@gmail.com");
//        user.setPassword("Carro54321#");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginSuccess(){
       app.getHelperUser().openLoginForm();
       app.getHelperUser().fillLoginForm("tretam0810@gmail.com", "Carro54321#");
       app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }
    @Test
    public void loginPositiveTestModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam0810@gmail.com", "Carro54321#");
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");


    }

   //Invalid Email without "@"
    @Test
    public void loginInvalidEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam0810gmail.com", "Carro54321#");
        app.getHelperUser().submitLogin();

       //Assert.assertFalse(app.getHelperUser().isElementPresent(By.id("cdk-overlay-0")));
       Assert.assertFalse(app.getHelperUser().isElementPresent(By.xpath("//button[text() = 'Ok']")));
    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam@gmail.com", "Carro54321#");
        app.getHelperUser().submitLogin();

        //Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertNotEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam0810@gmail.com", "Carro543#");
        app.getHelperUser().submitLogin();

        //Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertNotEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

}
