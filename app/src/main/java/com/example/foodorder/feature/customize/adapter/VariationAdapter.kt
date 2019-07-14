package com.example.foodorder.feature.customize.adapter;

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorder.R
import com.example.foodorder.feature.customize.dto.ExcludeItem
import com.example.foodorder.feature.customize.dto.Variation
import com.example.foodorder.feature.customize.OnOrderCustomized
import com.example.foodorder.util.VariationUtil

class VariationAdapter(
    var group_id: String,
    private var data: List<Variation>,
    private val excludeItem: List<List<ExcludeItem>>,
    private val listener: OnOrderCustomized
) :
    RecyclerView.Adapter<VariationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val singleSelectView = LayoutInflater.from(parent.context)
            .inflate(R.layout.variation_item, parent, false) as View
        return ViewHolder(singleSelectView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.rbTitle.text = data[position].name
        when {
            data[position].isVeg == 1 -> holder.ivVegNonVeg.setImageResource(R.drawable.ic_veg_icon)
            else -> holder.ivVegNonVeg.setImageResource(R.drawable.ic_non_veg_icon)
        }
        holder.tvPrice.text = data[position].price.toString()

        holder.rbTitle.isEnabled = data[position].inStock == 1 //disable if in not in stock

        holder.rbTitle.isChecked = data[position].default == 1

        holder.rbTitle.setOnClickListener {
            updateSelection(data, position, group_id)
        }
        holder.itemView.setOnClickListener {
            updateSelection(data, position, group_id)
        }

        data.forEach {
            if (it.default == 1) {
                listener.setSelection(group_id, it)
            }
        }
    }

    private fun updateSelection(
        variation: List<Variation>,
        position: Int,
        group_id: String
    ) {

        val prevSelection = listener.getSelection()

        val currentSelection = prevSelection
        currentSelection[group_id] = variation[position]

        //check current selection
        if (VariationUtil.verifySelection(currentSelection, excludeItem)) {
            listener.setSelection(group_id, variation[position])
            variation.forEachIndexed { index, item ->
                if (index == position) {
                    variation[index].default = 1
                } else {
                    variation[index].default = 0
                }
            }
            data = variation

        } else {
            listener.onInvalidSelection()
        }
        notifyDataSetChanged()

    }




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rbTitle: RadioButton = view.findViewById(R.id.rbTitle)
        val ivVegNonVeg: ImageView = view.findViewById(R.id.ivVegNonVeg)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
    }

    override fun getItemCount() = data.size
}