package pl.edu.agh.ics.to.events;

public class CallEndedEventArgs {
    private Object source;
    private boolean callEnded;

    public CallEndedEventArgs(Object source, boolean callEnded) {
        this.source = source;
        this.callEnded = callEnded;
    }
}