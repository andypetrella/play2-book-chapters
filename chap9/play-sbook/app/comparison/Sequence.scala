package comparison




object Sequence {


    val seq = Seq(1, 2, 3, 4, 5)


    /*apply*/
    val third = seq(2)


    /*find*/
    //17 chars
    def fetch3 = seq find (_ == 3)








    /*exists*/
    /*exists*/
    //36 chars
    def biggerThan5 = seq exists (_ > 5)










    /*filter*/
    //29 chars
    def even = seq.filter(_%2==0)








    /*map*/
    //40 chars
    def squaredSeq = seq.map(math.pow(_, 2))








    /*foreach*/
    //87 chars
    def printSeq =
        seq.foreach {
            element => println(element)
        }


}