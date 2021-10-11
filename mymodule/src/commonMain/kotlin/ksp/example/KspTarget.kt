package ksp.example

@MyAnnotation
interface KspTarget {
    fun provideFooBar(): FooBar
    fun hey(): String
}
