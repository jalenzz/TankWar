package top.royce2003.game.business

import top.royce2003.game.model.View

/**
 * 遭受攻击
 */
interface Sufferable:View {

    // 生命值
    val blood:Int

    fun notifySuffer(attackable: Attackable): Array<View>?

}