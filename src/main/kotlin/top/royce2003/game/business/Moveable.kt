package top.royce2003.game.business

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
    fun willCollision(block: Blockable): Direction?
    fun notice(direction: Direction?, block: Blockable?)
}