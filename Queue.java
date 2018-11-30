package pl.edu.agh.ics.to.models;

public interface Queue<Polaczenie> {
    Queue<Polaczenie>  dodajElement (Polaczenie oczekujacePolaczenia);
    Polaczenie pobierzElement ();
}
