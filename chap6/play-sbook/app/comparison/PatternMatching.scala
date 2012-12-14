package comparison

object PatternMatching {
    
    //204 chars
    def matchOnInt(value:Int) = 
        value match {
            case 1 => "one"
            case 2 => "two"
            case 3 => "three"
            case 4 => "four"
            case _ => "many"
        }


   





    //441 chars
    case class Data(i:Int, b:Boolean) {}
    def matchOnDomainObject(value:Data) =
        value match {
            case Data(1, true)            => "first"
            case Data(2, true)            => "second"
            case Data(3, true)            => "third"
            case Data(x, true) if x < 100 => "correct"
            case Data(_, true)            => "finished"
            case Data(_, false)           => "disqualified"
        }

}