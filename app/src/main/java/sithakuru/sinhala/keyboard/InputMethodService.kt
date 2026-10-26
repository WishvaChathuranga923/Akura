package sithakuru.sinhala.keyboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Toast
import sithakuru.sinhala.keyboard.data.Action

class SithakuruInputMethodService : InputMethodService() {

    /**
     * Call this method when your clipboard key is clicked in the keyboard layout view.
     */
    fun handleAction(actionId: Int) {
        when (actionId) {
            Action.ACTION_CLIPBOARD -> {
                pasteFromClipboard()
            }
            Action.DELETE -> {
                currentInputConnection?.deleteSurroundingText(1, 0)
            }
            Action.SPACE -> {
                currentInputConnection?.commitText(" ", 1)
            }
            Action.ENTER -> {
                sendKeyChar('\n')
            }
            else -> {
                // Handle other actions
            }
        }
    }

    /**
     * Reads the primary clip text from the Android system clipboard and pastes it.
     */
    private fun pasteFromClipboard() {
        try {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            
            if (!clipboard.hasPrimaryClip()) {
                Toast.makeText(this, "Clipboard is empty", Toast.LENGTH_SHORT).show()
                return
            }

            val item: ClipData.Item? = clipboard.primaryClip?.getItemAt(0)
            val pasteText = item?.coerceToText(this)?.toString()

            if (!pasteText.isNullOrEmpty()) {
                currentInputConnection?.commitText(pasteText, 1)
            } else {
                Toast.makeText(this, "No text found in clipboard", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to paste text", Toast.LENGTH_SHORT).show()
        }
    }
}
