package ksp.example

@MyAnnotation
fun interface Base<T> {
    fun provide(): T
}

@MyAnnotation
interface Advanced<T> : Base<T>

class Container {
    var base: Base<Int> = Base { 42 }
}
