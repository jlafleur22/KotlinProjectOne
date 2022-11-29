import java.math.RoundingMode

//Temperature conversions stored here
class Temperature internal constructor() {
    fun celsius(f: Double): Double {
        //this will need to pass in the value in the string
        //Formula for converting celsius to fahrenheit.
        val c = (f - 32) * .55
        //return and rounding combined for space efficiency, allow large decimal, set how many places, and rounding type, set as double return type.
        return c.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    fun fahrenheit(c: Double): Double {
        //Formula for converting celsius to fahrenheit.
        return c * 1.8 + 32
    }

    fun compareTemp(toImperial: Boolean, t: Double): Double { //User inputting imperial measurements
        return if (toImperial) {
            fahrenheit(t)
        } else {
            celsius(t)
        }
    }

    fun convertSystem(toImperial: Boolean): String {
        return if (toImperial) {
            "Fahrenheit"
        } else {
            "Celsius"
        }
    }
}