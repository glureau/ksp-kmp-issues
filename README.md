Given an annotated interface

```
package ksp.example

@MyAnnotation
interface KspTarget {
    fun provideFooBar(): FooBar
}
```

Calling the function returnType.resolve() returns a not very helpful declaration:

```
> Task :mymodule:kspKotlinJs FAILED
w: [ksp] returnType = FooBar
w: [ksp] resolved packageName = 
w: [ksp] resolved simpleName = <Error>
w: [ksp] resolved qualifiedName = null
e: [ksp] THE END
```

To reproduce, simply use `./gradlew compileKotlinJs` from this repository. (Full compilation is broken due to other known issues.)

[Compiler.kt](compiler/src/main/kotlin/ksp/example/Compiler.kt)

[The annotated interface](mymodule/src/commonMain/kotlin/ksp/example/KspTarget.kt)

[The dependency (a basic class)](submodule/src/commonMain/kotlin/ksp/example/FooBar.kt)
