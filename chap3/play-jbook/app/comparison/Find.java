package comparison;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Find {

    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);


    interface Function1<A,B> {
        B apply(A a);
    }

    public static abstract class Option<A> {
        public Boolean isDefined() { return this instanceof Some; }
    }
    public static class None<A> extends Option<A> {}
    public static None<Integer> NoneInt = new None<Integer>();
    public static class Some<A> extends Option<A> {
        public A v;
        public Some(A v) { this.v = v; }
    }
    public static Option<Integer> find(Function1<Integer, Boolean> p) {
        for (Integer i : list) {
            if (p.apply(i)) return new Some<Integer>(i);
        }
        return NoneInt;
    }
    //205 chars
    public static Option<Integer> fetch3() {
        return find(new Function1<Integer, Boolean>() {
            public Boolean apply(Integer i) {
                return i == 3;
            }
        });
    }   

}