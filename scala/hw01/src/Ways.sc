import scala.annotation.tailrec

@tailrec
def waysCount(n: Int, k: Int, prev: Option[List[Int]] = None)
    : Int =
{
  val prevVal = prev.getOrElse(List.fill(k - 1)(0) :+ 1)
  if (n == 1) prevVal.last
  else waysCount(n - 1, k, Some(prevVal.tail :+ prevVal.sum))
}


assert(waysCount(1, 1) == 1)
assert(waysCount(2, 1) == 1)
assert(waysCount(5, 2) == 5)

val l = List(1, 2, 3)
l.tail
l :+ 4
