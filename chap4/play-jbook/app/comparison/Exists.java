package comparison;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Exists {

    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    interface Function1<A,B> {
        B apply(A a);
    }

    public static Boolean exists(Function1<Integer, Boolean> p) {
        for (Integer i : list) {
            if (p.apply(i)) return true;
        }
        return false;
    }
    //203 chars
    public static Boolean biggerThan5() {
        return exists(new Function1<Integer, Boolean>() {
            public Boolean apply(Integer i) {
                return i > 5;
            }
        });
    }

}