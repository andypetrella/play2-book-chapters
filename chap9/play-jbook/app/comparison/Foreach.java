package comparison;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Foreach {

    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);


    interface Function0<A> {
        void apply(A a);
    }

    public static void foreach(Function0<Integer> f) {
        for (Integer i : list) {
            f.apply(i);
        }
    }
    //220 chars 
    public static void printSeq() {
        foreach(new Function0<Integer>() {
            public void apply(Integer element) {
                System.out.println(element);
            }
        });
    }


}