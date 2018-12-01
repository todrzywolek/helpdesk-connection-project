package pl.edu.agh.ics.to.events;


import java.util.function.Consumer;

public class Event<TArgs> {
    private Consumer<TArgs> handler;

    public Subscription addHandler(Consumer<TArgs> handler) {
        this.handler = handler;

        return new Subscription(this);
    }

    public void fire(TArgs args) {
        this.handler.accept(args);
    }

    public class Subscription implements AutoCloseable {

        public Subscription(Event<TArgs> event) {
            this.event = event;
        }

        private Event<TArgs> event;

        @Override
        public void close() {
            event.handler = null;
        }
    }
}
