package ksp.example

@MyAnnotation
interface Base<T> {
    fun provide(): T
}

@MyAnnotation
interface Advanced<T> : Base<T>
