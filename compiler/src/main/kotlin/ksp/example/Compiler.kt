package ksp.example

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.*
import kotlin.reflect.KProperty1

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(MyAnnotation::class.java.name).forEach {
            it.accept(object : KSVisitorVoid() {
                override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
                    environment.logger.warn("Supers of ${classDeclaration.simpleName.asString()}: ${classDeclaration.superTypes.count()}")
                }
            }, Unit)
        }

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

        return emptyList()
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}


@Suppress("UNCHECKED")
fun <T> KSAnnotation.getArg(kProp: KProperty1<*, *>) =
    arguments.first { it.name?.asString() == kProp.name }.value as T
