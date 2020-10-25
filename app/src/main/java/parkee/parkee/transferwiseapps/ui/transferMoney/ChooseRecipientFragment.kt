package parkee.parkee.transferwiseapps.ui.transferMoney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_choose_recipient_for_transfer.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.recipients.RecipientAdapter

class ChooseRecipientFragment : Fragment() {

    private val transferMoneyViewModel: TransferMoneyViewModel by sharedViewModel()

    private var recipientAdapter = RecipientAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_recipient_for_transfer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transferMoneyViewModel.getAllRecipient()
        initObserver()
        initListener()
    }

    private fun initListener() {

        recipientAdapter.onRecipientAdapterListener = RecipientAdapter.OnRecipientAdapterListener {
            transferMoneyViewModel.recipientChoosen(it)
        }
    }

    private fun initObserver() {

        transferMoneyViewModel.setRecipientList.observe(viewLifecycleOwner, {

            recipientAdapter.data = it

            rvRecipients.adapter = recipientAdapter
        })
    }
}