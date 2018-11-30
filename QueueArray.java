package pl.edu.agh.ics.to.models;

public class QueueArray<Polaczenie> implements  Queue<Polaczenie>{
    private  Polaczenie[] arr;
    private  int wszystkie, pierwszy, nastepny;

    public  QueueArray() {
        arr = (Polaczenie[])  new Object[2];
    }

    private void  zmienRozmiar (int pojemnosc ) {
        Polaczenie[] tmp = (Polaczenie[])  new Object[pojemnosc];

        for (int i = 0; i < wszystkie; i++)
            tmp[i] = arr[(pierwszy + i) % arr.length];

        arr = tmp;
        pierwszy = 0;
        nastepny = wszystkie;
    }

    public  QueueArray<Polaczenie> dodajElement (Polaczenie oczekujacePolaczenia) {
        if (arr.length == wszystkie) zmienRozmiar(arr.length*2);
        arr[nastepny++] = oczekujacePolaczenia;
        if (nastepny == arr.length) nastepny = 0;
        wszystkie++;
        return  this;
    }

    public Polaczenie pobierzElement() {
        if (wszystkie == 0) throw  new java.util.NoSuchElementException();
        Polaczenie oczekujacePolaczenia = arr[pierwszy];
        arr[pierwszy] = null;
        if (++pierwszy == arr.length) pierwszy = 0;
        if (--wszystkie > 0 && wszystkie == arr.length/4 ) zmienRozmiar(arr.length/2);
        return  oczekujacePolaczenia;

    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(arr);
    }
}


