package com.example.foodorder.feature.customize.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorder.R
import com.example.foodorder.feature.customize.dto.ExcludeItem
import com.example.foodorder.feature.customize.dto.VariantGroup
import com.example.foodorder.feature.customize.OnOrderCustomized
import kotlinx.android.synthetic.main.activity_main.*

class VariationGroupAdapter(
    private val data: List<VariantGroup>,
    private val excludeItem: List<List<ExcludeItem>>,
    private val listener: OnOrderCustomized
) :
    RecyclerView.Adapter<VariationGroupAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val singleSelectView = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_select_list, parent, false) as View
        return ViewHolder(singleSelectView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvGroupTitle.text = data[position].name
        holder.rvVariation.apply {
            adapter = VariationAdapter(
                data[position].group_id,
                data[position].variations,
                excludeItem,
                listener
            )
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGroupTitle: TextView = view.findViewById(R.id.tvGroupTitle)
        val rvVariation: RecyclerView = view.findViewById(R.id.rvVariation)
    }

    override fun getItemCount() = data.size
}