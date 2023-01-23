package com.example.bottomnavigation.clickinterface

import com.example.bottomnavigation.model.MenuUserData

interface ClickInterface {
     fun editClick(menuUserData: MenuUserData,position: Int)
     fun deleteClick(menuUserData: MenuUserData,position: Int)
}
interface QtyClickListener {
     fun addClick(menuUserData: MenuUserData,position: Int)
     fun removeClick(menuUserData: MenuUserData,position: Int)
}