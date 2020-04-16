package top.royce2003.game.model

import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config
import top.royce2003.game.business.AutoMoveable
import top.royce2003.game.business.Destroyable
import top.royce2003.game.enums.Direction

class Bullet(override val currentDirecton: Direction,
             create:(width:Int, height:Int) -> Pair<Int, Int>
) : AutoMoveable, Destroyable {

    override val speed: Int = 8

    override var x: Int = 0
    override var y: Int = 0
    override val width: Int
    override val height: Int

    private val imagePath:String = when(currentDirecton) {
        Direction.UP -> "/img/shot_top.gif"
        Direction.DOWN -> "/img/shot_bottom.gif"
        Direction.LEFT -> "/img/shot_left.gif"
        Direction.RIGHT -> "/img/shot_right.gif"
    }


    init {
        val size:Array<Int> = Painter.size(imagePath)
        width = size[0]
        height = size[1]

        val pair:Pair<Int,Int> = create.invoke(width,height)
        x = pair.first
        y = pair.second
    }



    override fun draw() {
        Painter.drawImage(imagePath, x, y)
    }


    override fun autoMove() {
        when(currentDirecton) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
    }

    // 脱离屏幕后销毁子弹
    override fun isDestroyed(): Boolean {

        if (x < -width ) return true
        if (y < -height ) return true
        if (x > Config.gameWith) return true
        if (y > Config.gameHeight ) return true

        return false
    }
}