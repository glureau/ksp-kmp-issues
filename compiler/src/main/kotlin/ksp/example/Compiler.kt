package ksp.example

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.squareup.kotlinpoet.ksp.toTypeName

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbol =
            resolver.getSymbolsWithAnnotation(MyAnnotation::class.java.name).firstOrNull() ?: return emptyList()
        symbol.accept(object : KSVisitorVoid() {
            override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
                classDeclaration.getAllFunctions().forEach { func ->
                    environment.logger.warn("returnType = ${func.returnType}")
                    val resolved = func.returnType?.resolve() ?: return
                    environment.logger.warn("resolved packageName = ${resolved.declaration.packageName.asString()}")
                    environment.logger.warn("resolved simpleName = ${resolved.declaration.simpleName.asString()}")
                    environment.logger.warn("resolved qualifiedName = ${resolved.declaration.qualifiedName?.asString()}")
                }
            }
        }, Unit)
        return emptyList()
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}