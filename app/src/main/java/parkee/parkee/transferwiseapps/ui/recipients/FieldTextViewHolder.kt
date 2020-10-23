package parkee.parkee.transferwiseapps.ui.recipients

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.field_text_type_item_layout.view.*
import parkee.parkee.transferwiseapps.ui.FieldRequirementsModel

class FieldTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(data: FieldRequirementsModel) {

        itemView.textInputEditTextCustom.hint = data.fieldName

        itemView.textInputEditTextCustom.addTextChangedListener {
            data.selectedValues = it.toString()
        }
    }
}