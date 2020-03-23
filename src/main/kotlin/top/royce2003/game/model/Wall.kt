package top.royce2003.game.model

import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config
import top.royce2003.game.business.Blockable

/**
 * 砖墙
 */
class Wall(override val x: Int, override val y: Int):Blockable {
//

    override val width: Int = Config.block
    override val height: Int = Config.block

    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }
}