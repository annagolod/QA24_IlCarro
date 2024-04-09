package tests;

import manager.DataProviderSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        // method restoreDateNow() is not placed in @Before method because it is not needed for the third universal test
        app.getHelperCar().restoreDateNow();
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "4/25/2024", "4/28/2024");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        // method restoreDateNow() is not placed in @Before method because it is not needed for the third universal test
        app.getHelperCar().restoreDateNow();
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel", "4/27/2024", "6/28/2024");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }

    @Test(dataProvider = "searchAnyPeriodSuccess", dataProviderClass = DataProviderSearch.class)
    public void searchAnyPeriodSuccess(String city, String dateFrom, String dateTo){
        logger.info("Tests run with data --> " + city + dateFrom + dateTo);
//        app.getHelperCar().restoreDateNow();
//        app.getHelperCar().searchAnyPeriod(city, dateFrom, dateTo);
        app.getHelperCar().searchAnyPeriod2(city, dateFrom, dateTo);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @AfterMethod
    //this method is for activate input locator By.id("dates") after each test
    //otherwise input locator will be "By.cssSelector("form.ng-valid.ng-touched.ng-dirty>div:nth-child(2)>input")"
    public void clearSearchFields(){
        app.getHelperCar().click(By.cssSelector("a[href='/search']"));
    }
}
