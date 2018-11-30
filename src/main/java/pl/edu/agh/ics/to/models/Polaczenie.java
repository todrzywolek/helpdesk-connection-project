package pl.edu.agh.ics.to.models;

import lombok.Data;

@Data
public class Polaczenie {
    private int numerKlienta;
    private boolean zakonczone = false;
    private int nrDzialu;

    public Polaczenie(int numerKlienta, int nrDzialu) {
        this.numerKlienta = numerKlienta;
        this.nrDzialu = nrDzialu;
    }

    public int odbierzNumer() {
        return nrDzialu;
    }

    public void zakonczPolaczenie() {
        zakonczone = true;
    }
}