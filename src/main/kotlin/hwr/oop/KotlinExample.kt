package hwr.oop

class KotlinExample {
  fun sayHello(): String {
    return "Hello World!"
  }
}

object Example {
    @JvmStatic
    fun main(args: Array<String>) {
      val example = KotlinExample()
      println(example.sayHello())
    }
}
