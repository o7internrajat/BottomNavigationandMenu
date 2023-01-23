package com.example.bottomnavigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.NotificationFragment
import com.example.bottomnavigation.R
import com.example.bottomnavigation.clickinterface.ClickInterface
import com.example.bottomnavigation.clickinterface.QtyClickListener
import com.example.bottomnavigation.model.MenuUserData

class MenuUserAdapter(
    var c: Context,
    var uList: ArrayList<MenuUserData>,var  clickInterface: ClickInterface

):
    RecyclerView.Adapter<MenuUserAdapter.ViewHolder>() {
   inner  class ViewHolder(val v:View): RecyclerView.ViewHolder(v) {

       var tvMenu: TextView
       var tvDescriptionView: TextView
       var tvEdit: TextView
       var ivDelete: ImageView

       init {
           tvMenu = v.findViewById<TextView>(R.id.tvMenu)
           tvDescriptionView = v.findViewById<TextView>(R.id.tvDescriptionView)
           tvEdit = v.findViewById<TextView>(R.id.tvEdit)
           ivDelete = v.findViewById<ImageView>(R.id.ivDelete)
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.listmenu,parent,false)
        return ViewHolder(v)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newList=uList[position]
        holder.tvMenu.text=newList.menuName
        holder.tvDescriptionView.text=newList.menuDescription

        holder.ivDelete.setOnClickListener{

            clickInterface.deleteClick(newList,position)
        }

        holder.tvEdit.setOnClickListener{
            clickInterface.editClick(newList,position)
        }
    }

    override fun getItemCount(): Int {
        println("Menu Array List size - "+ uList.size)
        return uList.size

    }


}
