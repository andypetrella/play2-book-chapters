package atomic

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import comparison.{Sequence}

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import java.util.ArrayList

//base class => Specification
class ComparisonSpec extends Specification {
  // start a logically grouped specs
  "Sequence " should {
    // test a particular use case
    "return even integers using 'even'" in {
      val l = Sequence.even.toList
      l must be_==(List(2, 4))
    }
    // and another one
    "contain all squares using 'squaredSeq'" in {
      val l = Sequence.squaredSeq.toList
      // !!! Fails !!!
      l must be_==(List(1.0, 4, 9, 16, 25))
    }
    "return even integers using 'even' (list independent)" in {
      val l = Sequence.even.toList
      // all elements are even ! not matter if the inner List changes
      l must haveAllElementsLike {case i => (i%2) must be_==(0)}
    }
    "return something when using 'fetch3'" in {
      val three = Sequence.fetch3
      // 3 can be found
      ((three.isDefined:Boolean) must beTrue) and
      //and get it from option won't throw "None.get"
      (three.get must not throwA(new RuntimeException()))
    }
    "return false when using 'biggerThan5''" in {
      val big:Boolean = Sequence.biggerThan5
      //none of the list's elements is bigger than 5
      big must beFalse
    }
  }
}