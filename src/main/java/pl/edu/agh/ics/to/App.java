package pl.edu.agh.ics.to;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.ics.to.models.Dzial;
import pl.edu.agh.ics.to.models.Infolinia;
import pl.edu.agh.ics.to.network.NetworkConnectionListener;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static final Logger LOGGER = LogManager.getLogger("App");

    public static void main(String[] args) {

        // create Departments
        LOGGER.info("Initializing departments");
        List<Dzial> departments = initDepartments();

        // init Infolinia
        LOGGER.info("Initializing infolinia");
        Infolinia infolinia = new Infolinia(departments, null);

        // init connection listener
        LOGGER.info("Listening for connection");
        new NetworkConnectionListener().listen(infolinia);

    }

    public static List<Dzial> initDepartments() {
        ArrayList<Dzial> departments = new ArrayList<>();

        departments.add(new Dzial("SPRZEDAZY", 1));
        departments.add(new Dzial("SERWISU", 2));
        departments.add(new Dzial("POMOCY_POWYPADKOWEJ", 3));
        departments.add(new Dzial("OBSLUGI_SAMOCHODOW_ZASTEPCZYCH", 4));

        return departments;
    }
}
