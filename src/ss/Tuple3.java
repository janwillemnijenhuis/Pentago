package ss;

/**
 * tuple class which can be used to construct a tuple of 3 different types
 * @param <K> type 1
 * @param <V> type 2
 * @param <W> type 3
 * @author janwillem.nijenhuis
 */
public class Tuple3<K, V, W> {
    private K first;
    private V second;
    private W third;

    public Tuple3(K first, V second, W third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void setFirst(K k) {this.first = k;}

    public void setSecond(V v) {this.second = v;}

    public void setThird(W w) {this.third = w;}

    public K getFirst() {return this.first;}

    public V getSecond() {return this.second;}

    public W getThird() {return this.third;}
}