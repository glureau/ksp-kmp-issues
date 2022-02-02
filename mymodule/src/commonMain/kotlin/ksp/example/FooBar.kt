package ksp.example

annotation class SplitMe

val a = """
    ${"a" + """ another ${""" inception""" + "level"}"""}
""".trimIndent()

class FooBar {
    data class Item(val id: Int, val name: String)

    private val items = listOf(
        Item(0, "Justin"),
        Item(1, "Steve"),
        Item(2, "Kevin"),
        Item(3, "Russell"),
        Item(4, "Liz"),
        Item(5, "Peter"),
    )

    @SplitMe
    fun render() = """<h3>repeat directive example</h3>
      <button @click=${items.sortedBy { it.name }}>Sort ascending</button>
      <button @click=${items.sortedByDescending { it.name }}>Sort descending</button><hr>
      With keying (DOM including checkbox state moves with items):
      <ul>
    ${
        items.joinToString { item ->
            "<li><label><input type=\"checkbox\">" + item.name + "</label></li>`)"
        }
    }
      Without keying (items are re - used in place, checkbox state does not change):
      <ul >
            blablabla
      </ul >
    """

    private fun notUsed() {

    }
}
