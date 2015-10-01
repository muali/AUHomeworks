import scala.annotation.tailrec

def euclidianStupid(a : Int, b : Int) : (Int, Int, Int) =
  a match {
    case 0 => (b, 0, 1)
    case x if x > b => {
      val t = euclidianStupid(b, a)
      (t._1, t._3, t._2)
    }
    case _ => {
      val t = euclidianStupid(b % a, a)
      (t._1, t._3 - (b / a) * t._2, t._2)
    }
  }

@tailrec
def euclidianTR(a: Int, b: Int, x1: Int = 0, y1: Int = 1, x2: Int = 1, y2: Int = 0)
      : (Int, Int, Int) =
  a match {
    case 0 => (b, x1, y1)
    case x if x > b => euclidianTR(b, a, x2, y2, x1, y1)
    case _ => euclidianTR(b % a, a, x2, y2, x1 - (b / a) * x2, y1 - (b / a) * y2)
  }


for (i <- 1 to 100)
{
  for (j <- 1 to 100)
    assert(euclidianStupid(i, j) == euclidianTR(i, j))
}