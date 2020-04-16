package top.royce2003.game.business

import top.royce2003.game.model.View

/**
 * 销毁
 */
interface Destroyable:View {

    fun isDestroyed():Boolean

}