package hwr.oop

class KotlinExample {
  fun sayHello(): String {
    return "Hello World!"
  }
}

class Example {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      val example = KotlinExample()
      println(example.sayHello())
    }
  }
}
