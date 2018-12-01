package pl.edu.agh.ics.to.models;

import lombok.Data;
import pl.edu.agh.ics.to.events.CallEndedEventArgs;
import pl.edu.agh.ics.to.events.Event;

@Data
public class Polaczenie {
    private int numerKlienta;
    private boolean zakonczone = false;
    private int nrDzialu;
    private Event<CallEndedEventArgs> callEndedEvent = new Event<>();

    public Polaczenie(int numerKlienta, int nrDzialu) {
        this.numerKlienta = numerKlienta;
        this.nrDzialu = nrDzialu;
    }

    public int odbierzNumer() {
        return nrDzialu;
    }

    public void zakonczPolaczenie() {
        zakonczone = true;
        callEndedEvent.fire(new CallEndedEventArgs(this, zakonczone));
    }

    public Event<CallEndedEventArgs> getCallEndedEvent() {
        return callEndedEvent;
    }


}