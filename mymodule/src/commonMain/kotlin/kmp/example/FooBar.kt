package kmp.example

open class BaseClass(internal val internalVal: String)
class FooBar(val exposedVar: Int) : BaseClass("internalValue") {
    fun compute() = 1 + 1
}
