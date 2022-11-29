import java.io.IOException

object Main {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val recipe = InstanceFunctions()
        recipe.conversionType()
        recipe.readFile()
        recipe.writeFile()
    }
}