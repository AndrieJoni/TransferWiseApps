package parkee.parkee.transferwiseapps.ui.transferMoney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import parkee.parkee.transferwiseapps.R

class ChooseRecipientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_recipient_for_transfer, container, false)
    }
}