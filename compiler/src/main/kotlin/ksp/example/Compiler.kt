package ksp.example

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbol =
            resolver.getSymbolsWithAnnotation("ksp.example.MyAnnotation")
                //.filter { it.validate() } // Fix by avoiding the file :(
                .firstOrNull() ?: return emptyList()
        environment.logger.warn("symbols = $symbol")
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
        return resolver.getSymbolsWithAnnotation(MyAnnotation::class.java.name)
            .filter { !it.validate() }.toList()
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}