package phoenix.utils

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.client.gui.FontRenderer
import net.minecraft.util.text.LanguageMap
import net.minecraft.util.text.TextFormatting
import java.util.*

object StringUtils
{
    fun stringToWords(s: String): ArrayList<String>
    {
        val result = ArrayList<String>()
        var current = ""
        for (i in s.indices)
        {
            if (s[i] == '\n')
            {
                result.add(current)
                result.add("[break]")
                current = ""
            } else if (s[i] == ' ' || i == s.length - 1)
            {
                result.add(current)
                current = ""
            } else
            {
                current += s[i]
            }
        }
        return result
    }

    fun translateAll(vararg strings: String): ArrayList<String>
    {
        val res = ArrayList<String>()
        for (string in strings)
        {
            res.add(LanguageMap.getInstance().func_230503_a_(string))
        }
        return res
    }

    fun translate(key: String): String
    {
        return LanguageMap.getInstance().func_230503_a_(key)
    }

    fun drawRightAlignedString(stack: MatrixStack, font: FontRenderer, string: String, x: Int, y: Int, colour: Int)
    {
        font.drawStringWithShadow(stack, string, (x - font.getStringWidth(string)).toFloat(), y.toFloat(), colour)
    }

    var rainbow = arrayOf(
        TextFormatting.RED,
        TextFormatting.YELLOW,
        TextFormatting.GREEN,
        TextFormatting.BLUE,
        TextFormatting.DARK_BLUE,
        TextFormatting.DARK_PURPLE
    )

    fun rainbowColor(string: String): String
    {
        val s = StringBuilder()
        for (i in string.indices)
        {
            if (string[i] != ' ' && string[i] != '\n') s.append(rainbow[i % rainbow.size])
            s.append(string[i])
        }
        return s.toString()
    }
}