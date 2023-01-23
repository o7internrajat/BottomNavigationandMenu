package com.example.bottomnavigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.adapter.UserAdapter
import com.example.bottomnavigation.clickinterface.QtyClickListener
import com.example.bottomnavigation.model.MenuUserData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), QtyClickListener {

    lateinit var mainActivity: MainActivity
    lateinit var spnAdd: Spinner
    lateinit var btnAdd: Button
    lateinit var recyclerView: RecyclerView
    lateinit var tvPriceSet:TextView

    lateinit var userAdapter: UserAdapter
    var selectedPosition = -1
    var userList = ArrayList<MenuUserData>()


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        btnAdd = view.findViewById(R.id.btnAdd)
        tvPriceSet=view.findViewById(R.id.tvPriceSet)

        userAdapter = UserAdapter(userList, this)

        spnAdd = view.findViewById(R.id.spnAdd)
        val spinnerAdapter = ArrayAdapter<MenuUserData>(
            mainActivity,
            android.R.layout.simple_list_item_1, mainActivity.arrayList
        )
        println("data" + spinnerAdapter)
        spnAdd.adapter = spinnerAdapter



        spnAdd.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                p3: Long
            ) {
                selectedPosition = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        recyclerView.adapter = userAdapter
        btnAdd.setOnClickListener {
            userList.add(mainActivity.arrayList.get(selectedPosition))
            println("Menu userlist" + userList)
            userAdapter.notifyDataSetChanged()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun addClick(menuUserData: MenuUserData, position: Int) {
        userList[selectedPosition].qty=userList[selectedPosition].qty+1



        userAdapter.notifyDataSetChanged()
        Price()
    }

    override fun removeClick(menuUserData: MenuUserData, position: Int) {
        userList[selectedPosition].qty=userList[selectedPosition].qty-1

        Price()
    }

    fun Price(){
        var tPrice=0.0
        for(i in userList) {
            tPrice = tPrice + (i.menuDescription.toDouble().times(i.qty))
        }

        tvPriceSet.setText(tPrice.toString())
    }
}
