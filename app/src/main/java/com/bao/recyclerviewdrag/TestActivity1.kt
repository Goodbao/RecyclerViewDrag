package com.bao.recyclerviewdrag

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_test1.*

class TestActivity1 : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DragAdapter

    private lateinit var callback: RecyclerTouchHelpCallBack

    private var mData = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_item.layoutManager = layoutManager


        adapter = DragAdapter(object : DragAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, data: String) {
                Toast.makeText(this@TestActivity1, data, Toast.LENGTH_SHORT).show()
            }

            override fun onItemTouchDrag(position: Int) {
                callback.edit = true
            }
        })
        //增加数据
        for (i: Int in 32..126) {
            mData.add(i.toChar() + "")
        }
        adapter.addData(mData)
        rv_item.adapter = adapter


        callback = RecyclerTouchHelpCallBack(object : RecyclerTouchHelpCallBack.OnHelperCallBack {
            override fun onMove(fromPosition: Int, targetPosition: Int) {
                //移动item
                callback.itemMove(adapter, adapter.mData, fromPosition, targetPosition)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder, actionState: Int) {
                //选中的改变样式
                viewHolder.itemView.alpha = 1f
                viewHolder.itemView.scaleX = 1.2f
                viewHolder.itemView.scaleY = 1.2f
            }

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                //松手后不让操作，不然会点击全部范围拖拽
                callback.edit = false
                //完成移动，选中的改变样式
                adapter.mData
                viewHolder.itemView.alpha = 1f
                viewHolder.itemView.scaleX = 1f
                viewHolder.itemView.scaleY = 1f
            }

            override fun remove(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
                // callback.edit = false，所以不会触发侧滑删除
               adapter.removeData(position)
            }
        })

        ItemTouchHelper(callback).attachToRecyclerView(rv_item)
    }
}
