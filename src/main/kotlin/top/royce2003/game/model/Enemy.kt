package top.royce2003.game.model

import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config
import top.royce2003.game.business.AutoMoveable
import top.royce2003.game.business.Blockable
import top.royce2003.game.business.Moveable
import top.royce2003.game.enums.Direction
import java.util.*

class Enemy(override var x: Int, override var y: Int) :Moveable, AutoMoveable, Blockable {
    override var currentDirection: Direction = Direction.DOWN
    override val speed: Int = 8

    override val width: Int = Config.block
    override val height: Int = Config.block
    private var badDirection:Direction? = null

    override fun draw() {
        Painter.drawImage(when(currentDirection) {
            Direction.UP -> "/img/enemy_1_u.gif"
            Direction.DOWN -> "/img/enemy_1_d.gif"
            Direction.LEFT -> "/img/enemy_1_l.gif"
            Direction.RIGHT -> "/img/enemy_1_r.gif"
        }, x, y)
    }

    override fun notice(direction: Direction?, block: Blockable?) {
        this.badDirection = direction
    }

    override fun autoMove() {

        if(currentDirection == badDirection) {
            currentDirection = rdmDirction(badDirection)
            return
        }

        when(currentDirection) {
            Direction.UP    -> y -= speed
            Direction.DOWN  -> y += speed
            Direction.LEFT  -> x -= speed
            Direction.RIGHT -> x += speed
        }

        //防止越界
        if (x < 0) x = 0
        if (y < 0) y = 0
        if (x > Config.gameWith - width) x = Config.gameWith - width
        if (y > Config.gameHeight - height) y = Config.gameHeight - height
    }

    private fun rdmDirction(bad: Direction?):Direction {
        val direction = when(Random().nextInt(4)) {
            0    -> Direction.UP
            1    -> Direction.DOWN
            2    -> Direction.LEFT
            3    -> Direction.RIGHT
            else -> Direction.UP
        }

        if(direction == bad) rdmDirction(bad)

        return direction
    }
}