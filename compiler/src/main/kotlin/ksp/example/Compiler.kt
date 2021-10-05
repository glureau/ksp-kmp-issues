package ksp.example

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbol =
            resolver.getSymbolsWithAnnotation(MyAnnotation::class.java.name).firstOrNull() ?: return emptyList()
        symbol.accept(object : KSVisitorVoid() {
            override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
                environment.codeGenerator.createNewFile(Dependencies(false), "ksp.example", "GeneratedCode")
                    .use {
                        it.write("class GeneratedCode { val data = 42L }".toByteArray())
                    }
            }
        }, Unit)
        return emptyList()
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}