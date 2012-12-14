package comparison;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Filter {

    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    interface Function1<A,B> {
        B apply(A a);
    }


    public static List<Integer> filter(Function1<Integer, Boolean> p) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer i : list) {
            if (p.apply(i)) result.add(i);
        }
        return result;
    }
    //207 chars
    public static List<Integer> even() {
        return filter(new Function1<Integer, Boolean>() {
            public Boolean apply(Integer i) {
                return i % 2 == 0;
            }
        });
    }

}