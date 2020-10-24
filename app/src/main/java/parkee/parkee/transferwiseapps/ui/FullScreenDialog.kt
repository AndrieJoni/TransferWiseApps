package parkee.parkee.transferwiseapps.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import parkee.parkee.transferwiseapps.R

open class FullScreenDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransferWise_DialogStyle)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }
}