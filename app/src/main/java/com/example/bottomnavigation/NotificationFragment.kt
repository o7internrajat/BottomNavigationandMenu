package com.example.bottomnavigation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.adapter.MenuUserAdapter
import com.example.bottomnavigation.clickinterface.ClickInterface
import com.example.bottomnavigation.model.MenuUserData
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//@Suppress("UNREACHABLE_CODE")
class NotificationFragment : Fragment(),ClickInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var btnAdding: FloatingActionButton
    lateinit var menuUserAdapter: MenuUserAdapter
    lateinit var mainActivity: MainActivity
    lateinit var etItem: EditText
    lateinit var btnAddItem: Button
    lateinit var btnCancelItem: Button
    lateinit var etDescription: EditText


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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        btnAdding = view.findViewById<FloatingActionButton>(R.id.btnAdding)
        recyclerView = view.findViewById(R.id.recyclerView)

        println("Menu Array List" + mainActivity.arrayList)
        menuUserAdapter = MenuUserAdapter(mainActivity, mainActivity.arrayList, this)
        recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        recyclerView.adapter = menuUserAdapter

        btnAdding.setOnClickListener {


            val inflater = LayoutInflater.from(mainActivity)
            val v = inflater.inflate(R.layout.fragment_dialog, null)


            val addDialog = Dialog(mainActivity)
            addDialog.setContentView(v)
            addDialog.create()
            addDialog.show()

            etItem = v.findViewById(R.id.etItem)
            btnAddItem = v.findViewById<Button>(R.id.btnAddItem)
            btnCancelItem = v.findViewById<Button>(R.id.btnCancelItem)
            etDescription = v.findViewById(R.id.etDescription)

            btnAddItem.setOnClickListener {

                val item = etItem.text.toString()
                val des = etDescription.text.toString()
                if (etItem.text.isEmpty()) {
                    etItem.setError("Type Menu")
                } else if (etDescription.text.isEmpty()) {
                    etDescription.setError("Type Price")
                } else {

                    mainActivity.arrayList.add(MenuUserData("$item", "$des"))
                    menuUserAdapter.notifyDataSetChanged()
                    Toast.makeText(mainActivity, "Add Information", Toast.LENGTH_SHORT).show()
                    addDialog.dismiss()
                }
            }

            btnCancelItem.setOnClickListener {
                Toast.makeText(mainActivity, "Cancel", Toast.LENGTH_SHORT).show()
                addDialog.dismiss()

            }
        }



        return view

       }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun editClick(menuUserData: MenuUserData, position: Int) {
        val inflater = LayoutInflater.from(mainActivity)
        val v = inflater.inflate(R.layout.fragment_dialog, null)

        val addDialog = Dialog(mainActivity)
        addDialog.setContentView(v)
        addDialog.show()
        etItem = v.findViewById(R.id.etItem)
        etDescription = v.findViewById(R.id.etDescription)
        etItem.setText(menuUserData.menuName)
        etDescription.setText(menuUserData.menuDescription)
        btnAddItem = v.findViewById<Button>(R.id.btnAddItem)
        btnCancelItem = v.findViewById<Button>(R.id.btnCancelItem)

        btnAddItem.setText("Update")

//        addDialog.setPositiveButton("Update") { dialog, _ ->
        btnAddItem.setOnClickListener {

            val item = etItem.text.toString()
            val des = etDescription.text.toString()
            if (etItem.text.isEmpty()) {
                etItem.setError("Type Menu")
            } else if (etDescription.text.isEmpty()) {
                etDescription.setError("Type Price")
            } else {

                Toast.makeText(mainActivity, "Information Update", Toast.LENGTH_SHORT).show()
                mainActivity.arrayList.set(
                    position,
                    MenuUserData(menuName = item, menuDescription = des)
                )
                menuUserAdapter.notifyDataSetChanged()
                addDialog.dismiss()
            }
        }
        btnCancelItem.setOnClickListener{
            Toast.makeText(mainActivity,"Cancel",Toast.LENGTH_SHORT).show()
            addDialog.dismiss()
    }

    }

    override fun deleteClick(menuUserData: MenuUserData, position: Int) {
        mainActivity.arrayList.removeAt(position)
        Toast.makeText(mainActivity,"Delete",Toast.LENGTH_SHORT).show()
        menuUserAdapter.notifyDataSetChanged()
    }
}
