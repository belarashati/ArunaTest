package com.example.arunatest.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.arunatest.DetailsActivity
import com.example.arunatest.R
import com.example.arunatest.models.DataModel
import kotlinx.android.synthetic.main.data_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class DataAdapter (private var listTitle: List<String> = ArrayList()): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable
{

    private var items: List<String> = ArrayList()
    private var  allDatas: List<DataModel> = ArrayList()
    lateinit var mContext :Context

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        items = listTitle
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(dataList: List<String>, allDataList: List<DataModel>){
        items = dataList
        allDatas = allDataList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tvTitle.text = items[position]


        holder.itemView.setOnClickListener {
            var bodyDetail = ""
            for (i in allDatas){
                if(items[position] == i.title){
                    bodyDetail = i.body
                    break
                }
            }
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("passselected", items[position])
            intent.putExtra("bodyData", bodyDetail)
            mContext.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.data_list_item, parent, false)
        val sch = DataHolder(view)
        mContext = parent.context
        return sch
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    items = listTitle
                } else {
                    val resultList = ArrayList<String>()
                    for (row in listTitle) {
                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    items = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = items
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}