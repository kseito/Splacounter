package kztproject.jp.splacounter.viewmodel

import android.databinding.InverseMethod

object BindingUtils {

    @JvmStatic
    @InverseMethod("toInt")
    fun toString(value: Int): String {
        return if (value == 0) "" else value.toString()
    }

    @JvmStatic
    fun toInt(value: String): Int {
        return if (value.isEmpty()) 0 else Integer.parseInt(value)
    }
}
