class A {

}

class B {

}

class C {
  def isC = true
}

implicit def a2b(a: A) = new B
implicit def b2c[T <% B](b: T) = new C

(new A).isC
