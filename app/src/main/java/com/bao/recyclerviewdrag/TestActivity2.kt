package com.bao.recyclerviewdrag

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_test2.*

class TestActivity2 : AppCompatActivity() {
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: DragAdapter

    private lateinit var callback: RecyclerTouchHelpCallBack

    private var mData = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_all.layoutManager = layoutManager


        adapter = DragAdapter(object : DragAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, data: String) {
                Toast.makeText(this@TestActivity2, data, Toast.LENGTH_SHORT).show()
            }

            override fun onItemTouchDrag(position: Int) {
            }
        })
        //增加数据
        for (i: Int in 32..126) {
            mData.add(i.toChar() + "")
        }
        adapter.addData(mData)
        rv_all.adapter = adapter


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
                //完成移动，选中的改变样式
                adapter.mData
                viewHolder.itemView.alpha = 1f
                viewHolder.itemView.scaleX = 1f
                viewHolder.itemView.scaleY = 1f
            }

            override fun remove(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
                //侧滑删除
                adapter.removeData(position)
            }
        })

        callback.edit = true
        ItemTouchHelper(callback).attachToRecyclerView(rv_all)
    }
}
