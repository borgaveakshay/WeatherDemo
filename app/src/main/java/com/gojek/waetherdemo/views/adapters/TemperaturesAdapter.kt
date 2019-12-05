package com.gojek.waetherdemo.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gojek.domain.entities.Temperature
import com.gojek.waetherdemo.R
import com.gojek.waetherdemo.databinding.RecyclerTempItemBinding

class TemperaturesAdapter : RecyclerView.Adapter<TemperaturesAdapter.ViewHolder>() {


    private var weatherUpdates: MutableList<Temperature> = mutableListOf()
    private lateinit var mLayoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mLayoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerTempItemBinding>(
            mLayoutInflater,
            R.layout.recycler_temp_item,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemCount() = weatherUpdates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBinding().temperature = weatherUpdates[position]
    }

    fun updateItems(updatedList: MutableList<Temperature>) {

        weatherUpdates = updatedList
        notifyDataSetChanged()
    }

    class ViewHolder(private val dataBinding: RecyclerTempItemBinding) : RecyclerView.ViewHolder(dataBinding.root) {


        fun getBinding(): RecyclerTempItemBinding = dataBinding
    }


}