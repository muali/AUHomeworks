
class Complex(private val real: Double, private val imagine: Double) {

  def Re : Double = real
  def Im : Double = imagine
  def conjugation: Complex = new Complex(real, -imagine)
  def sqrt: Complex = this ^ new Complex(0.5, 0)
  def abs: Double = math.sqrt(math.pow(real, 2) + math.pow(imagine, 2))

  def unary_+ : Complex = this
  def unary_- : Complex = new Complex(-real, -imagine)
  def +(other: Complex): Complex = new Complex(real + other.real, imagine + other.imagine)
  def -(other: Complex): Complex = new Complex(real - other.real, imagine - other.imagine)

  def *(other: Complex): Complex = {
    val r = real * other.real - imagine * other.imagine
    val i = real * other.imagine + imagine * other.real
    new Complex(r, i)
  }

  def /(other: Complex): Complex = {
    val h = math.pow(other.real, 2) + math.pow(other.imagine, 2)
    val r = (real * other.real + imagine * other.imagine) / h
    val i = (other.real * imagine - other.imagine * real) / h
    new Complex(r, i)
  }

  def ^(other: Complex): Complex = {
    if (real == 0 && imagine == 0) {
      if (other.imagine != 0)
        throw new IllegalArgumentException("Complex power of zero")
      if (other.real < 0)
        throw new IllegalArgumentException("Negative power of zero")
      this
    }
    else {
      val arg = math.acos(real / abs)
      val ln = new Complex(math.log(abs), arg)
      val expPower = ln * other
      new Complex(math.exp(expPower.real) * math.cos(expPower.imagine),
                  math.exp(expPower.real) * math.sin(expPower.imagine))
    }
  }

  override def toString : String = {
    if (imagine < 0)
      real.toString + imagine.toString + "*i"
    else
      real.toString + '+' + imagine.toString + "*i"
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Complex]

  override def equals(other: Any): Boolean = other match {
    case that: Complex =>
      (that canEqual this) &&
        real == that.real &&
        imagine == that.imagine
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(real, imagine)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }


}

object Complex {
  def apply(s: String) : Complex = {
    val pattern = """([\+\-]?\d+\.?\d*)([\+\-]\d+\.?\d*)\*i$""".r
    s match {
      case pattern(re, im) => new Complex(re.toDouble, im.toDouble)
      case _ => throw new IllegalArgumentException("cannot interpret string as complex")
    }
  }

  def apply(real: Double, imagine: Double): Complex = new Complex(real, imagine)

  def unapply(value: Complex): Option[(Double, Double)] = Some(value.Re, value.Im)

}

implicit def double2complex(value: Double) : Complex = Complex(value, 0)
implicit def int2complex(value: Int) : Complex = Complex(value, 0)

val x = Complex("-5+3*i")
val y = Complex(-5, 4)
(x+y).toString
(x-y).toString
(x*y).toString
(x/y).toString
(-x).toString
(+x).toString
(x^y).toString

val Complex(r, i) = x
x^2
x^2.0


