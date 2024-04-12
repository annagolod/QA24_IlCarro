package tests;

import manager.DataProviderSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {


    @Test
    public void searchCurrentMonthSuccess() {
        // method restoreDateNow() is not placed in @Before method because it is not needed for the third universal test
        app.getHelperCar().restoreDateNow();
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "4/25/2024", "4/28/2024");
        app.getHelperCar().getScreen("src/test/screenshots/current_month.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        // method restoreDateNow() is not placed in @Before method because it is not needed for the third universal test
        app.getHelperCar().restoreDateNow();
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel", "4/27/2024", "6/28/2024");
        app.getHelperCar().getScreen("src/test/screenshots/current_year.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }

    @Test(dataProvider = "searchAnyPeriodSuccess", dataProviderClass = DataProviderSearch.class)
    public void searchAnyPeriodSuccess(String city, String dateFrom, String dateTo) {
        logger.info("Tests run with data --> " + city + dateFrom + dateTo);
//        app.getHelperCar().restoreDateNow();
//        app.getHelperCar().searchAnyPeriod(city, dateFrom, dateTo);
        app.getHelperCar().searchAnyPeriod2(city, dateFrom, dateTo);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test(dataProvider = "searchAnyPeriodSuccess", dataProviderClass = DataProviderSearch.class)
    public void searchAnyPeriodSuccess2(String city, String dateFrom, String dateTo) {
        logger.info("Tests run with data --> " + city + dateFrom + dateTo);
//        app.getHelperCar().restoreDateNow();
//        app.getHelperCar().searchAnyPeriod(city, dateFrom, dateTo);
        app.getHelperCar().searchAnyPeriod3(city, dateFrom, dateTo);
        app.getHelperCar().getScreen("src/test/screenshots/any.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void negativeSearch() {

        app.getHelperCar().searchNotValidPeriod("Tel Aviv, Israel", "1/10/2024", "10/10/2024");
        Assert.assertEquals(app.getHelperCar().getErrorText(), "You can't pick date before today");
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());

    }

    @AfterMethod
    public void postCondition() {
        //app.getHelperCar().navigateByLogo();
    }
}
