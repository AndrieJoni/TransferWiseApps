package parkee.parkee.transferwiseapps.ui.recipients.add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.uiModel.FieldRequirementsModel

class DynamicFieldAdapter(var data: List<FieldRequirementsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            SELECT_TYPE -> {
                return FieldSelectViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.field_select_type_item_layout, parent, false)
                )
            }
            TEXT_TYPE -> {
                return FieldTextViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.field_text_type_item_layout, parent, false)
                )
            }
            else -> {
                return FieldTextViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.field_text_type_item_layout, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == SELECT_TYPE) {
            (holder as FieldSelectViewHolder).renderView(data = data[position])
        } else if (holder.itemViewType == TEXT_TYPE) {
            (holder as FieldTextViewHolder).renderView(data = data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {

        return when (data[position].fieldType) {
            "select" -> {
                SELECT_TYPE
            }
            "text" -> {
                TEXT_TYPE
            }
            else -> {
                SELECT_TYPE
            }
        }
    }

    companion object {
        private const val SELECT_TYPE = 0
        private const val TEXT_TYPE = 1
    }
}