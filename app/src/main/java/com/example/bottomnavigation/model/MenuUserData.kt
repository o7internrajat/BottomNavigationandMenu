package com.example.bottomnavigation.model


data class MenuUserData(
    var menuName:String,
    var menuDescription: String,
    var qty: Int = 0
) {

    override fun toString(): String {
        return menuName
    }
}