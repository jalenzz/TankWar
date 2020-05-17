package top.royce2003.game.business

import top.royce2003.game.Config
import top.royce2003.game.enums.Direction
import top.royce2003.game.model.View

interface Moveable:View {

    val currentDirection:Direction
    val speed:Int

    /**
     * 判断碰撞
     *
     * @return direction. if it's null,have no Collison
     */
    fun willCollision(block: Blockable): Direction? {
        var x:Int = this.x
        var y:Int = this.y

        when(currentDirection) {
            Direction.UP    -> y -= speed
            Direction.DOWN  -> y += speed
            Direction.LEFT  -> x -= speed
            Direction.RIGHT -> x += speed
        }

        if (x < 0) return Direction.LEFT
        if (y < 0) return Direction.UP
        if (x > Config.gameWith - width) return Direction.RIGHT
        if (y > Config.gameHeight - height) return Direction.DOWN

        val collision:Boolean = checkCollision(block.x, block.y, block.width, block.height,
                                      x ,y, width, height)

        return if (collision) currentDirection else null
    }
    fun notice(direction: Direction?, block: Blockable?)
}