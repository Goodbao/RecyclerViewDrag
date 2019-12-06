package com.bao.recyclerviewdrag

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DragAdapter(var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mData = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DragViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DragViewHolder) {
            holder.onBind(position, mData[position], onItemClickListener)
        }
    }

    /**
     * 添加数据
     */
    fun addData(list:List<String>){
        if(list.isNullOrEmpty()){
            return
        }
        mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * 移除数据
     */
    fun removeData(position: Int){
        mData.removeAt(position)
        notifyItemRemoved(position)
    }


    class DragViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.drag_item, parent, false)
    ) {
        var ivDrag = itemView.findViewById<ImageView>(R.id.iv_drag)
        var tvItem = itemView.findViewById<TextView>(R.id.tv_item)
        fun onBind(position: Int, data: String, onItemClickListener: OnItemClickListener) {
            tvItem.text = data
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(position, data)
            }
            //ontouch回调，不用onLongClick回调
            ivDrag.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    onItemClickListener.onItemTouchDrag(position)
                    return true
                }
            })
        }
    }

    public interface OnItemClickListener {
        fun onItemClick(position: Int, data: String)

        fun onItemTouchDrag(position: Int)
    }
}