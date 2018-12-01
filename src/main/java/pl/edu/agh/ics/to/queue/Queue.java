package pl.edu.agh.ics.to.queue;

public interface Queue<T> {
        Queue<T> enqueue(T ele);
        T dequeue();
}
