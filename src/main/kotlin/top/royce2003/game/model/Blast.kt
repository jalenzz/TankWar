package top.royce2003.game.model

import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config
import top.royce2003.game.business.Destroyable

/*
 * 爆炸物
 */
class Blast(override val x: Int, override val y: Int) :Destroyable {


    override val width: Int = Config.block
    override val height: Int = Config.block
    private val imagePaths = arrayListOf<String>()
    private var index:Int = 0 // 图片下标


    init {
        (1..32).forEach {
            imagePaths.add("img/blast_${it}.png")
        }
    }

    override fun draw() {

        // 防止越界
        val i = index % imagePaths.size

        Painter.drawImage(imagePaths[i], x, y)

        index++

    }

    // 销毁爆炸物
    override fun isDestroyed(): Boolean {
        return index > imagePaths.size
    }
}