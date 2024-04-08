package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderCar {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewCarSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("978-506-56")
                .price(50)
                .about("Nice car")
                .build()
        });

        list.add(new Object[]{Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Toyota")
                .model("Camry")
                .year("2021")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("978-345-91")
                .price(75)
                .about("Excellent car")
                .build()
        });


        return list.iterator();
    }
}
