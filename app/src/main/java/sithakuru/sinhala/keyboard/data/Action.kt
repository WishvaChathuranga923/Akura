package sithakuru.sinhala.keyboard.data

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    Action.ACTION, 
    Action.BACKSPACE, 
    Action.EMOJI, 
    Action.IME, 
    Action.LANG, 
    Action.SHIFT, 
    Action.SYMBOLS,
    Action.CLIPBOARD // <-- Add this here
)
annotation class Action {
    companion object {
        const val ACTION: Int = -1
        const val BACKSPACE: Int = -2
        const val EMOJI: Int = -3
        const val IME: Int = -4
        const val LANG: Int = -5
        const val SHIFT: Int = -6
        const val SYMBOLS: Int = -7
        
        const val CLIPBOARD: Int = -8 // <-- Add your new clipboard constant here
    }
}
