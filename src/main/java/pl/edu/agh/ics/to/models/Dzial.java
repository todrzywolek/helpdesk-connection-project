package pl.edu.agh.ics.to.models;

import lombok.Data;

@Data
public class Dzial {

    private String nazwaDzialu;
    private Polaczenie polaczenie;
    private int nrWewnetrzny;

    public Dzial(String nazwaDzialu, int nrWewnetrzny) {
        this.nazwaDzialu = nazwaDzialu;
        this.nrWewnetrzny = nrWewnetrzny;
        this.polaczenie = new Polaczenie(-1, nrWewnetrzny);
        this.polaczenie.setZakonczone(true);
    }

    public boolean czyZajete() {
        return !polaczenie.isZakonczone();
    }

    public void odbierzPolaczenie(Polaczenie polaczenie) {
        // TODO: random thread lock between 30s - 1 min
        this.polaczenie = polaczenie;
        polaczenie.zakonczPolaczenie();
    }

}