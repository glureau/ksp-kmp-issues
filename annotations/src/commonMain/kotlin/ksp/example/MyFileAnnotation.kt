package ksp.example

import kotlin.reflect.KClass

@Target(AnnotationTarget.FILE)
annotation class MyFileAnnotation(
    val klass: KClass<*>
)
