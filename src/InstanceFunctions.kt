import java.io.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

class InstanceFunctions {
    var toImperial = false
    @Throws(IOException::class)
    fun setRecipe(): String {
        //create converter from user input to string
        val bufferedString = BufferedReader(InputStreamReader(System.`in`))

        //command to enter file name to user
        println("Enter the recipe name: ")

        //call to set user input to variable
        return bufferedString.readLine()
    }

    //ask user if they want imperial or metric measurements
    fun conversionType() {
        println("Enter System of recipe measurements, i for Imperial or m for metric")
        val userIn = Scanner(System.`in`)
        val userInput = userIn.next()[0]

        //change toImperial if necessary
        if (userInput == 'i') {
            toImperial = true
        }
    }

    //Might be wholly useless, unless I just want to call the recipe
    @Throws(IOException::class)
    fun readFile() {
        val s = setRecipe()
        //set file path
        val path = "files/$s.txt"

        //create instance of filestream
        val `is`: InputStream = FileInputStream(path)
        Scanner(
            `is`, StandardCharsets.UTF_8
        ).use { sc ->
            while (sc.hasNextLine()) {
                //set variable to store change and check
                var line = StringBuilder(sc.nextLine())
                //check first position of string
                //Add check to if value and if line = 0 skip
                //if first position of the string == !, do the conversion
                //if line.length > 0 append blank line to var line.
                if (line.length > 0) {
                    if (line[0] == '!') { //weight conversion
                        line = StringBuilder(line.substring(1))
                        val lineArray = line.toString().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val w = Weight()
                        lineArray[0] = w.compareWeight(toImperial, lineArray[0].toDouble()).toString()
                        lineArray[1] = w.convertSystem(toImperial)
                        //reset line to empty string
                        line.setLength(0)
                        for (strLine in lineArray) {
                            line.append("$strLine ")
                        }
                    } else if (line[0] == '&') { //volume conversion
                        line = StringBuilder(line.substring(1))
                        val lineArray = line.toString().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val v = Volume()
                        lineArray[0] = v.compareVolume(toImperial, lineArray[0].toDouble()).toString()
                        lineArray[1] = v.convertSystem(toImperial)
                        line.setLength(0)
                        for (strLine in lineArray) {
                            line.append("$strLine ")
                        }
                    } else if (line[0] == '@') { //temperature conversion
                        // Currently this is converting a temperature even if it is already listed in fahrenheit; I need this to stop
                        if (toImperial && line[5] == 'F') {
                            line = StringBuilder(line.substring(1))
                            //break string
                            val lineArray = line.toString().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                            line.setLength(0)
                            for (strLine in lineArray) {
                                line.append("$strLine ")
                            }
                        } else {//delete first character
                            line = StringBuilder(line.substring(1))
                            //break string
                            val lineArray = line.toString().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                            //pass the numeric value and boolean
                            val t = Temperature()
                            lineArray[0] = t.compareTemp(toImperial, lineArray[0].toDouble()).toString()
                            //convert words to proper system
                            lineArray[1] = t.convertSystem(toImperial)
                            //reconstruct string and set = to line
                            line.setLength(0)
                            for (strLine in lineArray) {
                                line.append("$strLine ")
                            }
                        }
                        //Convert cup to floz, then floz to ml;
                    } else if (line[0] == '*') {//cups to floz, floz to ml
                        line = StringBuilder(line.substring(1))
                        val lineArray = line.toString().split(" ".toRegex()).dropLastWhile {  it.isEmpty() }.toTypedArray()
                        if (toImperial){//Do no conversion, just leave it alone.
                            line.setLength(0)
                            for (strLine in lineArray){
                                line.append("$strLine ")
                            }
                        } else {
                            val v = Volume()
                            v.cupToFlOz(lineArray[0].toDouble()).toString()
                            lineArray[0] = v.compareVolume(toImperial, lineArray[0].toDouble()).toString()
                            lineArray[1] = v.convertSystem(toImperial)
                            line.setLength(0)
                            for (strLine in lineArray) {
                                line.append("$strLine ")
                            }
                        }
                    }
                }
                //write to string
                stringOutput += """
            $line           
             
            """.trimIndent()
            }
        }
    }

    @Throws(IOException::class)
    fun writeFile() {
        val newRecipeString = BufferedReader(InputStreamReader(System.`in`))
        println("Enter new recipe name: ")

        //new recipe filename and file writer path
        val newRecipe = newRecipeString.readLine()
        val newRecipeFile = Path.of("files/$newRecipe.txt")

        //Write contents to new file
        Files.writeString(newRecipeFile, stringOutput)
    }

    companion object {
        var stringOutput = ""
    }
    //Removed line.append for empty lines, Kotlin seems to handle them okay, but is indenting the next line
    //I need to remove the indentation after an empty line.
    //I tried to add .trimMargin(), and I tried \n in my line.append()
}