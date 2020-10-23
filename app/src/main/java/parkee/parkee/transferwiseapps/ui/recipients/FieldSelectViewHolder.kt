package parkee.parkee.transferwiseapps.ui.recipients

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.field_select_type_item_layout.view.*
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.FieldRequirementsModel
import parkee.parkee.transferwiseapps.ui.FieldSelectValuesRequirementsModel

class FieldSelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun renderView(data: FieldRequirementsModel) {

        itemView.fieldsName.text = data.fieldName

        if (data.selectValues != null) {

            val values = itemView.context?.let { it1 ->
                ArrayAdapter(
                    it1,
                    R.layout.support_simple_spinner_dropdown_item,
                    data.selectValues
                )
            }

            itemView.spinnerField.adapter = values

            setSpinnerListener(data)
        }
    }

    private fun setSpinnerListener(data: FieldRequirementsModel) {

        itemView.spinnerField.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    data.selectedValues =
                        (parent?.getItemAtPosition(position) as FieldSelectValuesRequirementsModel).keyName
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }
}