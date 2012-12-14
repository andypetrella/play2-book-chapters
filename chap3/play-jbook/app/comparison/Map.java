package comparison;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {

    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);


    interface Function1<A,B> {
        B apply(A a);
    }

    public static <B> List<B> map(Function1<Integer, B> f) {
        List<B> result = new ArrayList<B>();
        for (Integer i : list) {
            result.add(f.apply(i));
        }
        return result;
    }
    //233 chars
    public static List<Double> squaredSeq() {
        return map(new Function1<Integer, Double>() {
            public Double apply(Integer element) {
                return Math.pow(2, element);
            }
        });
    }

}