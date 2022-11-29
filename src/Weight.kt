import java.math.RoundingMode

//Weight conversions held here
class Weight internal constructor() {
    fun ouncesToGrams(oz: Double): Double {
        //Formula for converting celsius to fahrenheit.
        val g = oz * 28.34952
        return g.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    fun gramsToOunces(g: Double): Double {
        //Formula for converting celsius to fahrenheit.
        val oz = g * .03527396
        return oz.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    fun poundsToOunces(lb: Double): Double {
        //Formula for converting celsius to fahrenheit.
        val oz = lb * 16.00
        return oz.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    fun compareWeight(toImperial: Boolean, w: Double): Double { //User inputting imperial measurements
        return if (toImperial) {
            gramsToOunces(w)
        } else {
            ouncesToGrams(w)
        }
    }

    fun convertSystem(toImperial: Boolean): String {
        return if (toImperial) {
            "ounces"
        } else {
            "grams"
        }
    }
}