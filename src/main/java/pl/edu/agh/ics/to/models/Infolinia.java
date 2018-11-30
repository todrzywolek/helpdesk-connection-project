package pl.edu.agh.ics.to.models;

import lombok.Data;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Data
public class Infolinia {
    private static final Logger LOGGER = Logger.getLogger(Infolinia.class);

    private List<Dzial> dzialy;
    private Queue<Polaczenie> oczekujacePolaczenia;

    public Infolinia(List<Dzial> dzialy, Queue<Polaczenie> oczekujacePolaczenia) {
        this.dzialy = dzialy;
        this.oczekujacePolaczenia = oczekujacePolaczenia;
    }

    public String nasluchujPolaczenia(Polaczenie polaczenie) {
        boolean result = przekazDoDzialu(polaczenie);
        if (result) return "Przekazano polaczenie. Dzial wolny. Przekierowano";
        return "Przekazano polaczenie. Dzial zajety. Prosze czekac";
    }

    public boolean przekazDoDzialu(Polaczenie polaczenie) {
        Optional<Dzial> dzial = znajdzDzial(polaczenie.odbierzNumer());
        if (!dzial.isPresent()) {
            throw new IllegalArgumentException("Niewlasciwy numer dzialu");
        }
        if (dzial.get().czyZajete()) {
            oczekujacePolaczenia.add(polaczenie);
            return false;
        }
        // TODO: make odbierzPolaczenie asynchronous
        dzial.get().odbierzPolaczenie(polaczenie);
        return true;
    }

    private Optional<Dzial> znajdzDzial(int nrDzialu) {
        return dzialy.stream()
                .filter(dzial -> dzial.getNrWewnetrzny() == nrDzialu)
                .findFirst();
    }

}