package parkee.parkee.transferwiseapps.ui.recipients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_recipients.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R

class RecipientsFragment : Fragment() {

    private val recipientsViewModel: RecipientsViewModel by viewModel()

    private var recipientAdapter = RecipientAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipientsViewModel.getAllRecipient()
        initObserver()
        onClickListener()
    }

    private fun onClickListener() {

        btnAddRecipients.setOnClickListener {
            startActivity(Intent(requireActivity(), AddRecipientActivity::class.java))
        }

        recipientAdapter.onRecipientAdapterListener = RecipientAdapter.OnRecipientAdapterListener {
            //transferMoneyViewModel.recipientChoosen(it)
        }
    }

    private fun initObserver() {

        recipientsViewModel.setRecipientList.observe(viewLifecycleOwner, {

            recipientAdapter.data = it

            rvRecipients.adapter = recipientAdapter
        })
    }


}