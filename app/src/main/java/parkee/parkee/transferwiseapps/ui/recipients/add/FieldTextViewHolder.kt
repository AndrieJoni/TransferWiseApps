package parkee.parkee.transferwiseapps.ui.recipients.add

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.field_text_type_item_layout.view.*
import parkee.parkee.transferwiseapps.uiModel.FieldRequirementsModel

class FieldTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(data: FieldRequirementsModel) {

        itemView.textInputLayoutCustom.hint = data.fieldName

        itemView.textInputEditTextCustom.addTextChangedListener {
            data.selectedValues = it.toString()
        }
    }
}