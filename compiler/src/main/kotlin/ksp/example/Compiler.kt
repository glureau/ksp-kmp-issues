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
                    logProperties(classDeclaration)
                }
            }, Unit)
        }

        resolver.getSymbolsWithAnnotation(MyFileAnnotation::class.java.name).forEach {
            it.accept(object : KSVisitorVoid() {
                override fun visitFile(file: KSFile, data: Unit) {
                    file.annotations.forEach { ksAnnotation ->
                        val classes = ksAnnotation.getArg<List<KSType>>(MyFileAnnotation::klasses)
                        classes.forEach { klass ->
                            val classDeclaration = klass.declaration as KSClassDeclaration
                            logProperties(classDeclaration)
                        }
                    }
                }
            }, Unit)
        }

        return emptyList()
    }

    fun logProperties(classDeclaration: KSClassDeclaration) {
        environment.logger.warn("Properties of ${classDeclaration.simpleName.asString()}")
        classDeclaration.getAllProperties().forEach { prop ->
            val annotations = prop.type.resolve().declaration.annotations
            val annotationsStr = annotations.joinToString { it.shortName.asString() }
            environment.logger.warn(" - ${prop.simpleName.asString()}: annotations=$annotationsStr")
        }
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}


@Suppress("UNCHECKED")
fun <T> KSAnnotation.getArg(kProp: KProperty1<*, *>) =
    arguments.first { it.name?.asString() == kProp.name }.value as T
