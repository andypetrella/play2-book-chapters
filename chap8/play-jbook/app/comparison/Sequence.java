package comparison;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sequence {

    public static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);


    /*get*/
    Integer third = list.get(2);


    public interface Function0<A> {
        void apply(A a);
    }

    public interface Function1<A,B> {
        B apply(A a);
    }

    /*find*/
    public static abstract class Option<A> {
        public Boolean isDefined() { return this instanceof Some; }
        abstract public A get();
    }
    public static class None<A> extends Option<A> {
        public A get() { throw new RuntimeException("None.get");}
    }
    public static class Some<A> extends Option<A> {
        public A v;
        public Some(A v) { this.v = v; }
        public A get() { return v; }
    }
    public static None<Integer> NoneInt = new None<Integer>();
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


    /*exists*/
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



    /*filter*/
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


    /*map*/
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
                // DUMB => return Math.pow(2, element);
                return Math.pow(element, 2);
            }
        });
    }


    /*foreach*/
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