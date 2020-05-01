package top.royce2003.game.business

import top.royce2003.game.model.View

/**
 * 攻击能力
 */
interface Attackable: View {

    // 攻击能力
    val attackPower:Int

    fun isCollision(sufferable: Sufferable):Boolean

    fun notifyAttack(sufferable: Sufferable)
}