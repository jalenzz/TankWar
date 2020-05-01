package top.royce2003.game.model

import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config
import top.royce2003.game.business.Blockable
import top.royce2003.game.enums.Direction
import top.royce2003.game.business.Moveable


/**
 * 我方Tank
 */
class Tank(override var x: Int, override var y: Int):Moveable {

    override val width: Int = Config.block
    override val height: Int = Config.block

    override var currentDirection:Direction = Direction.UP
    override val speed:Int = 8

    private var badDirection:Direction? = null


    override fun draw() {
        Painter.drawImage(when(currentDirection) {
            Direction.UP -> "/img/tank_u.gif"
            Direction.DOWN -> "/img/tank_d.gif"
            Direction.LEFT -> "/img/tank_l.gif"
            Direction.RIGHT -> "/img/tank_r.gif"
        }, x, y)
    }


    fun move(direction: Direction) {


        if (direction == badDirection) {
            return
        }


        if (this.currentDirection != direction) {
            this.currentDirection = direction
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


    override fun willCollision(block: Blockable): Direction? {

        var x:Int = this.x
        var y:Int = this.y

        when(currentDirection) {
            Direction.UP    -> y -= speed
            Direction.DOWN  -> y += speed
            Direction.LEFT  -> x -= speed
            Direction.RIGHT -> x += speed
        }


        val collision:Boolean = checkCollision(block.x, block.y, block.width, block.height,
                                                     x,       y,       width,       height)

        return if (collision) currentDirection else null

    }


    override fun notice(direction: Direction?, block: Blockable?) {
        this.badDirection = direction
    }

    fun shot():Bullet {

        return  Bullet(currentDirection) { bulletWidth, bulletHeight ->
            var tankX = x
            var tankY = y
            var tankWidth = width
            var tankHeight = height
            var bulletX = 0
            var bulletY = 0

            when(currentDirection) {
                Direction.UP -> {
                    bulletX = tankX + (tankWidth - bulletWidth) / 2
                    bulletY = tankY - bulletHeight / 2
                }
                Direction.DOWN -> {
                    bulletX = tankX + (tankWidth - bulletWidth) / 2
                    bulletY = tankY + tankHeight - bulletHeight / 2
                }
                Direction.LEFT -> {
                    bulletX = tankX - bulletWidth / 2
                    bulletY = tankY + (tankHeight - bulletHeight) / 2
                }
                Direction.RIGHT -> {
                    bulletX = tankX + tankWidth - bulletWidth /2
                    bulletY = tankY + (tankHeight - bulletHeight) / 2
                }
            }
            Pair(bulletX,bulletY)
        }

    }


}