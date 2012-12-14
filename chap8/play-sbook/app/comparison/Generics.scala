package comparison



object Generics {

    //48 chars
    case class NonEmptyList[G](h:G, rest:List[G]) {}










    //277 chars
    trait Ser {
        def export:Array[Byte]
    }
    trait Writeable[El <: Ser] {
        def write(el:El)
    }
    trait AdaptableWriter[W <: Writeable[El], El <: Ser] {
        def target:W

        def adapt(el:El):El

        def out(el:El) = target.write(adapt(el))
    }




    //WILL COMPILE -- now, search the web for Functor ;-)
    trait Functor[F[_]] {
        def fmap[A, B](a:F[A], f:A=>B):F[B]
    }

    //an instance of a Functor... Scala provides it for all traversable: map
    object ListFunctor extends Functor[List] {
        def fmap[A,B](a:List[A], f:A=>B) = 
            for (item <- a) yield f(item)
    }

}