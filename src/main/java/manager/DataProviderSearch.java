package manager;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderSearch {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> searchAnyPeriodSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Tel Aviv, Israel", "12/27/2024", "2/28/2025"});
        list.add(new Object[]{"Tel Aviv, Israel", "4/25/2024", "4/28/2024"});
        list.add(new Object[]{"Tel Aviv, Israel", "5/22/2024", "7/11/2024"});
        list.add(new Object[]{"Tel Aviv, Israel", "10/15/2024", "10/18/2024"});
        list.add(new Object[]{"Tel Aviv, Israel", "2/27/2025", "3/14/2025"});

        return list.iterator();
    }
}
