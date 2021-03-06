package phoenix.client.gui.diaryPages

import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import phoenix.client.gui.diaryPages.elements.DiaryChapter
import phoenix.containers.DiaryContainer
import java.util.*


class DiaryBook(val xSize: Int, val ySizeIn: Int, renderer: FontRenderer)
{
    private val openedChapters = LinkedList<DiaryChapter>()
    private var currentChapter = 0
    val font: FontRenderer = renderer

    fun next()
    {
        if (!openedChapters[currentChapter].isLast) openedChapters[currentChapter].next()
        else if(openedChapters.size > currentChapter + 1) currentChapter++
    }

    fun prev()
    {
        if (!openedChapters[currentChapter].isFirst) openedChapters[currentChapter].prev()
        else if(currentChapter - 1 >= 0) currentChapter--;
    }

    fun add(elements: Collection<DiaryChapter>) = openedChapters.addAll(elements)
    fun add(elements: DiaryChapter) = openedChapters.add(elements)

    fun render(gui: ContainerScreen<DiaryContainer>, renderer: FontRenderer, xSize: Int, ySize: Int, x: Int, y: Int, depth: Int)
    {
        if(openedChapters.size < currentChapter)
        {
            var page = openedChapters[currentChapter].currentPage1
            var sum = 0
            for (element in page)
            {
                element.render(gui, renderer, xSize, ySize, x, y + sum * (font.FONT_HEIGHT + 2), depth)
                sum += element.getHeight(xSize, ySize)
            }
            page = openedChapters[currentChapter].currentPage2
            sum = 0
            for (element in page)
            {
                element.render(gui, renderer, xSize, ySize, x + xSize / 2 - 10, y + sum * (font.FONT_HEIGHT + 2), depth)
                sum += element.getHeight(xSize, ySize)
            }
        }
    }


    operator fun inc() : DiaryBook
    {
        next()
        return this
    }

    operator fun dec() : DiaryBook
    {
        prev()
        return this
    }
}