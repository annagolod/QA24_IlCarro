package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginSuccessModel(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                new User().withEmail("tretam0810@gmail.com").withPassword("Carro54321#")
        });

        return list.iterator();
    }

}
