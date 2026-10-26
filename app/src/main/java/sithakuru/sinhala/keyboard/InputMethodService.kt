package sithakuru.sinhala.keyboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Toast
import sithakuru.sinhala.keyboard.data.Action

class SithakuruInputMethodService : InputMethodService() {

    // If your service class has a different name (e.g., just InputMethodService), 
    // keep your class name and replace the contents inside it with this logic.

    /**
     * Handle actions coming from key clicks, including the new CLIPBOARD action.
     */
    fun handleAction(action: Int) {
        when (action) {
            Action.CLIPBOARD -> {
                pasteFromClipboard()
            }
            // Keep your other action handlers here (like Backspace, Space, Shift, etc.)
            else -> {
                // Default handling
            }
        }
    }

    /**
     * Reads text from the Android system clipboard and inserts it into the active text field.
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
