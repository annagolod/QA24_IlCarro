package tests;

import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method was log out");
        }
    }
    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        if(app.getHelperUser().isElementPresent(By.xpath("//button[text() = 'Ok']"))) {
            app.getHelperUser().clickOkButton();
        }
    }

    @Test(dataProvider = "loginSuccessModel", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){
        logger.info("Tests run with data --> " + user.toString());
        //User user = new User().withEmail("tretam0810@gmail.com").withPassword("Carro54321#");
//        user.setEmail("tretam0810@gmail.com");
//        user.setPassword("Carro54321#");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check that window with 'Logged in success' appears");

    }

    @Test(groups = {"smoke"})
    public void loginSuccess(){
        logger.info("Test data --> email: tretam0810@gmail.com, password: Carro54321#");
       app.getHelperUser().openLoginForm();
       app.getHelperUser().fillLoginForm("tretam0810@gmail.com", "Carro54321#");
       app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check that window with 'Logged in success' appears");

    }


   //Invalid Email without "@"
    @Test
    public void loginInvalidEmail(){
        logger.info("Test data --> email: tretam0810gmail.com, password: Carro54321#");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam0810gmail.com", "Carro54321#");
        app.getHelperUser().submit();


       Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//div[contains(text(), \"It'snot look like email\")]")));
       logger.info("Assert check that error text 'It'snot look like email' appears");
       Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
       Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
       logger.info("Assert check that button Yalla is not active");
       //Assert.assertFalse(app.getHelperUser().isElementPresent(By.xpath("//button[text() = 'Ok']")));
    }


    @Test
    public void loginInvalidPassword(){
        logger.info("Test data --> email: tretam0810@gmail.com, password: car5");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam0810@gmail.com", "car5");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check that window with 'Login or Password incorrect' appears");
        Assert.assertNotEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data --> email: tretam@gmail.com, password: Carro54321#");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tretam@gmail.com", "Carro54321#");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check that window with 'Login or Password incorrect' appears");
        Assert.assertNotEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

}
