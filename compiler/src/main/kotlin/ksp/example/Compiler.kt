package ksp.example

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSVisitorVoid
import kotlin.reflect.KProperty1

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(MyAnnotation::class.java.name).forEach {
            it.accept(object : KSVisitorVoid() {
                override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
                    environment.logger.warn("Supers of ${classDeclaration.simpleName.asString()}: ${classDeclaration.superTypes.count()}")
                    environment.logger.warn("Annotations of ${classDeclaration.simpleName.asString()}: ${classDeclaration.annotations.joinToString { it.shortName.asString() }}")
                }
            }, Unit)
        }

        resolver.getSymbolsWithAnnotation(MyFileAnnotation::class.java.name).forEach {
            it.accept(object : KSVisitorVoid() {
                override fun visitFile(file: KSFile, data: Unit) {
                    file.annotations.forEach { ksAnnotation ->
                        val klasses = ksAnnotation.getArg<List<KSType>>(MyFileAnnotation::klasses)
                        klasses.forEach { klass ->
                            val classDeclaration = klass.declaration as KSClassDeclaration
                            environment.logger.warn("From another module - Supers of ${classDeclaration.simpleName.asString()}: ${classDeclaration.superTypes.count()}")
                            environment.logger.warn(
                                "From another module - Properties ${classDeclaration.simpleName.asString()}: ${
                                    classDeclaration.getAllProperties()
                                        .joinToString { it.simpleName.asString() + "->" + it.qualifiedName?.asString() }
                                }"
                            )
                            environment.logger.warn(
                                "From another module - Annotations of properties in ${classDeclaration.simpleName.asString()}: ${
                                    classDeclaration.getAllProperties()
                                        .firstOrNull()?.type?.resolve()?.annotations?.joinToString { it.shortName.asString() }
                                }"
                            )
                        }
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
