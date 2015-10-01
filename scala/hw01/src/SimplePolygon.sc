import scala.math.pow

case class Point(x: Double, y: Double)
{
}




def polygonPerimeterAndArea(polygon: List[Point])
    : (Double, Double) = {
  assert(polygon.size > 2)
  val edges = (1 until polygon.size).map(i => (polygon(i), polygon(i - 1))) :+
    (polygon.head, polygon.last)

  val p = edges.foldLeft(0d) {
    (cp, e) => cp + math.sqrt(pow(e._1.x - e._2.x, 2d)
                            + pow(e._1.y - e._2.y, 2d))
  }

  val s = edges.foldLeft(0d) {
    (cs, e) => cs + (e._1.x - e._2.x) * (e._1.y + e._2.y) / 2d
  }
  (p, s)
}

val polygon: List[Point] = List(Point(0d, 1d), Point(1d, 1d), Point(1d, 0d))

polygonPerimeterAndArea(polygon)
polygonPerimeterAndArea(polygon :+ Point(0d, 0d))