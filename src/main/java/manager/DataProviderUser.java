package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
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

    @DataProvider
    public Iterator<Object[]> registrationFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader
                (new File("src/test/resources/regPositiveTest.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] all = line.split(",");
            list.add(new Object[]{
                    new User().withFirstName(all[0]).withLastName(all[1]).withEmail(all[2]).withPassword(all[3])
            });
            line = reader.readLine();
        }
        return list.iterator();
    }

}
