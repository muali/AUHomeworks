object Both {
  def unapply[T](v: T) : Option[(T, T)] = Some(v, v)
}

val Both(x, y) = 1
