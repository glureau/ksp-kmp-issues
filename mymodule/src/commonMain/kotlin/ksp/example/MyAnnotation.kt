package ksp.example

// For some reasons, KMP/KSP doesn't work well with the annotation module dependency
// Even if the class with the exact same name is imported for commonMain or for jsMain, and the class is effectively accessible
// when asking KSP to retrieve the annotated classes, it returns nothing for js (indeed it returns something for jvm project...)
// Not sure if there is a ticket for that, maybe KSP 1.0.1 already fixed that.
annotation class MyAnnotation
