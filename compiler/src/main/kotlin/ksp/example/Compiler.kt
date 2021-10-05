package ksp.example

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        environment.codeGenerator.createNewFile(Dependencies(false), "ksp.example", "GeneratedCode")
            .use {
                it.write("class GeneratedCode { val data = 42L }".toByteArray())
            }
        return emptyList()
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}