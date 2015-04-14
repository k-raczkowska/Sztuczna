import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Squad2<A> {

    List<A> employees;
    A[] players2;

    Squad2() {
        employees = new ArrayList<A>();
    }

    public A[] x(Class<A> aClass, int c) {
        @SuppressWarnings("unchecked")
        final A[] a = (A[]) Array.newInstance(aClass, c);
        this.players2 = a;
        return a;
    }
}
