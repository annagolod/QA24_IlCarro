package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver wd) {
        super(wd);
    }


    public void openCarForm() {
        pause(500);
        click(By.xpath("//*[text() = ' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.xpath("//input[@id = 'year']"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        //type(By.id("seats"), car.getSeats()+"");
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice()+"");
       // type(By.id("price"), String.valueOf(car.getPrice()));
        type(By.id("about"), car.getAbout());

    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //Gas
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHome() {
        click(By.xpath("//button[text() = 'Search cars']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        String[] from = dateFrom.split("/");
        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));
        String[] to = dateTo.split("/");
        String locatorTo = "//div[text()=' " + to[1] + " ']";
        click(By.xpath(locatorTo));

    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        LocalDate now = LocalDate.now();
        //int year = now.getYear();
        int month = now.getMonthValue();
        //int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        int diffMonth = from.getMonthValue() - month;
        if(diffMonth > 0){
            clickNextMonthButton(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));
        diffMonth = to.getMonthValue() - from.getMonthValue();
        if(diffMonth > 0 ){
            clickNextMonthButton(diffMonth);
        }
        click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));

    }

    private void clickNextMonthButton(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label = 'Next month']"));
        }
    }
//This method works after data with 2025 year if the next test will be with 2024 only with method restoreDateNow()

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {

        typeCity(city);
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYearFrom = from.getYear() - year;
        int diffYearTo = to.getYear() - from.getYear();

        if (diffYearFrom >0){
            clickSearchYearButton();
            clickYearButton(from.getYear());
            clickMonthButton(from.getMonth());

        } else {
            int diffMonth = from.getMonthValue() - month;
            if(diffMonth > 0){
                clickNextMonthButton(diffMonth);
            }
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        if (diffYearTo >0){
            clickSearchYearButton();
            clickYearButton(to.getYear());
            clickMonthButton(to.getMonth());

        } else {
            int diffMonth = to.getMonthValue() - from.getMonthValue();
            if(diffMonth > 0){
                clickNextMonthButton(diffMonth);
            }
        }
        click(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']"));

    }
//This is universal test with clicking on Year and Month buttons for input of any date
    public void searchAnyPeriod2(String city, String dateFrom, String dateTo) {

        typeCity(city);
        click(By.id("dates"));

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        clickDate(from);
        clickDate(to);

    }

    private void clickDate(LocalDate date) {
        clickSearchYearButton();
        clickYearButton(date.getYear());
        clickMonthButton(date.getMonth());
        click(By.xpath("//div[text()=' " + date.getDayOfMonth() + " ']"));

    }

    private void clickMonthButton(Month month) {
        String mmm = month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase();
        click(By.xpath("//div[text() = ' " + mmm + " ']"));
    }

    private void clickYearButton(int year) {
        click(By.xpath("//div[text() = ' " + year + " ']"));
    }

    private void clickSearchYearButton() {
        click(By.cssSelector("button[aria-label = 'Choose month and year']"));
    }

    public void restoreDateNow() {
        LocalDate now = LocalDate.now();
        click(By.id("dates"));
        clickDate(now);
        clickDate(now);
    }

}
