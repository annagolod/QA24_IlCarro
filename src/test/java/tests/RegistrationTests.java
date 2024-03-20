package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logout();
    }

    @AfterMethod
    public void postCondition(){
        if(app.getHelperUser().isElementPresent(By.xpath("//button[text() = 'Ok']"))) {
            app.getHelperUser().clickOkButton();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        //int y = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withFirstName("Alisa")
                .withLastName("Snowball")
                .withEmail("alsnow" + i + "@gmail.com")
                .withPassword("Alisa123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");


    }

}
