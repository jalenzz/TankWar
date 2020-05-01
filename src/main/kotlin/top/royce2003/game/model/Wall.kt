package top.royce2003.game.model

import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import top.royce2003.game.Config
import top.royce2003.game.business.Attackable
import top.royce2003.game.business.Blockable
import top.royce2003.game.business.Destroyable
import top.royce2003.game.business.Sufferable

/**
 * 砖墙
 * 阻塞能力
 * 挨打能力
 * 销毁能力
 */
class Wall(override val x: Int, override val y: Int):Blockable, Sufferable, Destroyable {


//

    override val width: Int = Config.block
    override val height: Int = Config.block
    override var blood: Int = 3


    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }

    override fun isDestroyed(): Boolean = blood <= 0


    override fun notifySuffer(attackable: Attackable): Array<View>? {
        println("墙收到被碰撞")

        // 掉血
        blood -= attackable.attackPower

        // 声音
        Composer.play("snd/hit.wav")



        return arrayOf(Blast(x, y))
    }


}