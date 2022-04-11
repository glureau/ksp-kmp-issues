package ksp.example

@MyAnnotation
data class Base(val data: String = "data")

@MyAnnotation
class Container {
    var base: Base = Base()
}
