package controllers

import org.scalatest.FunSuite

class SampleTest extends FunSuite {

  val num1 = 1
  val num2 = 2
  val num3 = 1

  test ("Failure test") {
    assert(num1 === num2)
  }

  test ("Success test") {
    assert(num1 === num3)
  }
}
