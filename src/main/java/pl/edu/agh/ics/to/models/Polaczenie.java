package pl.edu.agh.ics.to.models;

import lombok.Data;

@Data
public class Polaczenie {
    private int numerKlienta;
    private boolean zakonczone = false;
    private TypDzialu typDzialu;

    public Polaczenie(int numerKlienta, TypDzialu typDzialu) {
        this.numerKlienta = numerKlienta;
        this.typDzialu = typDzialu;
    }

    public int odbierzNumer() {
        return typDzialu.getNumber();
    }

    public void zakonczPolaczenie() {
        zakonczone = true;
    }
}