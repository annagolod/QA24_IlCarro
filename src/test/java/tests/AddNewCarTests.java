package tests;

import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase{
    @BeforeClass
    private void preCondition(){
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().withEmail("tretam0810@gmail.com").withPassword("Carro54321#"));

    }

    @Test(dataProvider = "addNewCarSuccess", dataProviderClass = DataProviderCar.class)
    public void addNewCarSuccessAll(Car car){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
//        Car car = Car.builder()
//                .location("Tel Aviv, Israel")
//                .manufacture("Mazda")
//                .model("M3")
//                .year("2022")
//                .fuel("Petrol")
//                .seats(4)
//                .carClass("C")
//                .carRegNumber("978-506-" + i)
//                .price(50)
//                .about("Nice car")
//                .build();

        logger.info("Tests run with data --> " + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("/Users/annagolod/Documents/GitHub/QA24_IlCarro/lamb.jpeg");
        app.getHelperCar().getScreen("src/test/screenshots/screen-" + i + ".png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),
                car.getManufacture() + " " + car.getModel() + " added successful");
    }

    @Test
    public void addNewCarSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("KIA")
                .model("Sportage")
                .year("2020")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("978-505-" + i)
                .price(40)
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(),
                car.getManufacture() + " " + car.getModel() + " added successful");
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperCar().returnToHome();
    }
}
