package pl.edu.agh.ics.to.events;


import pl.edu.agh.ics.to.models.Polaczenie;

public class CallEndedEventArgs {
    private Polaczenie previousCall;
    private boolean callEnded;

    public CallEndedEventArgs(Polaczenie previousCall, boolean callEnded) {
        this.previousCall = previousCall;
        this.callEnded = callEnded;
    }

    public Polaczenie getPreviousCall() {
        return previousCall;
    }

    public void setPreviousCall(Polaczenie previousCall) {
        this.previousCall = previousCall;
    }

    public boolean isCallEnded() {
        return callEnded;
    }

    public void setCallEnded(boolean callEnded) {
        this.callEnded = callEnded;
    }
}