import java.lang.reflect.Array;
import java.math.MathContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karolina_2 on 2015-04-13.
 */

public class Test{


    public static void main(String... atgs){
        @SuppressWarnings("unchecked")
        Squad<Player> inty = new Squad<>();
        Player player1 = inty.newInstance("Player");
        System.out.print(player1.imie);
        try {
            Class cl = Class.forName("Player");
            inty.x(cl, 5);
            Player player = new Player();
            inty.addToArray(0, player);
            for(int i = 0; i < inty.getPlayers2().length; i++){
                System.out.print(inty.getPlayers2()[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Player{
    String imie;
    Player(){
        imie = "Adam";
    }
}

class Squad<A> {

    private List<A> employees;
    private A[] players2;
    A a;

    public A newInstance(String name){
        try {
            Class clas = Class.forName(name);
            a = (A)clas.newInstance();
            return a;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    Squad() {
        employees = new ArrayList<A>();
    }

    public A[] x(Class<A> aClass, int c) {
        @SuppressWarnings("unchecked")
        final A[] a = (A[]) Array.newInstance(aClass, c);
        this.players2 = a;
        return a;
    }

    public A[] getPlayers2() {
        return players2;
    }

    public void addToArray(int n, A a){
        players2[n] = a;
    }
}