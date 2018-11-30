package pl.edu.agh.ics.to.models;

import java.util.List;
import java.util.Queue;

public class Infolinia {

    private List<Dzial> dzialy;
    private Queue<Polaczenie> oczekujacePolaczenia;

    public Infolinia(List<Dzial> dzialy, Queue<Polaczenie> oczekujacePolaczenia) {
        this.dzialy = dzialy;
        this.oczekujacePolaczenia = oczekujacePolaczenia;
    }

    public void nasluchujPolaczenia(Polaczenie polaczenie) {
        if (!przekazDoDzialu(polaczenie)) {
            oczekujacePolaczenia.add(polaczenie);
        }
    }

    public boolean przekazDoDzialu(Polaczenie polaczenie) {
        Dzial dzial = znajdzDzial(polaczenie.odbierzNumer());
        if (dzial == null) {
            throw new IllegalArgumentException("Niewlasciwy numer dzialu");
        }
        if (dzial.czyZajete()) {
            oczekujacePolaczenia.add(polaczenie);
            return false;
        }
        // TODO: make odbierzPolaczenie asynchronous
        dzial.odbierzPolaczenie(polaczenie);
        return true;
    }

    private Dzial znajdzDzial(int nrDzialu) {
        return dzialy.stream()
                .filter(dzial -> dzial.getNrWewnetrzny() == nrDzialu)
                .findFirst()
                .orElse(null);
    }

}