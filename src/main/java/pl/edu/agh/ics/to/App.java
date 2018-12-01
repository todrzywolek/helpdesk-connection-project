package pl.edu.agh.ics.to;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.agh.ics.to.models.Dzial;
import pl.edu.agh.ics.to.models.Infolinia;
import pl.edu.agh.ics.to.models.Polaczenie;
import pl.edu.agh.ics.to.network.NetworkConnectionListener;
import pl.edu.agh.ics.to.queue.Queue;
import pl.edu.agh.ics.to.queue.QueueImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {
    private static final Logger LOGGER = LogManager.getLogger("App");

    public static void main(String[] args) {

        // create map of queues
        HashMap<Integer, Queue<Polaczenie>> queues = new HashMap<>();

        // create Departments
        LOGGER.info("Initializing departments");
        List<Dzial> departments = initDepartments(queues);

        // init Infolinia
        LOGGER.info("Initializing infolinia");
        Infolinia infolinia = new Infolinia(departments, queues);

        // init connection listener
        LOGGER.info("Listening for connection");
        new NetworkConnectionListener().listen(infolinia);

    }

    public static List<Dzial> initDepartments(HashMap<Integer, Queue<Polaczenie>> queues) {
        ArrayList<Dzial> departments = new ArrayList<>();

        Dzial dep1 = new Dzial("SPRZEDAZY", 1);
        departments.add(dep1);
        queues.put(dep1.getNrWewnetrzny(), new QueueImpl<>());

        Dzial dep2 = new Dzial("SERWISU", 2);
        departments.add(dep2);
        queues.put(dep2.getNrWewnetrzny(), new QueueImpl<>());

        Dzial dep3 = new Dzial("POMOCY_POWYPADKOWEJ", 3);
        departments.add(dep3);
        queues.put(dep3.getNrWewnetrzny(), new QueueImpl<>());

        Dzial dep4 = new Dzial("OBSLUGI_SAMOCHODOW_ZASTEPCZYCH", 4);
        departments.add(dep4);
        queues.put(dep4.getNrWewnetrzny(), new QueueImpl<>());

        return departments;
    }
}
