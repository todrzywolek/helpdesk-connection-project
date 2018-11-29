package pl.edu.agh.ics.to.models;

public enum TypDzialu {
    SPRZEDAZY(1),
    SERWISU(2),
    POMOCY_POWYPADKOWEJ(3),
    OBSLUGI_SAMOCHODOW_ZASTEPCZYCH(4);

    private int number;


    TypDzialu(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}