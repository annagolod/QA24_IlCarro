package tests;

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
        app.getHelperUser().clickOkButton();
    }

    @Test
    public void loginPositiveTest(){
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

    @Test
    public void loginNegativeTestWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam@gmail.com", "Carro54321#");
        app.getHelperUser().submitLogin();

//        Assert.assertFalse(app.getHelperUser().isLogged());

    }

    @Test
    public void loginNegativeTestWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam0810@gmail.com", "Carro543#");
        app.getHelperUser().submitLogin();

//        Assert.assertFalse(app.getHelperUser().isLogged());

    }

}
