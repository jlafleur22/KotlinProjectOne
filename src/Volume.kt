import java.math.RoundingMode

//Volume conversions stored here
class Volume  //currently fluidInput as universal. Likely won't use in final build.
internal constructor() {
    //Fluid Ounce to Liter
    private fun flozToLiter(l: Double): Double {
        //Formula for converting celsius to fahrenheit.
        var oz = l * 33.81402
        return oz.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    //Liter to fluid ounce
    private fun literToFluidOunce(oz: Double): Double {
        //Formula for converting celsius to fahrenheit.
        var mL = oz * .0295735296
        return mL.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    //Cup to fluid ounce
    fun cupToFlOz(c: Double): Double {
        return c * 8.0
    }

    fun compareVolume(toImperial: Boolean, v: Double): Double { //User inputting imperial measurements
        return if (toImperial) {
            literToFluidOunce(v)
        } else {
            flozToLiter(v)
        }
    }

    fun convertSystem(toImperial: Boolean): String {
        return if (toImperial) {
            "ounces"
        } else {
            "milliliters"
        }
    }
}