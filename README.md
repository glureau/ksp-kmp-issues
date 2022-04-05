# KSP 1.6.20-1.0.4

### Processing from same module: OK

Given an interface extending another interface in `mymodule` ([Base.kt](mymodule/src/commonMain/kotlin/ksp/example/Base.kt))

```
package ksp.example

@MyAnnotation
interface Base<T> {
    fun provide(): T
}

@MyAnnotation
interface Advanced<T> : Base<T>

```

Using KSP on the `mymodule` will properly resolve `Advanced` super.

```
> Task :mymodule:kspKotlinMetadata
> w: [ksp] Supers of Advanced: 1
```

---

# Processing from another module: KO

If I wrote another module and define an annotation that points to `Advanced` class ([here](anothermodule/src/commonMain/kotlin/ksp/example/FooBar.kt))

```kotlin
@file:MyFileAnnotation(
    klass = Advanced::class
)

package ksp.example

```
And if I define my [processor](compiler/src/main/kotlin/ksp/example/Compiler.kt) this way:

```kotlin
        resolver.getSymbolsWithAnnotation(MyFileAnnotation::class.java.name).forEach {
            it.accept(object : KSVisitorVoid() {
                override fun visitFile(file: KSFile, data: Unit) {
                    file.annotations.forEach { ksAnnotation ->
                        val klass = ksAnnotation.getArg<KSType>(MyFileAnnotation::klass)
                        val classDeclaration = klass.declaration as KSClassDeclaration
                        environment.logger.warn("From another module - Supers of ${classDeclaration.simpleName.asString()}: ${classDeclaration.superTypes.count()}")
                    }
                }
            }, Unit)
        }
```

then it's logging that Advanced have no supers.

>[ksp] From another module - Supers of Advanced: 0

I was expecting to found the Base class here, like for the 1st example.
