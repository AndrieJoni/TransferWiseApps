package parkee.parkee.transferwiseapps.ui.recipients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_recipients.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.recipients.add.AddRecipientActivity

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
        initView()
        initObserver()
        onClickListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AddRecipientActivity.REQUEST_ADD_RECIPIENT &&
            resultCode == AppCompatActivity.RESULT_OK
        ) {
            recipientsViewModel.getAllRecipient()
        }
    }

    private fun initView() {

        rvRecipients.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun onClickListener() {

        btnAddRecipients.setOnClickListener {
            startActivityForResult(
                Intent(requireActivity(), AddRecipientActivity::class.java),
                AddRecipientActivity.REQUEST_ADD_RECIPIENT
            )
        }
    }

    private fun initObserver() {

        recipientsViewModel.setRecipientList.observe(viewLifecycleOwner, {

            recipientAdapter.data = it

            rvRecipients.adapter = recipientAdapter
        })
    }
}