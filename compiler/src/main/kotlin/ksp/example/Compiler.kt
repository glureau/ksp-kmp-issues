package ksp.example

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbol =
            resolver.getSymbolsWithAnnotation(MyAnnotation::class.java.name).firstOrNull() ?: return emptyList()
        symbol.accept(object : KSVisitorVoid() {
            override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
                classDeclaration.getAllFunctions().forEach { func ->
                    if (func.returnType.toString() == "FooBar") {
                        environment.logger.warn("returnType = ${func.returnType}")
                        val resolved = func.returnType?.resolve() ?: return
                        environment.logger.warn("resolved packageName = ${resolved.declaration.packageName.asString()}")
                        environment.logger.warn("resolved simpleName = ${resolved.declaration.simpleName.asString()}")
                        environment.logger.warn("resolved qualifiedName = ${resolved.declaration.qualifiedName?.asString()}")
                        environment.logger.error("THE END")
                    }
                }
            }
        }, Unit)
        return emptyList()
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}