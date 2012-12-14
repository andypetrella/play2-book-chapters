package comparison;

import java.util.List;

class Generics {

    //204 chars
    class NonEmtpyList<G> {
        public G head;
        public List<G> rest;

        public NonEmtpyList(G head, List<G> rest) {
            this.head = head;
            this.rest = rest;
        }
    }


    //351 chars
    interface Ser {
        Byte[] export();
    }
    interface Writeable<El extends Ser> {
        void write(El ser);
    }
    abstract class AdaptableWriter<W extends Writeable<El>, El extends Ser> {
        public W target;

        public abstract El adapt(El e);

        public void out(El e) {
            target.write(adapt(e));
        }
    }


    //simulate a Function... Similar to the Command Pattern
    interface Function1<A,B> {
        B apply(A a);
    }
    //WILL FAIL AT COMPILE TIME
    // interface Functor<F<*>> {
    //     <A, B> F<B> fmap(F<A> a, Function1<A,B> f);
    // }

}