package com.example.bottomnavigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.HomeFragment
import com.example.bottomnavigation.R
import com.example.bottomnavigation.clickinterface.ClickInterface
import com.example.bottomnavigation.clickinterface.QtyClickListener
import com.example.bottomnavigation.model.MenuUserData

class UserAdapter(
    var arraylist: ArrayList<MenuUserData>,var qtyClickListener: QtyClickListener
):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    lateinit var tvCount:TextView
    var num=0

    inner class UserViewHolder(val v : View):RecyclerView.ViewHolder(v) {
         var tvMenu:TextView
         var tvPrice:TextView
         var tvAdd:ImageView
         var tvMinus:ImageView



        init {
            tvMenu = v.findViewById<TextView>(R.id.tvMenu)
            tvAdd = v.findViewById<ImageView>(R.id.tvAdd)
            tvCount = v.findViewById(R.id.tvCount)
            tvPrice = v.findViewById(R.id.tvPrice)
            tvMinus = v.findViewById<ImageView>(R.id.tvMinus)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.spinnerlist, parent, false)
        return UserViewHolder(v)


    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val List=arraylist[position]
        holder.tvMenu.text=List.menuName
        holder.tvPrice.text= List.menuDescription

      holder.tvAdd.setOnClickListener{
          qtyClickListener.addClick(List,position)
          num++
          tvCount.setText(""+num)

      }
        holder.tvMinus.setOnClickListener{
            qtyClickListener.removeClick(List,position)
            if(num<=0){
                num=0
            }else{
                num--
                tvCount.setText(""+num)
            }
        }









    }

    override fun getItemCount(): Int {
        return arraylist.size
    }
}

