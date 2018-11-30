package pl.edu.agh.ics.to.models;

import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Data
public class Infolinia {

    private List<Dzial> dzialy;
    private Queue<Polaczenie> oczekujacePolaczenia;

    public Infolinia(List<Dzial> dzialy, Queue<Polaczenie> oczekujacePolaczenia) {
        this.dzialy = dzialy;
        this.oczekujacePolaczenia = oczekujacePolaczenia;
    }

    public void nasluchujPolaczenia(Polaczenie polaczenie) {
        // TODO: implement listener
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