package ksp.example

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.FileLocation
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import java.io.File


class SlitMeVisitor(private val environment: SymbolProcessorEnvironment) : KSVisitorVoid() {

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        environment.logger.warn(function.declarations.joinToString { it.toString() })
        environment.logger.warn(function.location.toString())
        val fileLocation = function.location as FileLocation
        val lines = File(fileLocation.filePath).readLines().drop(fileLocation.lineNumber - 1)
        val allLines = lines.joinToString("\n")
        // Find a better way to get start/end of the method, may be possible via KSP
        val content = allLines.substringAfter("\"\"\"")
            .substringBeforeLast("\"\"\"")

        var generatedCode = "val FooBar_render = arrayOf("

        val splits = content.split("\${")
            .map { str ->
                var counter = 1
                str.forEachIndexed { index, c ->
                    if (c == '{') counter++
                    if (c == '}') counter--
                    if (counter == 0) {
                        return@map listOf(
                            str.substring(0, index),
                            str.substring(index + 1, str.length)
                        )
                    }
                }
                return@map listOf(str)
            }
        splits.forEach { s ->
            generatedCode += "\"\"\"" + s[0] + "\"\"\",\n"
            s.getOrNull(1)?.let { generatedCode += "\"\"\"$it\"\"\",\n" }
        }
        generatedCode += ")"
        environment.codeGenerator.createNewFile(
            Dependencies(false, function.containingFile!!),
            "ksp.example",
            "GeneratedCode"
        )
            .use {
                it.write(generatedCode.toByteArray())
            }
    }
}

class Compiler(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val visitor = SlitMeVisitor(environment)
        resolver.getSymbolsWithAnnotation("ksp.example.SplitMe")
            .forEach {
                it.accept(visitor, Unit)
            }
        /*
        environment.codeGenerator.createNewFile(Dependencies(false), "ksp.example", "GeneratedCode")
            .use {
                it.write("class GeneratedCode { val data = 42L }".toByteArray())
            }*/
        return emptyList()
    }
}

class CompilerProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = Compiler(environment)
}