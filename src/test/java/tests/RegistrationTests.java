package tests;

import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logout();
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        if(app.getHelperUser().isElementPresent(By.xpath("//button[text() = 'Ok']"))) {
            app.getHelperUser().clickOkButton();
        }
//        if(!app.getHelperUser().isLogged()){
//            app.getHelperUser().openLoginForm();
//        }
    }

    @Test(dataProvider = "registrationFile", dataProviderClass = DataProviderUser.class)
    public void registrationSuccess(User user){
//        Random random = new Random();
//        int i = random.nextInt(1000);
//        //int y = (int)(System.currentTimeMillis()/1000)%3600;
//        User user = new User()
//                .withFirstName("Alisa")
//                .withLastName("Snowball")
//                .withEmail("alsnow" + i + "@gmail.com")
//                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }

    @Test(groups = {"smoke"})
    public void registrationBlankName(){
        User user = new User()
                .withFirstName(" ")
                .withLastName("Snowball")
                .withEmail("als@gmail.com")
                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "{\"firstName\":\"must not be blank\"}");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationEmptyName(){
        User user = new User()
                .withFirstName("")
                .withLastName("Snowball")
                .withEmail("al@gmail.com")
                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationBlankLastName(){
        User user = new User()
                .withFirstName("Alisa")
                .withLastName(" ")
                .withEmail("alsn@gmail.com")
                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "{\"lastName\":\"must not be blank\"}");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationEmptyLastName(){
        User user = new User()
                .withFirstName("Alisa")
                .withLastName("")
                .withEmail("alsno@gmail.com")
                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationInvalidEmail(){
        User user = new User()
                .withFirstName("Alisa")
                .withLastName("Snowball")
                .withEmail("alsnowgmail.com")
                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong email format\nWrong email format");
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationEmptyEmail(){
        User user = new User()
                .withFirstName("Al")
                .withLastName("Snow")
                .withEmail("")
                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationInvalidPassword(){
        User user = new User()
                .withFirstName("Alisa")
                .withLastName("Snowball")
                .withEmail("alsnowb@gmail.com")
                .withPassword("Alisa123456");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isElementPresent
                (By.xpath("//div[contains(text(),'Password must contain')]")));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationEmptyPassword(){
        User user = new User()
                .withFirstName("Alisa")
                .withLastName("Snowball")
                .withEmail("alsnowball@gmail.com")
                .withPassword("");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationNotCheckedPolicy(){
        User user = new User()
                .withFirstName("Alisa")
                .withLastName("Snowball")
                .withEmail("alsnow@gmail.com")
                .withPassword("Alisa123456#");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().unCheckPolicy();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationRegisteredUser(){
        User user = new User()
                .withFirstName("Anna")
                .withLastName("Golod")
                .withEmail("tretam0810@gmail.com")
                .withPassword("Carro54321#");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"User already exists\"");
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

}
