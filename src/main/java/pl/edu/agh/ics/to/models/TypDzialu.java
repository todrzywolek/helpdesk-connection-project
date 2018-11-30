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

    public static TypDzialu fromNumber(int number) {
        TypDzialu[] dzialy = values();

        for (int i = 0; i < dzialy.length; ++i) {
            TypDzialu dzial = dzialy[i];
            if (dzial.number == number) {
                return dzial;
            }
        }

        throw new IllegalArgumentException("Niewlasciwy numer dzialu");
    }

}